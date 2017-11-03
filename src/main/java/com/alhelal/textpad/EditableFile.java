/*
* @author : alhelal
* */

package com.alhelal.textpad;

import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EditableFile
{
    public LanguageBehavior languageBehavior;
    public Options options;
    File file;
    Tab tab;

    public EditableFile()
    {
    }

    public void saveFile(ArrayList<EditableFile> editableFileArrayList)
    {
        File file;
        String filePath;
        System.out.println("in saveFile");
        //savefile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Assembly Files", "*.asm"));
        //I change this portion
        System.out.println("saveFile called");
        if (options == null)
        {
            System.out.println("Optin is null");
        }
        if (languageBehavior == null)
        {
            System.out.println("languageBehavior is null");
        }
        Tab tb = options.centerPane.getSelectionModel().getSelectedItem();
        String fileName = options.centerPane.getSelectionModel().getSelectedItem().getText();
        int i = 0;
        while (!editableFileArrayList.get(i).tab.equals(tb))
        {
            i++;
        }
        if (editableFileArrayList.get(i).file == null)
        {
            FileChooser savefile = new FileChooser();
            savefile.setTitle("Open File");
            Stage stage = new Stage();
            file = savefile.showSaveDialog(stage);
            editableFileArrayList.get(i).file = file;
            options.centerPane.getSelectionModel().getSelectedItem().setText(file.getName());
        }
        else
        {
            file = editableFileArrayList.get(i).file;
        }
        // Set the new title of the window
        // setTitle(file.getName() + " | " + SimpleJavaTextEditor.NAME);
        // Create a buffered writer to write to a file
        try
        {
            //BufferedWriter out = new BufferedWriter(new FileWriter(file.getPath()));
            FileWriter fileWriter = new FileWriter(file);
            //System.out.println(file.getPath());
            // Write the contents of the CodeArea to the file
            options.output = new Actions().getCodeAreaFromTab(options.centerPane.getSelectionModel().getSelectedItem());
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

    public void performRunCode(ArrayList<EditableFile> editableFileArrayList)
    {
        saveFile(editableFileArrayList);
        Tab tb = options.centerPane.getSelectionModel().getSelectedItem();
        int i = 0;
        while (!editableFileArrayList.get(i).tab.equals(tb))
        {
            i++;
        }
        LanguageBehavior lb = editableFileArrayList.get(i).languageBehavior;
        File runFile = editableFileArrayList.get(i).file;
        BufferedReader stdInput = lb.runCode(runFile);
        String string;
        options.outputText.setText(null);
        try
        {
            if (stdInput != null)
            {
                while ((string = stdInput.readLine()) != null)
                {
                    if (options == null)
                    {
                        System.out.println("option is null, so");
                    }
                    else
                    {
                        options.outputText.appendText(string);
                    }
                }
            }
        }
        catch (IOException io)
        {
            System.out.println(io);
        }
    }

    public void performBuildCode(ArrayList<EditableFile> editableFileArrayList)
    {
        saveFile(editableFileArrayList);
        Tab tb = options.centerPane.getSelectionModel().getSelectedItem();
        int i=0;
        while (!editableFileArrayList.get(i).tab.equals(tb))
        {
            i++;
        }
        LanguageBehavior lb = editableFileArrayList.get(i).languageBehavior;
        File runFile = editableFileArrayList.get(i).file;
        lb.runCode(runFile);
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
