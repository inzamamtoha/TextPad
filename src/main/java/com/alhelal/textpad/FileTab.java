package com.alhelal.textpad;

import javafx.scene.control.Tab;

import java.io.File;

public class FileTab
{
    File file;
    Tab tab;
    Options options;
    EditableFile editableFile;

    public FileTab(File file, Tab tab, Options options, EditableFile editableFile)
    {
        this.file = file;
        this.tab = tab;
        this.options = options;
        this.editableFile = editableFile;
    }
}
