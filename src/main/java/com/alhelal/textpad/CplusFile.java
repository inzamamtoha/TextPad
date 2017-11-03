package com.alhelal.textpad;

import javafx.scene.control.Tab;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;

import java.io.File;

public class CplusFile extends EditableFile
{
    String keywordsPaht = "src/main/java/com/alhelal/resource/CplusKeywords";
    public CplusFile(File file, Options options, Tab tab)
    {
        this.file = file;
        this.options = options;
        this.tab = tab;
        //this.tab.setContent(new VirtualizedScrollPane<CodeArea>(new ProgramEditor(this.options.stage, keywordsPaht).getEditArea()));
        languageBehavior = CplusLanguageBehavior.getUniqueInstance();
    }
}
