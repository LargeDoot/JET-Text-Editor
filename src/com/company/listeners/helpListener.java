package com.company.listeners;

import com.company.EditorWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class helpListener implements ActionListener {

    public helpListener() {
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        JOptionPane.showMessageDialog(null, "This is where a help dialog would go!");

    }
}
