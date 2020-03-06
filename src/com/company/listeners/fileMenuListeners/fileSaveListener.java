package com.company.listeners.fileMenuListeners;

import com.company.EditorWindow;
import com.company.FileBrowser;
import com.company.JETFile;
import com.company.ReaderWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class fileSaveListener implements ActionListener {

    EditorWindow window;
    JDialog dialog;
    JETFile fileToSave;

    public fileSaveListener(EditorWindow window) {

        this.window = window;
    }

    public fileSaveListener(EditorWindow window, JDialog dialog) {

        this.dialog = dialog;
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        this.fileToSave = window.getCurrentFile();
        fileToSave.setTextContents(window.getText());

        saveFile();

    }

    void saveFile() {
        if (fileToSave.getFileLocation().equals("Untitled")) {

            fileToSave.setFileLocation(new FileBrowser().getURI());
        }

        new ReaderWriter(fileToSave);

        if (dialog != null) {

            window.dispose();
        }
    }

}
