/*
* @author : alhelal
* */

package com.alhelal.textpad;

import javafx.event.Event;
import javafx.scene.Node;
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
import java.util.ArrayList;

public class Actions
{
    public Options options;
    Stage primaryStage;
    ArrayList<EditableFile> editableFilesArrayList;
    private EditableFile editableFile;

    public Actions()
    {
    }

    public Actions(Options options, Stage primaryStage)
    {
        if (options == null)
        {
            System.out.println("option is null");
        }
        if (primaryStage == null)
        {
            System.out.println(primaryStage);
        }
        this.primaryStage = primaryStage;
        this.options = options;
        editableFilesArrayList = new ArrayList<>();
        newFile();
    }

    public void setActions()
    {
        CLanguageBehavior cLanguageBehavior = CLanguageBehavior.getUniqueInstance();
        options.btnNew.setOnAction(evt -> newFile());
        options.btnFullScreen.setOnAction(evt -> toggleFullScreen());
        options.btnOpen.setOnAction(evt -> openFile());
        options.btnSave.setOnAction(evt -> editableFile.saveFile(editableFilesArrayList));
        options.btnRun.setOnAction(evt -> editableFile.performRunCode(editableFilesArrayList));
        options.btnBuild.setOnAction(evt -> editableFile.performBuildCode(editableFilesArrayList));
        options.menuPrint.setOnAction(evt -> editableFile.printFile(new TextArea(getCodeAreaFromTab(
                options.centerPane.getSelectionModel().getSelectedItem()).getText())));

        options.menuFullScreen.setOnAction(evt -> toggleFullScreen());
        options.btnSuggest.setOnAction(evt -> cLanguageBehavior.suggest());

        options.menuShowLineNumber.setOnAction(evt -> editableFile.toggleLineNumber(getCodeAreaFromTab(
                options.centerPane.getSelectionModel().getSelectedItem())));

        options.menuExit.setOnAction(evt -> System.exit(0));
        options.menuNew.setOnAction(evt -> newFile());
        options.menuSave.setOnAction(evt -> editableFile.saveFile(editableFilesArrayList));
        options.menuOpen.setOnAction(evt -> openFile());
    }

    public CodeArea getCodeAreaFromTab(Tab tb)
    {
        Node nd = tb.getContent();
        VirtualizedScrollPane<CodeArea> cd = (VirtualizedScrollPane<CodeArea>) (nd);
        return cd.getContent();
    }

    private void openFile()
    {
        FileChooser openfile = new FileChooser();
        openfile.setTitle("Open File");
        File file = openfile.showOpenDialog(options.stage);
        if (file != null)
        {
            CodeArea cd = getCodeAreaFromTab(options.centerPane.getSelectionModel().getSelectedItem());
            Tab tb = options.centerPane.getSelectionModel().getSelectedItem();
            int i = 0;
            while (editableFilesArrayList.get(i).tab.equals(tb) == false)
            {
                i++;
            }
            try
            {
                cd.replaceText(new String(Files.readAllBytes(Paths.get(file.getPath()))));
                options.centerPane.getSelectionModel().getSelectedItem().setText(file.getName());
                String name = file.getName();
                FileFactory fileFactory = new FileFactory();
                FileBox fileBox = new FileBox(fileFactory);
                editableFilesArrayList.add(i, fileBox.orderFile(file, options, tb));

            }
            catch (Exception io)
            {
                 System.out.println(io);
            }
        }
        System.out.println("open file success");
        if (editableFilesArrayList == null)
        {
            System.out.println("editableFilesArrayList is null");
        }
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

    private void newFile()
    {
        Tab newTab = addTab();
        options.centerPane.getTabs().add(newTab);
        options.centerPane.getSelectionModel().select(newTab);
        getCodeAreaFromTab(options.centerPane.getSelectionModel().getSelectedItem()).requestFocus();
    }

    private Tab addTab()
    {
        String keywordsPath = "src/main/java/com/alhelal/resource/Keywords";
        ProgramEditor ed = new ProgramEditor(primaryStage, keywordsPath);
        String fileName = "*Untitled";
        Tab tab = new Tab(fileName,
                new VirtualizedScrollPane<CodeArea>(ed.getEditArea()));
        tab.setOnSelectionChanged((Event event) -> {
            if (tab.isSelected())
            {
                getCodeAreaFromTab(tab).requestFocus();
            }
        });
        FileFactory fileFactory = new FileFactory();
        FileBox fileBox = new FileBox(fileFactory);
        editableFile = fileBox.orderFile(null, options, tab);
        editableFilesArrayList.add(editableFile);

        return tab;
    }
}
