/*
* @author : alhelal
* */

package com.alhelal.textpad;

import javafx.scene.control.Tab;

import java.io.File;

public class CFile extends EditableFile
{
    public CFile(File file, Options options, Tab tab)
    {
        this.file = file;
        this.options = options;
        this.tab = tab;
        languageBehavior = CLanguageBehavior.getUniqueInstance();
    }
}
