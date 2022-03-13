package com.example.cmpt370group41;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AirportManagementSystem extends Application {
    @Override
    public void start(Stage stage){
        StackPane root = new StackPane();
        Scene scene = new Scene(root);
        stage.setTitle("Airport Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}