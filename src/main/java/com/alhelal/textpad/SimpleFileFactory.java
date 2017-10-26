package com.alhelal.textpad;

public class SimpleFileFactory
{
    public EditableFile createFile(String extension)
    {
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
