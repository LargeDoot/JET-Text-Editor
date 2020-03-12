package com.company;

import javax.swing.*;
import java.io.File;

public class FileBrowser extends JFrame {

    public FileBrowser() {

    }

    public String getURI() {

        JFileChooser fileChooser = new JFileChooser();
        String filePath = null;

        int i = fileChooser.showOpenDialog(this);
        if (i == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            filePath = file.getPath();

        }

        //Dispose of this frame to ensure the application closes properly
        dispose();


        return filePath;
    }

}