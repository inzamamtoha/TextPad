package com.alhelal.textpad;

import javafx.scene.control.Tab;

import java.io.File;

public class FileTab
{
    String filePath;
    Tab tab;
    //EditableFile editableFile;

    public FileTab(String filePath, Tab tab)
    {
        this.filePath = filePath;
        this.tab = tab;
    }
}
