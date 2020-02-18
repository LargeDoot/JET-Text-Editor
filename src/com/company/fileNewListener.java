package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class fileNewListener extends JFrame implements ActionListener {

    public fileNewListener() {
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        new EditorWindow(new JETFile()).setVisible(true);

    }
}
