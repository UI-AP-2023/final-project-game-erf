package com.example.game_project;

import com.example.Controller.PlayerController;
import com.example.Exception.InvalidPassword;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class SignUpController {

    @FXML
    private AnchorPane ach_SignUp;

    @FXML
    private Button btn_Back;

    @FXML
    private Button btn_Submit;

    @FXML
    private ImageView img_SignUp;

    @FXML
    private TextField txt_Password;

    @FXML
    private TextField txt_UserName;

    public static String userName;

    public static String Password;

    @FXML
    void SignUp(ActionEvent event) throws IOException {

        PlayerController playerController=new PlayerController();
        String username=txt_UserName.getText();
        String password=txt_Password.getText();
        try {

            String result=playerController.signUp(username, password);

            if (result.equals("This account already exists you can login now!"))
            {
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setTitle("This account already exists!!");
                alert.setContentText("This account already exists you can login now!");
                alert.show();

                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginPage.fxml"));
                Scene scene = new Scene(fxmlLoader.load(),1366 , 763);
                Main.primaryStage.setTitle("Login Page");
                Main.primaryStage.setScene(scene);
                Main.primaryStage.show();

            }

            if (result.equals("Sign up was successfully"))
            {
                userName=username;
                Password=password;
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShowAllMaps.fxml"));
                Scene scene = new Scene(fxmlLoader.load(),1299 , 763);
                Main.primaryStage.setTitle("AllMaps Page");
                Main.primaryStage.setScene(scene);
                Main.primaryStage.show();

                //-------------------------------------------------------------------remmmmmmmm
            }

            if (result.equals("This username already exists"))
            {
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Duplicate Information");
                alert.setContentText("This username already exists please enter another one");
                alert.show();
            }

        } catch (ClassNotFoundException e) {

            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Could not sign up!");
            alert.setContentText("Could not sign up caused by class not found Error!!");
            alert.show();

        } catch (SQLException e) {

            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Could not sign up!");
            alert.setContentText("Could not sign up caused by SQlException!!");
            alert.show();

        } catch (InvalidPassword e) {

            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Could not sign up!");
            alert.setContentText("Your password is invalid please enter another password");
            alert.show();

        }
    }

    @FXML
    void back(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1366 , 763);
        Main.primaryStage.setTitle("Main Page");
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }

}
