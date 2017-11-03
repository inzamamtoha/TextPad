/*
* @author : alhelal
* */

package com.alhelal.textpad;

import javafx.scene.control.Tab;

import java.io.File;

public class LaTexFile extends EditableFile
{
    String keywordsPath = "src/main/java/com/alhelal/resource/LaTeXKeywords";

    public LaTexFile(File file, Options options, Tab tab)
    {
        this.file = file;
        this.options = options;
        this.tab = tab;
        //this.tab.setContent(new VirtualizedScrollPane<CodeArea>(new ProgramEditor(this.options.stage, keywordsPath).getEditArea()));
        languageBehavior = LaTexLanguageBehavior.getUniqueInstance();
    }
}
