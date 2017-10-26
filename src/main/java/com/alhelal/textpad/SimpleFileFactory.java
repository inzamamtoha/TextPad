package com.alhelal.textpad;

import org.apache.commons.io.FilenameUtils;

import java.io.File;

public class SimpleFileFactory
{
    public EditableFile createFile(File file)
    {
        String fileName = file.getName();
        String extension = FilenameUtils.getExtension(fileName);
        EditableFile editableFile = null;
        if (extension.equals("java"))
        {
            editableFile = new JavaFile();
            System.out.println("java file detected");
        }
        else if (extension.equals("c"))
        {
            editableFile = new CFile();
        }
        else if (extension.equals("cpp"))
        {
            editableFile = new CplusFile();
        }
        else if (extension.equals("py"))
        {
            editableFile = new PythonFile();
        }
        //  editableFile = new TextFile(options);
        return editableFile;
    }
}
