package com.xidian.ui;

import com.xidian.domin.GameInfo;
import com.xidian.domin.User;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

//游戏的主页面
public class gameJFrame extends JFrame implements KeyListener, ActionListener {
    private int[][] arrRandom = new int[4][4];
    //空白图像的坐标
    private int x ;
    private int y ;
    private User user;
    private String[] pathArr = {
            "PuzzleGame\\image\\animal\\animal",
            "PuzzleGame\\image\\girl\\girl",
            "PuzzleGame\\image\\sport\\sport"
    };

    private String path = "PuzzleGame\\image\\animal\\animal5\\";
    private int count = -1;
    private int[][] win = new int[][]{
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };

    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem accountItem = new JMenuItem("公众号");
    JMenuItem girlItem = new JMenuItem("美女");
    JMenuItem animalItem = new JMenuItem("动物");
    JMenuItem sportItem = new JMenuItem("运动");
    JMenuItem[] saveItem = new JMenuItem[5];
    JMenuItem[] loadItem = new JMenuItem[5];
    public gameJFrame(User user){
        this.user = user;
        initJFrame();//初始化界面

        initJMenuBar();//初始化菜单

        initData();

        initImage();

        setVisible(true);
    }

    private void initImage() {//初始化图面
        //清空原本出现的所有图片
        getContentPane().removeAll();
        if(vector()){
            JLabel winJLabel = new JLabel(new ImageIcon("PuzzleGame/image/win.png"));
            winJLabel.setBounds(203,283,197,73);
            getContentPane().add(winJLabel);
            System.out.println("胜利");
        }
        count++;
        JLabel countText = new JLabel("步数："+count);
        countText.setBounds(20,20,80,30);
        getContentPane().add(countText);
        //先加载的图片在上方，后加载的图片塞在下方
        for(int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++) {
                int num = arrRandom[i][j];
                //创建一个图片ImageIcon的对象
                //创建一个JLabel的对象（管理容器）
                JLabel jLabel = new JLabel(new ImageIcon(path+num+".jpg"));
                //指定图片位置
                jLabel.setBounds(j*105+83, i*105+134,105,105);
                //给图像加一个边框
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK,Color.WHITE));
                //把管理容器添加到界面中,界面也是一个容器
                getContentPane().add(jLabel);
            }
        }
        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("PuzzleGame/image/background.png"));
        background.setBounds(40,40,508,560);
        getContentPane().add(background);

        //刷新一下界面
        getContentPane().repaint();

    }

    private void initData() {
        int random;
        Random rd = new Random();
        int temp;
        int[] arr = new int[16];
        for(int i = 0; i < 16; i++){
            arr[i] = i ;
        }
        for(int i = 0; i < 16; i++){
            random = rd.nextInt(0,16);
            temp = arr[i];
            arr[i] = arr[random];
            arr[random] = temp;
        }
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(arr[count] == 0){
                    x = i;
                    y = j;
                }
                arrRandom[i][j] = arr[count++];
                System.out.print(arrRandom[i][j]+"\t");
            }
            System.out.println();
        }
    }


    private void initJMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();//创建菜单条

        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");

        JMenu replaceItem = new JMenu("更换图片");
        JMenu saveJMenu = new JMenu("存档");
        JMenu loadJMenu = new JMenu("读档");

        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);

        girlItem.addActionListener(this);
        animalItem.addActionListener(this);
        sportItem.addActionListener(this);


        accountItem.addActionListener(this);

        functionJMenu.add(replaceItem);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        replaceItem.add(animalItem);
        replaceItem.add(girlItem);
        replaceItem.add(sportItem);

        aboutJMenu.add(accountItem);
        for (int i = 0; i < 5; i++) {
            saveItem[i] = new JMenuItem();
            loadItem[i] = new JMenuItem();
            saveItem[i].setText("存档"+i+"(空)");
            loadItem[i].setText("读档"+i+"(空)");
            saveItem[i].addActionListener(this);
            loadItem[i].addActionListener(this);
            saveJMenu.add(saveItem[i]);
            loadJMenu.add(loadItem[i]);
        }
        functionJMenu.add(saveJMenu);
        functionJMenu.add(loadJMenu);
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        setSize(603,680);
        setTitle("拼图单机版 v1.0");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);//置顶
        setLayout(null);//取消JFrame默认容器的居中放置
        addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();
        if(code == 65){//按下A查看全图
            getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon(path+"all.jpg"));
            all.setBounds(83,134,420,420);
            getContentPane().add(all);
            JLabel background = new JLabel(new ImageIcon("PuzzleGame/image/background.png"));
            background.setBounds(40,40,508,560);
            getContentPane().add(background);
            getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if(vector()){
            return ;
        }
        int code = keyEvent.getKeyCode();
        //左：37 上：38 右：39 下：40
        if(code == 37){//向左移动
            if(y == 0) return;
            arrRandom[x][y] = arrRandom[x][y-1];
            arrRandom[x][y-1] = 0;
            y--;
            System.out.println("左");
            initImage();
        } else if(code == 38){//向上移动
            if(x == 0) return;
            arrRandom[x][y] = arrRandom[x-1][y];
            arrRandom[x-1][y] = 0;
            x--;
            System.out.println("上");
            initImage();
        } else if(code == 39){//向右移动
            if(y == 3) return;
            arrRandom[x][y] = arrRandom[x][y+1];
            arrRandom[x][y+1] = 0;
            y++;
            System.out.println("右");
            initImage();
        } else if(code == 40){//向下移动
            if(x == 3) return;
            arrRandom[x][y] = arrRandom[x+1][y];
            arrRandom[x+1][y] = 0;
            x++;
            System.out.println("下");
            initImage();
        } else if (code == 65) {
            count--;
            initImage();
        } else if (code == 87) {
            arrRandom = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            initImage();
        }
    }
    public boolean vector(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(arrRandom[i][j] != win[i][j]) return false;
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object obj = actionEvent.getSource();
        Random rd = new Random();
        if(obj == null){
            System.out.println("无效");
        } else if (obj == replayItem) {
            initData();
            count = -1;
            initImage();
        } else if (obj == reLoginItem) {
            setVisible(false);
            try {
                new LoginJFrame();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (obj == closeItem) {
            System.exit(0);
        } else if (obj == accountItem) {
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon("PuzzleGame/image/about.png"));
            jLabel.setBounds(0,0,258,258);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(344,344);
            jDialog.setLocationRelativeTo(null);
            jDialog.setAlwaysOnTop(true);
            jDialog.setModal(true);
            jDialog.setVisible(true);
        } else if (obj == animalItem) {
            int random = rd.nextInt(1,9);
            path = pathArr[0]+random+"\\";
            initData();
            count = -1;
            initImage();
        } else if (obj == girlItem) {
            int random = rd.nextInt(1,14);
            path = pathArr[1]+random+"\\";
            initData();
            count = -1;
            initImage();
        } else if (obj == sportItem) {
            int random = rd.nextInt(1,11);
            path = pathArr[2]+random+"\\";
            initData();
            count = -1;
            initImage();
        } else if (obj == saveItem[0] || obj == saveItem[1] || obj == saveItem[2] || obj == saveItem[3] || obj == saveItem[4]) {
            JMenuItem item = (JMenuItem)obj;
            int index = item.getText().charAt(2)-'0';
            GameInfo[] gameInfos = user.getGameInfos();
            gameInfos[index] = new GameInfo(x,y,count,path,arrRandom);


        } else if (obj == loadItem[0] || obj == loadItem[1] || obj == loadItem[2] || obj == loadItem[3] || obj == loadItem[4]) {
            JMenuItem item = (JMenuItem)obj;
            int index = item.getText().charAt(2)-'0';
        }

    }
}
