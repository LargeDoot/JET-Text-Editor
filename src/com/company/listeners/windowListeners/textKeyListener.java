package com.company.listeners.windowListeners;

import com.company.EditorWindow;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class textKeyListener implements KeyListener {

    EditorWindow window;

    public textKeyListener(EditorWindow window) {

        this.window = window;

    }


    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

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

        //Get char and word counts
        int wordCount, charCount = 0;

        String[] wordList = textArea.getText().split(" ");

        wordCount = wordList.length;
        charCount = textArea.getText().length();

        //Check if the string is "" as this will otherwise result in a length of 1
        if (wordList[0].equals("")) {

            wordCount = 0;

        }

        //Create strings to insert into the labels
        String lineNumString, lineColumnString, wordCountString, charCountString;

        lineNumString = String.format(" Ln: %d \t ", lineNum + 1);
        lineColumnString = String.format("Col: %d ", lineColumn + 1);

        wordCountString = String.format(" Words: %d \t ", wordCount);
        charCountString = String.format("Chars: %d ", charCount);

        //Set the label text
        window.getCharCount().setText(charCountString);
        window.getWordCount().setText(wordCountString);
        window.getLineNum().setText(lineNumString);
        window.getLineColumn().setText(lineColumnString);

    }
}


//wordCount = new JLabel(" Words: 0 \t ");
//charCount = new JLabel("Chars: 0 ");
//lineNum = new JLabel(" Ln: 0 \t ");
//lineColumn = new JLabel("Col: 0 ");