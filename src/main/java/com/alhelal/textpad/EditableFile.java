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
import java.util.ArrayList;

public class EditableFile
{
    public LanguageBehavior languageBehavior;
    public Options options;
    public CodeArea getCodeAreaFromTab(Tab tb)
    {
        Node nd = tb.getContent();
        VirtualizedScrollPane<CodeArea> cd = (VirtualizedScrollPane<CodeArea>) (nd);
        return cd.getContent();
    }

    public void saveFile(ArrayList<FileTab> fileTabArrayList, Options option)
    {
        File file;
        String filePath;
        System.out.println("in saveFile");
        //savefile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Assembly Files", "*.asm"));
        //I change this portion
        System.out.println("saveFile called");
        if (option == null)
        {
            System.out.println("Optin is null");
        }
        if (languageBehavior == null)
        {
            System.out.println("languageBehavior is null");
        }
        Tab tb = option.centerPane.getSelectionModel().getSelectedItem();
        String fileName = option.centerPane.getSelectionModel().getSelectedItem().getText();
        int i=0;
        while (fileTabArrayList.get(i).tab.equals(tb) == false)
        {
            i++;
        }
        if(fileTabArrayList.get(i).filePath == null)
        {
            FileChooser savefile = new FileChooser();
            savefile.setTitle("Open File");
            Stage stage = new Stage();
            file = savefile.showSaveDialog(stage);
            filePath = file.getPath();
            fileTabArrayList.get(i).filePath = filePath;
            option.centerPane.getSelectionModel().getSelectedItem().setText(file.getName());
        }
        else
        {
            filePath = fileTabArrayList.get(i).filePath;
        }
        // Set the new title of the window
        // setTitle(file.getName() + " | " + SimpleJavaTextEditor.NAME);
        // Create a buffered writer to write to a file
        try
        {
            //BufferedWriter out = new BufferedWriter(new FileWriter(file.getPath()));
            FileWriter fileWriter = new FileWriter(new File(filePath));
            //System.out.println(file.getPath());
            // Write the contents of the CodeArea to the file
            option.output = getCodeAreaFromTab(option.centerPane.getSelectionModel().getSelectedItem());
            fileWriter.write(option.output.getText());
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
