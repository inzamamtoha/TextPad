package com.alhelal.textpad;

public class FileBox
{
    SimpleFileFactory simpleFileFactory;
    public FileBox(SimpleFileFactory simpleFileFactory)
    {
        this.simpleFileFactory = simpleFileFactory;
    }
    public EditableFile orderFile(String path)
    {
        EditableFile editableFile;
        editableFile = simpleFileFactory.createFile(path);
        return editableFile;
    }
}
