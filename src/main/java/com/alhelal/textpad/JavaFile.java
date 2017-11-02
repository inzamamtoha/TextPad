package com.alhelal.textpad;

import javafx.scene.control.Tab;

import java.io.File;

public class JavaFile extends EditableFile
{
    public JavaFile(File file, Options options, Tab tab)
    {

        this.file = file;
        this.options = options;
        this.tab = tab;
        languageBehavior = JavaLanguageBehavior.getUniqueInstance();
    }
}
