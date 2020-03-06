package com.company.listeners.editMenuListeners;

import com.company.EditorWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class editPasteListener implements ActionListener {

    EditorWindow window;
    JTextArea textArea;

    public editPasteListener(EditorWindow window) {

        this.window = window;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Paste code from 
        //https://stackoverflow.com/questions/7105778/get-readable-text-only-from-clipboard

        String textToPaste = null;

        textArea = window.getTextArea();

        try {
            textToPaste = (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData(DataFlavor.stringFlavor);

        } catch (UnsupportedFlavorException ex) {

            System.err.println("ERROR - User has attempted to paste unsupported data into the editor!");
        } catch (IOException ex) {

            System.err.println("ERROR - IOException while trying to paste!");
        }

        if (textArea.getSelectedText() != null) {

            textArea.replaceSelection(textToPaste);

        } else {

            textArea.insert(textToPaste, textArea.getCaretPosition());

        }
    }
}
