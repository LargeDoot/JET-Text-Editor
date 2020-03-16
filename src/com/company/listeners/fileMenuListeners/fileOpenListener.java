package com.company.listeners.fileMenuListeners;

import com.company.EditorWindow;
import com.company.FileBrowser;
import com.company.ReaderWriter;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class fileOpenListener implements ActionListener {

    final EditorWindow window;

    public fileOpenListener(EditorWindow editorWindow) {

        this.window = editorWindow;

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        String readLocation = new FileBrowser().getURI();
        new ReaderWriter(readLocation, window);

        if (readLocation != null) {
            window.setTitle(String.format(" JET \t | \t %s", readLocation));

        }

        JTextArea textArea = window.getTextArea();

        //Variables for determining location
        int caretPos = textArea.getCaretPosition();
        int lineNum = 0, lineColumn = 0;

        //Get row and column numbers from the caretPos
        try {
            lineNum = textArea.getLineOfOffset(caretPos);
            lineColumn = caretPos - textArea.getLineStartOffset(lineNum);
        } catch (BadLocationException ignored) {

            System.err.println("Bad Location @ textKeyListener " + caretPos);
        }

        //Create strings to insert into the labels
        String lineNumString, lineColumnString;

        lineNumString = String.format(" Ln: %d \t ", lineNum + 1);
        lineColumnString = String.format("Col: %d ", lineColumn + 1);

        //Set the label text
        window.getLineNum().setText(lineNumString);
        window.getLineColumn().setText(lineColumnString);


    }

}
