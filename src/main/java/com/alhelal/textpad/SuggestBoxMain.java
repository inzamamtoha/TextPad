package com.alhelal.textpad;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * <pre>
 *     author: Md.Inzamam-Ul-Haque
 * </pre>
 */


public class SuggestBoxMain
{

    TabPane tabPane = new TabPane();
    BorderPane mainPane = new BorderPane();
    ListView<String> list = new ListView<>();
    ObservableList<String> data = FXCollections.observableArrayList();
    WebParse webParseob = new WebParse("stackoverflow");

    public void initActions()
    {
        //Detecting mouse clicked
        list.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent arg0)
            {
                //Check wich list index is selected then set txtContent value for that index
                String tabName = "";
                for (int i = 0; i < webParseob.questionLinks.size(); i++)
                {
                    if (list.getSelectionModel().getSelectedIndex() == i)
                    {
                        System.out.println("Selected index: " + i);
                        Tab tabA = new Tab();
                        tabName = "StackOverflow";
                        tabA.setText(tabName);
                        //Add something in Tab
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("WebUI.fxml"));
                        WebUIController wb = new WebUIController(webParseob.questionLinks.get(i));
                        loader.setController(wb);
                        try
                        {

                            //tabA.setContent(FXMLLoader.load(getClass().getResource("WebUI.fxml")));
                            tabA.setContent(loader.load());
                        }
                        catch (Exception e)
                        {

                        }
                        tabPane.getTabs().add(tabA);
                    }
                }
            }

        });
    }


    public void load() throws IOException
    {

        Stage primaryStage = new Stage();
        primaryStage.setTitle("SuggestionBox");
        Group root = new Group();
        Scene scene = new Scene(root, 1200, 600, Color.BLACK);

        webParseob.searchResult("semicolon error in c");
        //Create Tabs
        Tab tabSuggestion = new Tab();
        tabSuggestion.setText("Suggestions");
        //Add something in Tab
        StackPane sp = new StackPane();
        list.setItems(data);
        for (int i = 0; i < webParseob.questions.size(); i++)
        {
            data.add(webParseob.questions.get(i));
        }

        initActions();//call for mouse Actions

        sp.getChildren().add(list);
        tabSuggestion.setContent(sp);
        tabPane.getTabs().add(tabSuggestion);

        mainPane.setCenter(tabPane);
        mainPane.prefHeightProperty().bind(scene.heightProperty());
        mainPane.prefWidthProperty().bind(scene.widthProperty());

        root.getChildren().add(mainPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
