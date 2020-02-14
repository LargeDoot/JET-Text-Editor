package com.company;

import javax.swing.*;
import java.io.File;

public class FileBrowser extends JFrame {

    private String defaultAddress;

    public FileBrowser() {

    }

    public FileBrowser(String defaultAddress) {

        this.defaultAddress = defaultAddress;

    }

    public String getURI() {

        JFileChooser fileChooser = new JFileChooser(defaultAddress);
        String filePath = null;

        int i = fileChooser.showOpenDialog(this);
        if (i == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            filePath = file.getPath();

        }

        return filePath;
    }

}