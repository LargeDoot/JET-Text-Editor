package com.company.listeners.editMenuListeners;

import com.company.EditorWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class editPasteListener implements ActionListener {

    final EditorWindow window;
    JTextArea textArea;

    /**
     * Constructor to create the listener.
     *
     * @param window the parent Editor Window
     */
    public editPasteListener(EditorWindow window) {

        this.window = window;

    }

    /**
     * Code to paste text from the system clipboard into the parent editor window's text area. If text is selected, then
     * the selected text will be replaced with the pasted text.
     *
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        //Paste code from 
        //https://stackoverflow.com/questions/7105778/get-readable-text-only-from-clipboard

        String textToPaste = null;

        textArea = window.getTextArea();

        try {
            //Try to get text from the clipboard
            textToPaste = (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData(DataFlavor.stringFlavor);

        } catch (UnsupportedFlavorException ex) {

            System.err.println("ERROR - User has attempted to paste unsupported data into the editor!");
        } catch (IOException ex) {

            System.err.println("ERROR - IOException while trying to paste!");
        }

        //If text is selected, paste the text and replace the selected text, otherwise insert it after the carat
        if (textArea.getSelectedText() != null) {

            textArea.replaceSelection(textToPaste);

        } else {

            textArea.insert(textToPaste, textArea.getCaretPosition());

        }
    }
}
