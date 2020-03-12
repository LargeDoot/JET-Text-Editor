package com.company.listeners.fileMenuListeners;

import com.company.EditorWindow;
import com.company.FileBrowser;
import com.company.ReaderWriter;

import java.awt.event.ActionListener;

public class fileSaveAsListener extends fileSaveListener implements ActionListener {

    public fileSaveAsListener(EditorWindow window) {
        super(window);
    }

    @Override
    void saveFile() {
        fileToSave.setFileLocation(new FileBrowser().getURI());
        new ReaderWriter(fileToSave);
    }

}