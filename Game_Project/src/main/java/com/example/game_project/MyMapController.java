package com.example.game_project;

import com.example.Controller.PlayerController;
import com.example.Model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MyMapController implements Initializable {

    @FXML
    private Button btn_Back;

    @FXML
    private ImageView img_Background;

    @FXML
    private ImageView img_Map;

    @FXML
    private Text txt_TroopsLimit;

    @FXML
    void back(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1366 , 763);
        Main.primaryStage.setTitle("User Page");
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            Player player=PlayerController.findByUsername(SignUpController.userName);
            img_Map.setImage(player.getMap().getMapImage().getImage());
            txt_TroopsLimit.setText(txt_TroopsLimit.getText()+player.getMap().getTroopsLimit());

        } catch (ClassNotFoundException e) {

            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error while loading player!!!");
            alert.setContentText("Error loading player caused by class not found exception");
            alert.show();

        } catch (SQLException e) {

            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error while loading player!!!");
            alert.setContentText("Error loading player caused by SQLException");
            alert.show();

        }
    }
}
