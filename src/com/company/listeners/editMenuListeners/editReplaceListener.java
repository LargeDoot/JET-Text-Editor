package com.company.listeners.editMenuListeners;

import com.company.EditorWindow;
import com.company.FindDialog;
import com.company.ReplaceDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class editReplaceListener implements ActionListener {

    final EditorWindow window;

    public editReplaceListener(EditorWindow window) {

        this.window = window;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        new ReplaceDialog(window);

    }
}
