package com.company.listeners.viewMenuListeners;

import com.company.EditorWindow;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class viewToolbarToggleListener implements ItemListener {

    final EditorWindow window;

    public viewToolbarToggleListener(EditorWindow editorWindow) {

        this.window = editorWindow;

    }


    @Override
    public void itemStateChanged(ItemEvent e) {

        boolean result;

        result = e.getStateChange() == ItemEvent.SELECTED;

        window.getToolbar().setVisible(result);
        window.getPrefs().setShowToolbar(result);


    }
}
