package com.alhelal.textpad;

import javafx.scene.control.Tab;

import java.io.File;

public class CplusFile extends EditableFile
{
    public CplusFile(File file, Options options, Tab tab)
    {
        this.file = file;
        this.options = options;
        this.tab = tab;
        languageBehavior = CplusLanguageBehavior.getUniqueInstance();
    }
}
