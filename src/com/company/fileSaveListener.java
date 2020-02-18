package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class fileSaveListener extends JFrame implements ActionListener {

    EditorWindow window;
    JETFile fileToSave;

    public fileSaveListener(EditorWindow window) {

        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        System.out.println("File > Save");

        this.fileToSave = window.getCurrentFile();

        fileToSave.setTextContents(window.getText());

        if (fileToSave.getFileLocation() == null) {

            fileToSave.setFileLocation(new FileBrowser().getURI());

        }


        new ReaderWriter(fileToSave);


    }

}
