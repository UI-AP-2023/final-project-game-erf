package com.example.game_project;

import com.example.Controller.PlayerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {

    @FXML
    private AnchorPane anch_Login;

    @FXML
    private Button btn_Back;

    @FXML
    private ImageView img_Login;

    @FXML
    private ImageView img_Sharingan;

    @FXML
    private ImageView img_normal;

    @FXML
    private PasswordField psTxt_Password;

    @FXML
    private TextField txt_Password;

    @FXML
    private TextField txt_Username;
    @FXML
    private Button btn_Submit;


    @FXML
    void back(ActionEvent event) throws IOException {

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),1366 , 763);
            Main.primaryStage.setTitle("Main Page");
            Main.primaryStage.setScene(scene);
            Main.primaryStage.show();
    }

    @FXML
    void hidePassword(MouseEvent event) {

        psTxt_Password.setText(txt_Password.getText());
        txt_Password.setVisible(false);
        psTxt_Password.setVisible(true);
        img_Sharingan.setVisible(false);
        img_normal.setVisible(true);

    }

    @FXML
    void showPassword(MouseEvent event) {
        txt_Password.setText(psTxt_Password.getText());
        psTxt_Password.setVisible(false);
        txt_Password.setVisible(true);
        img_Sharingan.setVisible(true);
        img_normal.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        img_Sharingan.setVisible(false);
        img_normal.setVisible(true);
        txt_Password.setVisible(false);
        psTxt_Password.setVisible(true);
    }

    @FXML
    void login(ActionEvent event) throws IOException {

        try {
            boolean check=PlayerController.loginPlayersFromDatabase(txt_Username.getText(), txt_Password.getText());

            if (check) {

                SignUpController.userName=txt_Username.getText();
                SignUpController.Password=txt_Password.getText();

                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserMenu.fxml"));
                Scene scene = new Scene(fxmlLoader.load(),1366 , 763);
                Main.primaryStage.setTitle("User Page");
                Main.primaryStage.setScene(scene);
                Main.primaryStage.show();

            }

            else {

                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed!");
                alert.setContentText("Login Failed because Your userName,Password Or both are wrong!!");
                alert.show();

            }

        } catch (ClassNotFoundException e) {

            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed!");
            alert.setContentText("Login Failed because of class not found exception!!");
            alert.show();

        } catch (SQLException e) {

            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed!");
            alert.setContentText("Login Failed because of class not found SQLException!!");
            alert.show();

        }


    }
}
