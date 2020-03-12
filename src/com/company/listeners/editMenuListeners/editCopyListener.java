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

    public editCopyListener(EditorWindow window) {

        this.window = window;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        textArea = window.getTextArea();

        //Copy code from
        //https://stackoverflow.com/questions/6710350/copying-text-to-the-clipboard-using-java

        String selectedText = textArea.getSelectedText();

        if (selectedText != null) {

            StringSelection stringSelection = new StringSelection(selectedText);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            System.err.println("Text copied!");

        } else {
            System.err.println("No text selected - cannot copy!");
        }
    }
}
