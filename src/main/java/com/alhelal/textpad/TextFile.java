package com.alhelal.textpad;

import javafx.scene.control.Tab;

import java.io.File;

public class TextFile extends EditableFile
{
    public TextFile(File file, Options options, Tab tab)
    {
        this.file = file;
        this.options = options;
        this.tab = tab;
        languageBehavior = new TextFileBehavior();
    }
}
