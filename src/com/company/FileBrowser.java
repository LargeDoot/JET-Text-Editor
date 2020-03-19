package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileBrowser extends JFrame {

    final String title;

    public FileBrowser(String title) {

        this.title = title;

    }

    public String getURI() {

        JFileChooser fileChooser = new JFileChooser("C:\\Users\\Ethan\\IdeaProjects\\JET-Text-Editor");
        fileChooser.setDialogTitle(title);

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