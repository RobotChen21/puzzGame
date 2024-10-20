package com.xidian.test;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyJFrame2 extends JFrame implements MouseListener {
    JButton jtb1 = new JButton("点我");
    JButton jtb2 = new JButton("再点我");
    public MyJFrame2(){
        setSize(603,600);
        setTitle("测试");
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        jtb1.setBounds(0,0,100,50);
        jtb2.setBounds(100,0,100,50);
        jtb1.addMouseListener(this);
        jtb2.addMouseListener(this);
        getContentPane().add(jtb1);
        getContentPane().add(jtb2);
        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        System.out.println("单击");
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        System.out.println("按下不松");
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        System.out.println("松开");
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        System.out.println("滑入");
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        System.out.println("划出");
    }
}
