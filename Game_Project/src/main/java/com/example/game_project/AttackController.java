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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AttackController implements Initializable {

    @FXML
    private AnchorPane anch_Map;

    @FXML
    private Button btn_Attack;

    @FXML
    private Button btn_Menu;

    @FXML
    private Button btn_next;

    @FXML
    private HBox hbox_Heroes;

    @FXML
    private ImageView img_Gol;

    @FXML
    private ImageView img_Map;

    @FXML
    private ImageView img_Orc;

    @FXML
    private ImageView img_Wolf;

    @FXML
    private ImageView img_jag;

    @FXML
    private Text txt_TroopsLimit;

    @FXML
    private Text txt_UserName;

    private ImageView wolf;

    private ImageView Jug;

    private ImageView Raz;

    private ImageView Jak;

    private static int index=0;

    public static String enemyName;


    @FXML
    void attack(ActionEvent event) throws IOException {

        btn_Menu.setDisable(true);
        btn_next.setDisable(true);
        btn_Attack.setDisable(true);

        if (this.img_Map.getImage().getUrl().contains("Map21.png"))
        {


            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Map2.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),1366 , 763);
            Main.primaryStage.setTitle("Attack Page");
            Main.primaryStage.setScene(scene);
            Main.primaryStage.show();

            PlayerController.allMaps.remove(PlayerController.allMaps.size()-1);

        }
        if (this.img_Map.getImage().getUrl().contains("Map11.png"))
        {


            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Map1.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),1366 , 763);
            Main.primaryStage.setTitle("Attack Page");
            Main.primaryStage.setScene(scene);
            Main.primaryStage.show();

            PlayerController.allMaps.remove(PlayerController.allMaps.size()-1);

        }

        if (this.img_Map.getImage().getUrl().contains("Map31.png"))
        {


            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Map3.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),1366 , 763);
            Main.primaryStage.setTitle("Attack Page");
            Main.primaryStage.setScene(scene);
            Main.primaryStage.show();

            PlayerController.allMaps.remove(PlayerController.allMaps.size()-1);

        }

        if (this.img_Map.getImage().getUrl().contains("Map41.png"))
        {



            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Map4.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),1366 , 763);
            Main.primaryStage.setTitle("Attack Page");
            Main.primaryStage.setScene(scene);
            Main.primaryStage.show();

            PlayerController.allMaps.remove(PlayerController.allMaps.size()-1);
        }

    }

    @FXML
    void back(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1366 , 763);
        Main.primaryStage.setTitle("User Page");
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();

    }

    @FXML
    void next(ActionEvent event) throws IOException {

        try {
            showNextMapExceptMyMap();
        } catch (SQLException e) {

            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error while initializing the map");
            alert.setContentText("Error while initializing the map because of the SQLException!!");
            alert.show();

        } catch (ClassNotFoundException e) {

            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error while initializing the map");
            alert.setContentText("Error while initializing the map because of the ClassNotFound!!");
            alert.show();

        }

    }


    //============================================Show next map=========================================================

    private void showNextMapExceptMyMap() throws SQLException, ClassNotFoundException, IOException {

                if (index>PlayerController.players.size()-1)
                {
                    index=0;
                }

                Player enemy=PlayerController.players.get(index);
                enemyName=enemy.getUserName();

                txt_UserName.setText("UserName: " + enemy.getUserName());
                txt_TroopsLimit.setText("Troops Limit: " + enemy.getMap().getTroopsLimit());

                img_Map.setImage(enemy.getMap().getMapImage().getImage());
                index++;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            PlayerController.initializePlayers();
            PlayerController.initializaAllMaps();
            showNextMapExceptMyMap();

        } catch (SQLException e) {

            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error while initializing the map");
            alert.setContentText("Error while initializing the map because of the SQLException!!");
            alert.show();

        } catch (ClassNotFoundException e) {

            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error while initializing the map");
            alert.setContentText("Error while initializing the map because of the ClassNotFound!!");
            alert.show();

        } catch (IOException e) {

            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error while initializing the map");
            alert.setContentText("Error while initializing the map because of the IOException !!");
            alert.show();
        }
    }


}
