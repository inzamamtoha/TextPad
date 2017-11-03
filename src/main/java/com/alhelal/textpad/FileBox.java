/*
* @author : alhelal
* */

package com.alhelal.textpad;

import javafx.scene.control.Tab;

import java.io.File;

public class FileBox
{
    FileFactory fileFactory;

    public FileBox(FileFactory fileFactory)
    {
        this.fileFactory = fileFactory;
    }

    public EditableFile orderFile(File file, Options options, Tab tab)
    {
        EditableFile editableFile;
        editableFile = fileFactory.createFile(file, options, tab);
        return editableFile;
    }
}
