package com.alhelal.textpad;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;

/**
 * @author alhelal
 */
public class Options
        //public class TextPad extends Application {
{
    public volatile static Options uniqueInstance;

    public final BorderPane mainPane;
    public final TabPane centerPane;
    public final ToolBar toolbar;
    public final VBox bottomPane;
    public final TitledPane outputWindow;
    public final HBox StatusBar;
    public final Button btnNew;
    public final Button btnOpen;
    public final Button btnSave;
    public final Button btnCut;
    public final Button btnCopy;
    public final Button btnPaste;
    public final Button btnDelete;
    public final Button btnUndo;
    public final VBox topPane;
    public final Button btnAssemble;
    public final Button btnRun;
    public final ToggleButton btnFullScreen;
    public final Button btnRedo;
    public final Button btnSaveAll;
    public final MenuBar mainMenu;
    public final Menu fileMenu;
    public final Menu editMenu;
    public final Menu viewMenu;
    public final Menu runMenu;
    public final Menu toolsMenu;
    public final Menu helpMenu;
    public final MenuItem menuPrint;
    public final MenuItem menuNew;
    public final MenuItem menuOpen;
    public final MenuItem menuSave;
    public final MenuItem menuSaveAs;
    public final MenuItem menuSaveAll;
    public final MenuItem menuExit;
    public final MenuItem menuCopy;
    public final MenuItem menuCut;
    public final MenuItem menuUndo;
    public final MenuItem menuPaste;
    public final MenuItem menuDelete;
    public final MenuItem menuSelectAll;
    public final MenuItem menuFindSelected;
    public final MenuItem menuRedo;
    public final MenuItem menuFind;
    public final MenuItem menuReplace;
    public final CheckMenuItem menuFullScreen;
    public final CheckMenuItem menuShowLineNumber;
    public final MenuItem menuRun;
    public final MenuItem menuAssemble;
    public final MenuItem menuSettings;
    public final MenuItem menuOpenInTerminal;
    public final MenuItem menuAbout;
    public final Label StatusBarText;
    public final TextField txtFind;
    public final HBox findBox;
    public Scene scene;
    public CodeArea output;
    public Stage stage;
    public boolean fullScreen;
    public boolean lineNumber;

    //    public TextPad() {
    private Options()
    {
        fullScreen = false;
        lineNumber = true;
        mainMenu = new MenuBar();
        // mainMenu.getStylesheets().add(
        //       this.getClass().getResource("/com/alhelal/resource/toolbar_style.css").toExternalForm());
        mainMenu.getStylesheets().add(this.getClass().getResource("../resource/toolbar_style.css").toExternalForm());
        fileMenu = new Menu("File");

        editMenu = new Menu("Edit");
        viewMenu = new Menu("View");
        runMenu = new Menu("Run");
        toolsMenu = new Menu("Tools");
        helpMenu = new Menu("Help");

        menuPrint = new MenuItem("Print           ");
        menuPrint.setId("menuPrint");
        menuPrint.setAccelerator(new KeyCodeCombination(KeyCode.P, KeyCombination.CONTROL_DOWN));

        menuNew = new MenuItem("New File       ");
        menuNew.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));

        menuOpen = new MenuItem("Open File        ");
        menuOpen.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));

        menuSaveAs = new MenuItem("Save File As   ");

        menuSave = new MenuItem("Save           ");
        menuSave.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));

        menuSaveAll = new MenuItem("Save All   ");
        menuSaveAll.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN,
                KeyCombination.SHIFT_DOWN));

        menuExit = new MenuItem("Exit           ");
        menuExit.setAccelerator(new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN));

        menuCopy = new MenuItem("Copy           ");
        menuCopy.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));

        menuCut = new MenuItem("Cut      ");
        menuCut.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));

        menuPaste = new MenuItem("Paste        ");
        menuPaste.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN));

        menuFindSelected = new MenuItem("Find Selection        ");
        menuFindSelected.setAccelerator(new KeyCodeCombination(KeyCode.F3, KeyCombination.CONTROL_DOWN));

        menuDelete = new MenuItem("Delete           ");
        menuDelete.setAccelerator(new KeyCodeCombination(KeyCode.DELETE, KeyCombination.CONTROL_ANY));

        menuSelectAll = new MenuItem("Select All   ");
        menuSelectAll.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));

        menuFind = new MenuItem("Find           ");
        menuFind.setAccelerator(new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN));

        menuReplace = new MenuItem("Replace           ");
        menuReplace.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN));

        menuUndo = new MenuItem("Undo           ");
        menuUndo.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));

        menuRedo = new MenuItem("Redo           ");
        menuRedo.setAccelerator(new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN));

        menuFullScreen = new CheckMenuItem("View Full Screen     ");
        menuFullScreen.setAccelerator(new KeyCodeCombination(KeyCode.F11, KeyCombination.CONTROL_ANY));

        menuRun = new MenuItem("Run            ");
        menuRun.setAccelerator(new KeyCodeCombination(KeyCode.F9, KeyCombination.CONTROL_ANY));

        menuAssemble = new MenuItem("Assemble           ");
        menuAssemble.setAccelerator(new KeyCodeCombination(KeyCode.F10, KeyCombination.CONTROL_ANY));

        menuSettings = new MenuItem(" Preferences             ");

        menuShowLineNumber = new CheckMenuItem("Show Line Number     ");
        menuShowLineNumber.setSelected(true);

        menuOpenInTerminal = new MenuItem(" Open in Terminal        ");
        menuAbout = new MenuItem("  About                ");


        mainPane = new BorderPane();
        topPane = new VBox();
        centerPane = new TabPane();
        toolbar = new ToolBar();
        toolbar.getStylesheets().add(
                getClass().getResource("/com/alhelal/resource/toolbar_style.css").toExternalForm());
        bottomPane = new VBox();
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setStyle("-fx-width: 100%");

        outputWindow = new TitledPane("Log",
                new TextArea("Once upon a time \nthere many uninhabited lands"));
        outputWindow.setExpanded(false);
        outputWindow.setStyle("-fx-width: 100%");

        StatusBar = new HBox();
        StatusBar.setPadding(new Insets(10, 10, 10, 10));

        StatusBarText = new Label("Ready");

        btnNew = new Button();
        btnNew.setId("new");
        btnNew.setPrefSize(28, 28);
        btnNew.setTooltip(new Tooltip("New File(Ctrl+N)"));

        btnOpen = new Button();
        btnOpen.setId("open");
        btnOpen.setPrefSize(28, 28);
        btnOpen.setTooltip(new Tooltip("Open File(Ctrl+O)"));

        btnSave = new Button();
        btnSave.setId("save");
        btnSave.setPrefSize(28, 28);
        btnSave.setTooltip(new Tooltip("Save File(Ctrl+O)"));

        btnSaveAll = new Button();
        btnSaveAll.setId("save_all");
        btnSaveAll.setPrefSize(28, 28);
        btnSaveAll.setTooltip(new Tooltip("Save All Files(Ctrl+Shift+S)"));
        btnSaveAll.setPadding(new Insets(0, 20, 0, 20));

        btnCut = new Button();
        btnCut.setId("cut");
        btnCut.setPrefSize(28, 28);
        btnCut.setTooltip(new Tooltip("Cut(Ctrl+X)"));

        btnCopy = new Button();
        btnCopy.setId("copy");
        btnCopy.setPrefSize(28, 28);
        btnCopy.setTooltip(new Tooltip("Copy(Ctrl+C)"));

        btnPaste = new Button();
        btnPaste.setId("paste");
        btnPaste.setPrefSize(28, 28);
        btnPaste.setTooltip(new Tooltip("Paste(Ctrl+V)"));

        btnDelete = new Button();
        btnDelete.setId("delete");
        btnDelete.setPrefSize(28, 28);
        btnDelete.setTooltip(new Tooltip("Delete(Del)"));

        btnUndo = new Button();
        btnUndo.setId("undo");
        btnUndo.setPrefSize(28, 28);
        btnUndo.setTooltip(new Tooltip("Undo(Ctrl+Z)"));

        btnRedo = new Button();
        btnRedo.setId("redo");
        btnRedo.setPrefSize(28, 28);
        btnRedo.setTooltip(new Tooltip("Redo(Ctrl+Y)"));

        btnAssemble = new Button();
        btnAssemble.setId("assemble");
        btnAssemble.setPrefSize(28, 28);
        btnAssemble.setTooltip(new Tooltip("Assemble File(F10)"));

        btnRun = new Button();
        btnRun.setId("run");
        btnRun.setPrefSize(28, 28);
        btnRun.setTooltip(new Tooltip("Run File(F9)"));

        btnFullScreen = new ToggleButton();
        btnFullScreen.setId("full_screen");
        btnFullScreen.setPrefSize(28, 28);
        btnFullScreen.setTooltip(new Tooltip("Full Screen(F11)"));

        txtFind = new TextField();
        txtFind.setPromptText("Find Text(Ctrl + F)");
        txtFind.setId("txtFind");

        findBox = new HBox();
        findBox.setAlignment(Pos.CENTER_RIGHT);


        fileMenu.getItems().addAll(menuNew, new SeparatorMenuItem(), menuOpen,
                new SeparatorMenuItem(), menuSave, menuSaveAs, menuSaveAll,
                new SeparatorMenuItem(), menuPrint, new SeparatorMenuItem(), menuExit);

        editMenu.getItems().addAll(menuUndo, menuRedo, new SeparatorMenuItem(), menuCut,
                menuCopy, menuPaste, menuDelete, new SeparatorMenuItem(), menuSelectAll,
                new SeparatorMenuItem(), menuFind, menuFindSelected, menuReplace);

        viewMenu.getItems().addAll(menuShowLineNumber, menuFullScreen);
        runMenu.getItems().addAll(menuRun, menuAssemble);
        toolsMenu.getItems().addAll(menuOpenInTerminal, menuSettings);
        helpMenu.getItems().add(menuAbout);

        //findBox.getChildren().add(txtFind);
        HBox.setHgrow(findBox, Priority.ALWAYS);
        topPane.getChildren().addAll(mainMenu, toolbar);
        mainMenu.getMenus().addAll(fileMenu, editMenu, viewMenu, runMenu, toolsMenu, helpMenu);
        toolbar.getItems().addAll(btnNew, btnOpen, btnSave, btnSaveAll, new Separator(),
                btnCopy, btnPaste, btnCut, btnDelete, new Separator(),
                btnUndo, btnRedo, new Separator(),
                btnAssemble, btnRun, new Separator(),
                btnFullScreen, findBox, txtFind);


        StatusBar.getChildren().add(StatusBarText);
        bottomPane.getChildren().addAll(outputWindow, StatusBar);


        mainPane.setCenter(centerPane);
        mainPane.setTop(topPane);
        mainPane.setBottom(bottomPane);


    }

    public static Options getUniqueInstance()
    {
        if (uniqueInstance == null)
        {
            synchronized (Options.class)
            {
                if (uniqueInstance == null)
                {
                    uniqueInstance = new Options();
                }
            }
        }
        return uniqueInstance;
    }
}
