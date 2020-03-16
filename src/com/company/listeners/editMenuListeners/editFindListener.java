package com.company.listeners.editMenuListeners;

import com.company.EditorWindow;
import com.company.FindDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class editFindListener implements ActionListener {

    final EditorWindow window;

    public editFindListener(EditorWindow window) {

        this.window = window;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        new FindDialog(window);

    }
}
