package com.example.game_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class UserPageController {

    @FXML
    private AnchorPane anch_UserPage;

    @FXML
    private Button btn_Back;

    @FXML
    private ImageView img_Attack;

    @FXML
    private ImageView img_Background;

    @FXML
    private ImageView img_Heroes;

    @FXML
    private ImageView img_Map;

    @FXML
    private ImageView img_Profile;

    @FXML
    void attack(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Attack.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1366 , 763);
        Main.primaryStage.setTitle("Attack");
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }

    @FXML
    void back(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1366 , 763);
        Main.primaryStage.setTitle("Main Page");
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }

    @FXML
    void showHeroes(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Heroes.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1366 , 763);
        Main.primaryStage.setTitle("Heroes Page");
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();

    }

    @FXML
    void showMap(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MyMap.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1366 , 766);
        Main.primaryStage.setTitle("Map");
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();

    }

    @FXML
    void showProfile(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Profile.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1372 , 763);
        Main.primaryStage.setTitle("Profile");
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }

}
