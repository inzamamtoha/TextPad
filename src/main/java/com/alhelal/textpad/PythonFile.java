/*
* @author : alhelal
* */

package com.alhelal.textpad;

import javafx.scene.control.Tab;

import java.io.File;

public class PythonFile extends EditableFile
{
    String keywords = "src/main/java/com/alhelal/resource/PythonKeywords";

    public PythonFile(File file, Options options, Tab tab)
    {
        this.file = file;
        this.options = options;
        this.tab = tab;
        //this.tab.setContent(new VirtualizedScrollPane<CodeArea>(new ProgramEditor(this.options.stage, keywords).getEditArea()));
        languageBehavior = PythonLanguageBehavior.getUniqueInstance();
    }
}
