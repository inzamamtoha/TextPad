package com.alhelal.textpad;

import javafx.scene.control.Tab;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;

import java.io.File;

public class JavaFile extends EditableFile
{
    String keywordsPath = "src/main/java/com/alhelal/resource/JavaKeywords";
    public JavaFile(File file, Options options, Tab tab)
    {

        this.file = file;
        this.options = options;
        this.tab = tab;
        //this.tab.setContent(new VirtualizedScrollPane<CodeArea>(new ProgramEditor(this.options.stage, keywordsPath).getEditArea()));
        //this.options.centerPane.getTabs().addAll(this.tab);
        languageBehavior = JavaLanguageBehavior.getUniqueInstance();
    }
}
