package com.alhelal.textpad;

import org.apache.commons.io.FilenameUtils;

public class SimpleFileFactory
{
    public EditableFile createFile(String path)
    {
        String extension = FilenameUtils.getExtension(path);
        EditableFile editableFile = null;
                if (extension.equals("java"))
                {
                    editableFile = new JavaFile();
                    System.out.println("java file detected");
                }
                else if (extension.equals("c"))
                    editableFile = new CFile();
                else if (extension.equals("cpp"))
                    editableFile = new CplusFile();
                else if (extension.equals("py"))
                    editableFile = new PythonFile();
                  //  editableFile = new TextFile(options);
        return editableFile;
    }
}
