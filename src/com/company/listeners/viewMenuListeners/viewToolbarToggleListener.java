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

        Boolean result;

        if (e.getStateChange() == ItemEvent.SELECTED) {
            result = true;
        } else {
            result = false;
        }

        window.getToolbar().setVisible(result);
        window.getPrefs().setShowToolbar(result);

    }
}
