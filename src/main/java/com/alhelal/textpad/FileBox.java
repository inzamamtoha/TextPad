package com.alhelal.textpad;

import java.io.File;

public class FileBox
{
    SimpleFileFactory simpleFileFactory;

    public FileBox(SimpleFileFactory simpleFileFactory)
    {
        this.simpleFileFactory = simpleFileFactory;
    }

    public EditableFile orderFile(File file)
    {
        EditableFile editableFile;
        editableFile = simpleFileFactory.createFile(file);
        return editableFile;
    }
}
