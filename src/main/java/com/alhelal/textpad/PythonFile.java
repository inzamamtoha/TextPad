package com.alhelal.textpad;

import javafx.scene.control.Tab;

import java.io.File;

public class PythonFile extends EditableFile
{
    public PythonFile(File file, Options options, Tab tab)
    {
        this.file = file;
        this.options = options;
        this.tab = tab;
        languageBehavior = PythonLanguageBehavior.getUniqueInstance();
    }
}
