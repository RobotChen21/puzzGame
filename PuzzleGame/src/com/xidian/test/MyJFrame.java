package com.xidian.test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class MyJFrame extends JFrame implements ActionListener {
    JButton jtb1 = new JButton("点我");
    JButton jtb2 = new JButton("再点我");
    public MyJFrame(){
        setSize(603,600);
        setTitle("测试");
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        jtb1.setBounds(0,0,100,50);
        jtb2.setBounds(100,0,100,50);
        jtb1.addActionListener(this);
        jtb2.addActionListener(this);

        //以下是匿名内部类的写法
//        jtb1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                jtb1.setSize(200,100);
//            }
//        });
//        jtb2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                Random r= new Random();
//                jtb2.setLocation(r.nextInt(500),r.nextInt(100));
//            }
//        });
        getContentPane().add(jtb1);
        getContentPane().add(jtb2);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if(source == jtb1){
            jtb1.setSize(200,100);
        }else if(source == jtb2){
            Random r= new Random();
            jtb2.setLocation(r.nextInt(500),r.nextInt(100));
        }
    }
}
