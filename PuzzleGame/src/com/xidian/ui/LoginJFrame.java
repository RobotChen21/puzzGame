package com.xidian.ui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import com.xidian.domin.User;
import com.xidian.util.CodeUtil;

public class LoginJFrame extends JFrame implements MouseListener{


    ArrayList<User> allUser;
//    static {
//        allUser.add(new User("zhangsan","123456"));
//        allUser.add(new User("lisi","123456"));
//    }
    private JButton loginButton = new JButton(new ImageIcon("PuzzleGame/image/login/登录按钮.png"));
    private JButton registerButton = new JButton(new ImageIcon("PuzzleGame/image/login/注册按钮.png"));
    private String codeStr = CodeUtil.getCode();               //CodeUtil.getCode();
    private JTextField username = new JTextField();
    private JPasswordField password = new JPasswordField();
    private JTextField code = new JTextField();
    private JLabel rightCode = new JLabel(codeStr);
    private JButton showPassword = new JButton(new ImageIcon("PuzzleGame/image/login/显示密码.png"));
    private char defaultEchoChar = password.getEchoChar();
    private registerJFrame registerJF;
    public LoginJFrame() throws IOException, ClassNotFoundException {
        registerJF = new registerJFrame(this);
        registerJF.setVisible(false);
        getUserInfo();
        initView();
        initJFrame();
    }

    public ArrayList<User> getAllUser() {
        return allUser;
    }

    private void getUserInfo() throws IOException, ClassNotFoundException {
        File file = new File("PuzzleGame/src/com/xidian/domin/User.txt");
        if (file.length() == 0) {
            // 文件为空，初始化一个新的 ArrayList
            allUser = new ArrayList<>();
            System.out.println("File is empty. Initialized allUser as an empty list.");
        } else {
            try (ObjectInputStream userInfo = new ObjectInputStream(new FileInputStream(file))) {
                Object obj = userInfo.readObject();
                if (obj instanceof ArrayList<?>) {
                    allUser = (ArrayList<User>) obj;
                } else {
                    throw new ClassCastException("File does not contain a valid ArrayList<User>");
                }
            } catch (EOFException e) {
                System.out.println("Reached end of file unexpectedly. Initializing an empty list.");
                allUser = new ArrayList<>();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                allUser = new ArrayList<>(); // 初始化为一个空列表，以防读取失败
            }
        }
    }

    private void initView() {
        //添加用户文字
        JLabel usernameText = new JLabel(new ImageIcon("PuzzleGame/image/login/用户名.png"));
        usernameText.setBounds(116,135,47,17);
        getContentPane().add(usernameText);

        username.setBounds(195,134,200,30);
        getContentPane().add(username);

        JLabel passwordText = new JLabel(new ImageIcon("PuzzleGame/image/login/密码.png"));
        passwordText.setBounds(130,195,32,16);
        getContentPane().add(passwordText);

        password.setBounds(195,195,200,30);
        getContentPane().add(password);
        showPassword.setBounds(400,195,18,29);
        getContentPane().add(showPassword);
        showPassword.setBorderPainted(false);
        showPassword.setContentAreaFilled(false);
        showPassword.addMouseListener(this);

        JLabel codeText = new JLabel(new ImageIcon("PuzzleGame/image/login/验证码.png"));
        codeText.setBounds(133,256,50,30);
        getContentPane().add(codeText);


        code.setBounds(195,256,100,30);
        getContentPane().add(code);


        rightCode.setBounds(300,256,50,30);
        rightCode.addMouseListener(this);
        getContentPane().add(rightCode);


        loginButton.setBounds(123,310,128,47);
        loginButton.setBorderPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.addMouseListener(this);
        getContentPane().add(loginButton);


        registerButton.setBounds(256,310,128,47);
        registerButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);
        registerButton.addMouseListener(this);
        getContentPane().add(registerButton);

        JLabel background = new JLabel(new ImageIcon("PuzzleGame/image/login/background.png"));
        background.setBounds(0,0,470,390);
        getContentPane().add(background);

    }

    private void initJFrame() {
        setSize(488,430);
        setTitle("拼图单机版 v1.0");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);//置顶
        setVisible(true);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        String usernameInput = username.getText();
        String passwordInput = new String(password.getPassword());
        String codeInput = code.getText();
        Object obj = mouseEvent.getSource();
        int flag;
        if(obj == rightCode){
            codeStr = CodeUtil.getCode();
            rightCode.setText(codeStr);
        } else if (obj == loginButton) {
            if(codeInput.length() == 0){
                new MyJDialog("验证码不能为空");
            } else if (usernameInput.length() == 0 || passwordInput.length() == 0) {
                new MyJDialog("用户名或密码不能为空");
            } else if (!codeInput.equalsIgnoreCase(rightCode.getText())) {
                new MyJDialog("验证码错误");
            } else if ((flag = contain(usernameInput,passwordInput)) != -1) {
                setVisible(false);
                new gameJFrame(allUser.get(flag),this);
            }else {
                new MyJDialog("用户名或者密码错误");
            }
            System.out.println(11);
        } else if (obj == registerButton){
            System.out.println("点击了注册页面");
            setVisible(false);
            username.setText("");
            password.setText("");
            registerJF.setVisible(true);
//            new registerJFrame(this);
        }
    }

    private int contain(String usernameInput, String passwordInput) {
        for (int i = 0; i < allUser.size(); i++) {
            User temp = allUser.get(i);
            if(usernameInput.equals(temp.getUserName())&&passwordInput.equals(temp.getPassWord())){
                return i;
            }
        }
        return -1;
    }

    private boolean contains(User userInfo) {
        String tempName = userInfo.getUserName();
        for (int i = 0; i < allUser.size(); i++) {
            User user = allUser.get(i);
            if(user.getUserName().equals(tempName)&&user.getPassWord().equals(userInfo.getPassWord())){
                return true;
            }
        }
        return false;
    }


    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Object obj = mouseEvent.getSource();
        if(obj == registerButton){
            registerButton.setIcon(new ImageIcon("PuzzleGame/image/login/注册按下.png"));
        } else if (obj == loginButton){
            loginButton.setIcon(new ImageIcon("PuzzleGame/image/login/登录按下.png"));
        } else if (obj == showPassword){
            showPassword.setIcon(new ImageIcon("PuzzleGame/image/login/显示密码按下.png"));
            password.setEchoChar('\0');
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        Object obj = mouseEvent.getSource();
        if(obj == registerButton){
            registerButton.setIcon(new ImageIcon("PuzzleGame/image/login/注册按钮.png"));
        } else if (obj == loginButton){
            loginButton.setIcon(new ImageIcon("PuzzleGame/image/login/登录按钮.png"));
        } else if (obj == showPassword){
            showPassword.setIcon(new ImageIcon("PuzzleGame/image/login/显示密码.png"));
            password.setEchoChar(defaultEchoChar);
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
