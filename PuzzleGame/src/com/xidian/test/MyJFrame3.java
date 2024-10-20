package com.xidian.test;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyJFrame3 extends JFrame implements KeyListener {
    public MyJFrame3(){
        setSize(603,600);
        setTitle("测试");
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        addKeyListener(this);
        setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
//        System.out.println("我也不知道");
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
//        System.out.println("按下不松");
        System.out.println((char) (keyEvent.getKeyCode()));
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
//        System.out.println("键盘松开");
    }
}
