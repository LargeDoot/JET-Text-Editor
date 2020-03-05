package com.company.listeners.viewMenuListeners;

import com.company.EditorWindow;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class viewToolbarToggleListener implements ItemListener {

    EditorWindow window;

    public viewToolbarToggleListener(EditorWindow editorWindow) {

        this.window = editorWindow;

    }


    @Override
    public void itemStateChanged(ItemEvent e) {

        if (e.getStateChange() == ItemEvent.SELECTED) {
            window.getToolbar().setVisible(true);
        } else {
            window.getToolbar().setVisible(false);
        }
    }
}
