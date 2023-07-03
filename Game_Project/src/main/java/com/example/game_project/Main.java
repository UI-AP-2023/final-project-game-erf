package com.example.game_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Stage primaryStage;
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1366 , 763);
        stage.setTitle("Main Page");
        stage.setScene(scene);
        primaryStage=stage;
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}