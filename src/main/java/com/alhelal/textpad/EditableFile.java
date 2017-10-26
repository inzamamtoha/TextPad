package com.alhelal.textpad;

import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class EditableFile
{
    public LanguageBehavior languageBehavior;
    public Options options;

    public EditableFile(Options option)
    {
        options = option;
    }

    public EditableFile()
    {
    }


    public CodeArea getCodeAreaFromTab(Tab tb)
    {
        Node nd = tb.getContent();
        VirtualizedScrollPane<CodeArea> cd = (VirtualizedScrollPane<CodeArea>) (nd);
        return cd.getContent();
    }

    public void saveFile()
    {
        System.out.println("in saveFile");
        FileChooser savefile = new FileChooser();
        savefile.setTitle("Open File");
        //savefile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Assembly Files", "*.asm"));
        //I change this portion
        System.out.println("saveFile called");
        Stage stage = new Stage();
        File file = savefile.showSaveDialog(options.stage);
        // Set the new title of the window
        // setTitle(file.getName() + " | " + SimpleJavaTextEditor.NAME);
        // Create a buffered writer to write to a file
        try
        {
            //BufferedWriter out = new BufferedWriter(new FileWriter(file.getPath()));
            FileWriter fileWriter = new FileWriter(new File(file.getPath()));
            //System.out.println(file.getPath());
            // Write the contents of the CodeArea to the file
            options.centerPane.getSelectionModel().getSelectedItem().setText(file.getName());
            options.output = getCodeAreaFromTab(options.centerPane.getSelectionModel().getSelectedItem());
            fileWriter.write(options.output.getText());
            // Close the file stream
            fileWriter.flush();
            fileWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("file is not saved, save button/option failed");
        }
    }

    public void printFile(Node node)
    {
        PrinterJob printer = PrinterJob.createPrinterJob();

        if (printer != null && printer.showPrintDialog(options.stage))
        {
            boolean success = printer.printPage(node);

            if (success)
            {
                printer.endJob();
            }
        }
    }

    public void toggleLineNumber(CodeArea cd)
    {
        if (options.lineNumber)
        {
            cd.setParagraphGraphicFactory(null);
            options.lineNumber = false;
        }
        else
        {
            cd.setParagraphGraphicFactory(LineNumberFactory.get(cd));
            options.lineNumber = true;
        }
    }

    public void setLanguageBehavior(com.alhelal.textpad.LanguageBehavior langBehavior)
    {
        languageBehavior = langBehavior;
    }

    public void performRunCode(File file)
    {
        languageBehavior.runCode(file);
    }

    public void performBuildCode(File file)
    {
        languageBehavior.buildCode(file);
    }

    public void performSetHighlightableText()
    {
        languageBehavior.setHighlightableText();
    }

    public void performSetAutocompletableText()
    {
        languageBehavior.setAutoCompletableText();
    }
}
