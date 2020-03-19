package com.company;

import javax.swing.*;
import javax.swing.text.BadLocationException;

public class JetUtils {

    public static void setLineNumbers(EditorWindow window) {

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
