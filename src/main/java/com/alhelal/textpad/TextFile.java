package com.alhelal.textpad;

import javafx.scene.control.Tab;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;

import java.io.File;

public class TextFile extends EditableFile
{
    String keywordsPath = "src/main/java/com/alhelal/resource/Dictionary";
    public TextFile(File file, Options options, Tab tab)
    {
        this.file = file;
        this.options = options;
        this.tab = tab;
        //this.tab.setContent(new VirtualizedScrollPane<CodeArea>(new NormalTextEditor(this.options.stage, keywordsPath).getEditArea()));
        languageBehavior = TextFileBehavior.getUniqueInstance();
    }
}
