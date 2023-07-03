package com.example.game_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainController {

    @FXML
    private AnchorPane anch_Main;

    @FXML
    private Button btn_SignUp;

    @FXML
    private Button btn_SignUp1;

    @FXML
    private ImageView img_Main;

    @FXML
    void Login(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1366 , 763);
        Main.primaryStage.setTitle("Login Page");
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();

    }

    @FXML
    void signUp(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SignUpPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1366 , 763);
        Main.primaryStage.setTitle("SignUp Page");
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }

}
