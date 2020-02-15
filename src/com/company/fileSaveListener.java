package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class fileSaveListener extends JFrame implements ActionListener {

    JETFile fileToSave;

    public fileSaveListener(EditorWindow editorWindow, JETFile file) {

        this.fileToSave = file;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        System.out.println("File > Open");

        new ReaderWriter(fileToSave);

    }
}
