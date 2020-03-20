package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/*******************************************************************************
 * Copyright (c) Ethan Wilson 2020.
 *
 * The file browser class creates a JFileChooser where a user can pick a file location, and the getURI method will
 * return the URI chosen by the user.
 *
 * @author Ethan Wilson
 * @since 13-02-2020
 ******************************************************************************/
public class FileBrowser extends JFrame {

    final String title;

    /**
     * Creates the fileBrowser object
     *
     * @param title determines the title of the file browser dialog
     */
    public FileBrowser(String title) {

        this.title = title;

    }

    /**
     * Opens a new filChooser window and gets a URI from the user.
     *
     * @return the chosen URI, or null if the fileChooser was cancelled
     */
    public String getURI() {

        //Create a new fileChooser dialog and set the title
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(title);

        String filePath = null;

        //Set a file filter so that the user is encourages to save as a .txt file (not compulsory)
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Document (*.txt)", "txt"));
        fileChooser.setSelectedFile(new File("Untitled.txt"));

        //Show the dialog
        int i = fileChooser.showOpenDialog(this);

        //Get the URI and set filePath
        if (i == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            filePath = file.getPath();

        }

        //Dispose of this frame to ensure the application closes properly
        dispose();


        return filePath;
    }

}