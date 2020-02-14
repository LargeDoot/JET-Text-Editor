package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class fileOpenListener extends JFrame implements ActionListener {


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        System.err.println("The File>Open button was clicked!!");

    }

}
