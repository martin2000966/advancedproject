package com.example.project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //creating pane
        GridPane p=new GridPane();
        p.setAlignment(Pos.CENTER);
        p.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        p.setHgap(5.5);
        p.setVgap(5.5);


        //add label
        Label l=new Label("Choose a file:");
        p.add(l,0,0);
        GridPane.setHalignment(l, HPos.CENTER);

        //add buttons
        Button btOK = new Button("OK");
        p.add(btOK,0,1);
        GridPane.setHalignment(btOK, HPos.CENTER);

        //handler for btOK
        btOK.setOnAction((ActionEvent e)->{
          FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");

            File f=fileChooser.showOpenDialog(stage);

            // check if the user has selected a file
            if (f != null) {
                // get the path of the selected file
                String filePath = f.getPath();
                System.out.println("Selected file path: " + filePath);
            }
            else
                System.out.println("No file selected!");



        
        });

        //creating scene
        Scene scene = new Scene(p, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();



    }



    public static void main(String[] args) {
        launch();
    }
}