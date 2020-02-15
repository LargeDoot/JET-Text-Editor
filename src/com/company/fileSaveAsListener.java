package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class fileSaveAsListener extends JFrame implements ActionListener {

    JETFile fileToSave;

    public fileSaveAsListener(JETFile file) {

        this.fileToSave = file;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        System.out.println("File > Open");

        fileToSave.setFileLocation( new FileBrowser().getURI() );
        new ReaderWriter(fileToSave);

    }

}