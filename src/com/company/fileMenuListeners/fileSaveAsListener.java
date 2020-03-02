package com.company.fileMenuListeners;

import com.company.EditorWindow;
import com.company.FileBrowser;
import com.company.JETFile;
import com.company.ReaderWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class fileSaveAsListener implements ActionListener {

    EditorWindow window;
    JETFile fileToSave;

    public fileSaveAsListener(EditorWindow window) {

        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        System.out.println("File > Save As");

        this.fileToSave = window.getCurrentFile();
        fileToSave.setTextContents(window.getText());


        fileToSave.setFileLocation(new FileBrowser().getURI());
        new ReaderWriter(fileToSave);


    }

}