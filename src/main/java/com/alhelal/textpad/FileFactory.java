/*
* @author : alhelal
* */

package com.alhelal.textpad;

import javafx.scene.control.Tab;
import org.apache.commons.io.FilenameUtils;

import java.io.File;

public class FileFactory
{
    public EditableFile createFile(File file, Options options, Tab tab)
    {
        if (file == null)
        {
            return new TextFile(null, options, tab);
        }
        String fileName = file.getName();
        String extension = FilenameUtils.getExtension(fileName);
        EditableFile editableFile = null;
        if (extension.equals("java"))
        {
            editableFile = new JavaFile(file, options, tab);
            System.out.println("java file detected");
        }
        else if (extension.equals("c"))
        {
            editableFile = new CFile(file, options, tab);
        }
        else if (extension.equals("cpp"))
        {
            editableFile = new CplusFile(file, options, tab);
        }
        else if (extension.equals("py"))
        {
            editableFile = new PythonFile(file, options, tab);
        }
        else if (extension.equals("tex"))
        {
            editableFile = new LaTexFile(file, options, tab);
        }
        else
        {
            editableFile = new TextFile(file, options, tab);
        }
        return editableFile;
    }
}
