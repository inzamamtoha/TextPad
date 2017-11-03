package com.alhelal.textpad;

import javafx.event.Event;
import javafx.scene.control.Tab;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;

import java.io.File;

public class CFile extends EditableFile
{
    String keywordsPath = "src/main/java/com/alhelal/resource/CKeywords";
    public CFile(File file, Options options, Tab tab)
    {
        this.file = file;
        this.options = options;
        this.tab = tab;
        /*VirtualizedScrollPane<CodeArea> virtualizedScrollPane = new VirtualizedScrollPane<CodeArea>(new ProgramEditor(this.options.stage, keywordsPath).getEditArea());
        if (virtualizedScrollPane == null)
        {
            System.out.println("virtualizedScrollPane is null");
        }
        else
        {
            System.out.println("virtualizedScrollPane is not null");
        }*/
        //this.tab.setContent(virtualizedScrollPane);

        /*this.tab.setContent(new VirtualizedScrollPane<CodeArea>(new ProgramEditor(this.options.stage, keywordsPath).getEditArea()));
        this.tab.setOnSelectionChanged((Event event) -> {
            if (tab.isSelected())
            {
                new Actions().getCodeAreaFromTab(tab).requestFocus();
            }
        });*/
        languageBehavior = CLanguageBehavior.getUniqueInstance();
    }
}
