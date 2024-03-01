package com.example.game_project;

import com.example.Controller.PlayerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowAllMapsController implements Initializable {

    @FXML
    private AnchorPane anch_MapSelection;

    @FXML
    private Button btn_Next;

    @FXML
    private Button btn_Select;

    @FXML
    private ImageView img_Background;

    @FXML
    private Text txt_TroopsLimit;

    private static ImageView currentImage=new ImageView();

    private int currentImageIndex=-1;

    ArrayList<ImageView>mapImages;


    int troopsLimit;

    @FXML
    void select(ActionEvent event) throws IOException {

        PlayerController.mapSelection(currentImage);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1366 , 763);
        Main.primaryStage.setTitle("User Page");
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();

    }

    @FXML
    void next(MouseEvent event) {

        currentImageIndex++;

        if (currentImageIndex<=mapImages.size()-1) {

            currentImage = mapImages.get(currentImageIndex);

            img_Background.setImage(currentImage.getImage());
            troopsLimit = PlayerController.getMapTroopsLimit(currentImage);
            txt_TroopsLimit.setText("Troops Limit: " + troopsLimit);
        }

        else
        {
            currentImageIndex=0;
            currentImage=mapImages.get(currentImageIndex);

            img_Background.setImage(currentImage.getImage());
            troopsLimit=PlayerController.getMapTroopsLimit(currentImage);
            txt_TroopsLimit.setText("Troops Limit: " +troopsLimit);

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //////////////   troops limit and pictures size
        mapImages=PlayerController.allMapImages();
        btn_Select.setDefaultButton(true);
        currentImage=mapImages.get(0);


        img_Background.setImage(currentImage.getImage());
        troopsLimit=PlayerController.getMapTroopsLimit(currentImage);
        txt_TroopsLimit.setText("Troops Limit: " +troopsLimit);


    }
}
