/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alhelal.textpad;

import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

//import java.nio.file.Files;
//import java.nio.file.Paths;

/**
 * @author alhelal
 */
public class TextPad extends Application
{
    File file;
    private Options options;
    private EditableFile editableFile;

    public TextPad()
    {
        options = new Options();
        editableFile = new TextFile(options);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(Stage primaryStage)
    {
        options.btnNew.setOnAction(evt -> newFile(primaryStage));
        options.btnFullScreen.setOnAction(evt -> toggleFullScreen());
        options.btnOpen.setOnAction(evt -> openFile());
        options.btnSave.setOnAction(evt -> editableFile.saveFile());
        options.btnRun.setOnAction(evt -> editableFile.performRunCode(file));
        options.menuPrint.setOnAction(evt -> editableFile.printFile(new TextArea(editableFile.getCodeAreaFromTab(
                options.centerPane.getSelectionModel().getSelectedItem()).getText())));

        options.menuFullScreen.setOnAction(evt -> toggleFullScreen());
        options.menuShowLineNumber.setOnAction(evt -> editableFile.toggleLineNumber(editableFile.getCodeAreaFromTab(
                options.centerPane.getSelectionModel().getSelectedItem())));

        options.menuExit.setOnAction(evt -> System.exit(0));
        options.menuNew.setOnAction(evt -> newFile(primaryStage));
        options.menuSave.setOnAction(evt -> editableFile.saveFile());
        options.menuOpen.setOnAction(evt -> openFile());


        options.scene = new Scene(options.mainPane, 800, 600);

        primaryStage.setScene(options.scene);
        //scene.getStylesheets().add(
        //      getClass().getResource("/com/alhelal/resource/main_style.css").toExternalForm());
        primaryStage.setTitle("TextPad");
        primaryStage.show();
        newFile(primaryStage);
        options.stage = primaryStage;

        //primaryStage.setOnHiding(evt -> intellisense.hide());
    }

    private Tab addTab(Stage primaryStage)
    {

        Editor ed = new Editor(primaryStage);
        Tab tb = new Tab("*Untitled",
                new VirtualizedScrollPane<CodeArea>(ed.getEditArea()));
        tb.setOnSelectionChanged((Event event) -> {
            if (tb.isSelected())
            {
                editableFile.getCodeAreaFromTab(tb).requestFocus();
            }
        });


        return tb;
    }

    private void newFile(Stage primaryStage)
    {
        Tab newTab = addTab(primaryStage);
        options.centerPane.getTabs().add(newTab);
        options.centerPane.getSelectionModel().select(newTab);
        editableFile.getCodeAreaFromTab(options.centerPane.getSelectionModel().getSelectedItem()).requestFocus();
    }

    private void toggleFullScreen()
    {
        if (!options.fullScreen)
        {
            options.btnFullScreen.setSelected(true);
            options.fullScreen = true;
            options.btnFullScreen.setTooltip(new Tooltip("Close Full Screen(F11)"));
            options.stage.setFullScreen(true);
        }
        else
        {
            options.btnFullScreen.setSelected(false);
            options.fullScreen = false;
            options.btnFullScreen.setTooltip(new Tooltip("Full Screen(F11)"));
            options.stage.setFullScreen(false);
        }
    }

    private void openFile()
    {
        FileChooser openfile = new FileChooser();
        openfile.setTitle("Open File");
        //openfile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Assembly Files", "*.asm"));
        File file = openfile.showOpenDialog(options.stage);
        String path = file.getPath();
        if (file != null)
        {
            CodeArea cd = editableFile.getCodeAreaFromTab(options.centerPane.getSelectionModel().getSelectedItem());
            //cd.replaceText(FileUtils.readFiletoString(new File(path), "UTF-8"));

            try
            {
                cd.replaceText(new String(Files.readAllBytes(Paths.get(path))));
                options.centerPane.getSelectionModel().getSelectedItem().setText(file.getName());
                String name = file.getName();
                //String[] splited;
                //System.out.println("filename = " + name);
                //= new String[2];
                //splited = name.split(".");
                //System.out.println("length =" + splited.length);
                //String extension = FilenameUtils.getExtension(name);
                //splited[0];
                SimpleFileFactory simpleFileFactory = new SimpleFileFactory();
                FileBox fileBox = new FileBox(simpleFileFactory);
                this.file = file;
                editableFile = fileBox.orderFile(file);

                //editableFile = new FileBox(simpleFileFactory);

               /* if (file.getName().endsWith("java"))
                {
                    editableFile = new JavaFile();
                    System.out.println("java file detected");
                }
                else if (file.getName().endsWith("c"))
                    editableFile = new CFile();
                else if (file.getName().endsWith("cpp"))
                    editableFile = new CplusFile();
                else if (file.getName().endsWith("py"))
                    editableFile = new PythonFile();
                else
                    editableFile = new TextFile(options);*/
                // / editableFile.setLanguageBehavior(new JavaLanguageBehavior());
            }
            catch (Exception io)
            {
                //   System.out.println(io);
            }

            //CodeArea cd = new CodeArea();
        }
    }
}

