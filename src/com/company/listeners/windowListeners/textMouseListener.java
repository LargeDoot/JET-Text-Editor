package com.company.listeners.windowListeners;

import com.company.EditorWindow;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class textMouseListener implements MouseListener {

    EditorWindow window;

    public textMouseListener(EditorWindow window) {

        this.window = window;

    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
        String lineNumString, lineColumnString, wordCountString, charCountString;

        lineNumString = String.format(" Ln: %d \t ", lineNum + 1);
        lineColumnString = String.format("Col: %d ", lineColumn + 1);

        //Set the label text
        window.getLineNum().setText(lineNumString);
        window.getLineColumn().setText(lineColumnString);

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}