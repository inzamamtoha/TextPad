/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alhelal.textpad;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

//import java.nio.file.Files;
//import java.nio.file.Paths;

/**
 * @author alhelal
 */
public class TextPad extends Application
{
    File file;
    private Options options;

    public TextPad()
    {
        options = Options.getUniqueInstance();

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
        primaryStage.setTitle("TextPad");
        options.scene = new Scene(options.mainPane, 800, 600);
        primaryStage.setScene(options.scene);
        options.stage = primaryStage;
        Actions actions = new Actions(options, primaryStage);
        actions.setActions();
        //scene.getStylesheets().add(
        //      getClass().getResource("/com/alhelal/resource/main_style.css").toExternalForm());
        primaryStage.show();
        //primaryStage.setOnHiding(evt -> intellisense.hide());
    }
}
