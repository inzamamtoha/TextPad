package com.alhelal.textpad;

import javafx.scene.control.Tab;

import java.io.File;

public class FileBox
{
    SimpleFileFactory simpleFileFactory;

    public FileBox(SimpleFileFactory simpleFileFactory)
    {
        this.simpleFileFactory = simpleFileFactory;
    }

    public EditableFile orderFile(File file, Options options, Tab tab)
    {
        EditableFile editableFile;
        editableFile = simpleFileFactory.createFile(file, options, tab);
        return editableFile;
    }
}
