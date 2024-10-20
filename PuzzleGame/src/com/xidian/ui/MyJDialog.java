package com.xidian.ui;

import javax.swing.*;

public class MyJDialog extends JDialog {
    public MyJDialog(String content){
        setSize(200,150);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setModal(true);
        JLabel warning = new JLabel(content);
        warning.setBounds(0,0,200,150);
        getContentPane().add(warning);
        setVisible(true);
    }
}
