package com.company.listeners.fileMenuListeners;

import com.company.EditorWindow;
import com.company.FileBrowser;
import com.company.JetUtils;
import com.company.ReaderWriter;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class fileOpenListener implements ActionListener {

    final EditorWindow window;

    /**
     * Constructor to create the listener.
     *
     * @param window the parent Editor Window
     */
    public fileOpenListener(EditorWindow window) {

        this.window = window;

    }

    /**
     * Obtains a file location from a fieBrowser object, and uses the chosen address to open a file via a ReaderWriter
     * object. It then sets the EditorWindow's title to match the files location, and sets the line number labels.
     *
     * @param actionEvent event
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        //Get the files location from a file browser dialog
        String readLocation = new FileBrowser("Open").getURI();

        //Create a ReaderWriter object to open the file
        new ReaderWriter(readLocation, window);

        //Set the title of the window as long as the file browser was not closed
        if (readLocation != null) {
            window.setTitle(String.format(" JET \t | \t %s", readLocation));

        }

        JetUtils.setLineNumbers(window);

    }

}
