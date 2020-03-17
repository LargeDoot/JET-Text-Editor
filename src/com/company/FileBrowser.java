package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileBrowser extends JFrame {

    public FileBrowser() {

    }

    public String getURI() {

        JFileChooser fileChooser = new JFileChooser("C:\\Users\\Ethan\\IdeaProjects\\JET-Text-Editor");
        String filePath = null;

        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Document (*.txt)", "txt"));
        fileChooser.setSelectedFile(new File("Untitled.txt"));

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