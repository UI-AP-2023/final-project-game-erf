package com.example.game_project;

import com.example.Controller.PlayerController;
import com.example.Model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private AnchorPane anch_Profile;

    @FXML
    private ImageView img_Profile;

    @FXML
    private Text txt_Level;

    @FXML
    private Text txt_Losses;

    @FXML
    private Text txt_Password;

    @FXML
    private Text txt_UserName;

    @FXML
    private Text txt_Wins;

    @FXML
    private Text txt_Map;

    @FXML
    void back(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1366 , 763);
        Main.primaryStage.setTitle("Main Page");
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            Player player=PlayerController.findByUsername(SignUpController.userName);
            txt_Level.setText(txt_Level.getText()+player.getLevel());
            txt_UserName.setText(txt_UserName.getText()+player.getUserName());
            txt_Password.setText(txt_Password.getText()+player.getPassword());
            txt_Wins.setText(txt_Wins.getText()+player.getPlayerWins());
            txt_Losses.setText(txt_Losses.getText()+player.getPlayerLosses());
            txt_Map.setText(txt_Map.getText()+player.getMap().getClass().getSimpleName());


        } catch (ClassNotFoundException e) {

            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Could not find the player!!");
            alert.setContentText("Could not find the player caused by class not found!!");
            alert.show();

        } catch (SQLException e) {

            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Could not find the player!!");
            alert.setContentText("Could not find the player caused by SQLException!!");
            alert.show();
        }

    }
}
