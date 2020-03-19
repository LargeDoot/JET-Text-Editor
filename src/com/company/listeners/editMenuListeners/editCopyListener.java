package com.company.listeners.editMenuListeners;

import com.company.EditorWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class editCopyListener implements ActionListener {

    final EditorWindow window;
    JTextArea textArea;

    /**
     * Constructor to create the listener.
     *
     * @param window the parent Editor Window
     */
    public editCopyListener(EditorWindow window) {

        this.window = window;

    }

    /**
     * Method to get the currently selected text, and place it into the system clipboard.
     *
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        textArea = window.getTextArea();

        //Copy code from
        //https://stackoverflow.com/questions/6710350/copying-text-to-the-clipboard-using-java

        //Get the selected text
        String selectedText = textArea.getSelectedText();

        //If something is actually selected
        if (selectedText != null) {

            //Put the selected text into a string selection object in order to place it into the clipboard
            StringSelection stringSelection = new StringSelection(selectedText);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            System.err.println("Text copied!");

        } else {
            System.err.println("No text selected - cannot copy!");
        }
    }
}
