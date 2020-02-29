package com.company.fileMenuListeners;

import com.company.EditorWindow;
import com.company.JETFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class fileNewListener extends JFrame implements ActionListener {

    public fileNewListener() {
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        new EditorWindow().setVisible(true);

    }
}
