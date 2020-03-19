package com.company.listeners.windowListeners;

import com.company.EditorWindow;
import com.company.JetUtils;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class textKeyListener implements KeyListener {

    final EditorWindow window;

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

        JetUtils.setLineNumbers(window);

    }
}
