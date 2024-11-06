package com.xidian.ui;

import com.xidian.domin.User;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class registerJFrame extends JFrame implements MouseListener {
    private JTextField username = new JTextField();
    private JPasswordField password = new JPasswordField();
    private JPasswordField rePassword =new JPasswordField();
    private JButton resetButton = new JButton(new ImageIcon("PuzzleGame/image/register/重置按钮.png"));
    private JButton registerButton = new JButton(new ImageIcon("PuzzleGame/image/register/注册按钮.png"));
    private LoginJFrame loginJFrame;
    public registerJFrame(LoginJFrame loginJFrame){
        this.loginJFrame = loginJFrame;
        initView();
        initJFrame();
    }

    private void initView() {

        JLabel usernameText = new JLabel(new ImageIcon("PuzzleGame/image/register/注册用户名.png"));
        usernameText.setBounds(100,130,79,17);
        getContentPane().add(usernameText);
        JLabel passwordText = new JLabel(new ImageIcon("PuzzleGame/image/register/注册密码.png"));
        passwordText.setBounds(115,180,64,16);
        getContentPane().add(passwordText);
        JLabel rePasswordText = new JLabel(new ImageIcon("PuzzleGame/image/register/再次输入密码.png"));
        rePasswordText.setBounds(85,230,96,17);
        getContentPane().add(rePasswordText);
        username.setBounds(210,125,180,25);
        getContentPane().add(username);
        password.setBounds(210,175,180,25);
        getContentPane().add(password);
        rePassword.setBounds(210,225,180,25);
        getContentPane().add(rePassword);
        resetButton.setBounds(260,280,128,47);
        resetButton.setBorderPainted(false);
        resetButton.setContentAreaFilled(false);
        resetButton.addMouseListener(this);
        getContentPane().add(resetButton);
        registerButton.setBounds(120,280,128,47);
        getContentPane().add(registerButton);
        registerButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);
        registerButton.addMouseListener(this);
        JLabel background = new JLabel(new ImageIcon("PuzzleGame/image/register/background.png"));
        background.setBounds(0,0,470,390);
        getContentPane().add(background);
    }

    private void initJFrame() {
        setSize(488,440);
        setTitle("拼图单机版 v1.0");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);//置顶
        setVisible(false);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Object obj = mouseEvent.getSource();
        String usernameInput = username.getText();
        String passwordInput = new String(password.getPassword());
        String rePasswordInput = new String(rePassword.getPassword());
        if(obj == registerButton){
            if (usernameInput.isEmpty() || passwordInput.isEmpty() || rePasswordInput.isEmpty()){
                new MyJDialog("输入框不能为空");
            }
            else if(!passwordInput.equals(rePasswordInput)){
                new MyJDialog("两次密码不一致");
            } else if(contains(usernameInput)){
                LoginJFrame.allUser.add(new User(usernameInput,passwordInput));
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("PuzzleGame/src/com/xidian/domin/User.txt",false));
                    oos.writeObject(LoginJFrame.allUser);
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("对象无法写回");
                }
                new MyJDialog("注册成功");
                setVisible(false);
                username.setText("");
                password.setText("");
                rePassword.setText("");
                loginJFrame.setVisible(true);
            } else{
                new MyJDialog("用户名已存在");
            }
        } else if (obj == resetButton) {
            username.setText("");
            password.setText("");
            rePassword.setText("");
        }
    }

    private boolean contains(String userInfo) {
        for (int i = 0; i < LoginJFrame.allUser.size(); i++) {
            if(LoginJFrame.allUser.get(i).getUserName().equals(userInfo)){
                return false;
            }
        }
        return true;
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Object obj = mouseEvent.getSource();
        if(obj == registerButton){
            registerButton.setIcon(new ImageIcon("PuzzleGame/image/register/注册按下.png"));
        } else if (obj == resetButton) {
            resetButton.setIcon(new ImageIcon("PuzzleGame/image/register/重置按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        Object obj = mouseEvent.getSource();
        if(obj == registerButton){
            registerButton.setIcon(new ImageIcon("PuzzleGame/image/register/注册按钮.png"));
        } else if (obj == resetButton) {
            resetButton.setIcon(new ImageIcon("PuzzleGame/image/register/重置按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
