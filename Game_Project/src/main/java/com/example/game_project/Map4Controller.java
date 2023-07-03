package com.example.game_project;

import com.example.Controller.MakeDraggable;
import com.example.Controller.PlayerController;
import com.example.Model.Buildings.ArcherTower;
import com.example.Model.Heroes.Juggernuat;
import com.example.Model.Maps.Map;
import com.example.Model.Maps.Map4;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class Map4Controller implements Initializable {

    @FXML
    private AnchorPane anch_Map4;

    @FXML
    private ImageView img_Archer1;

    @FXML
    private ImageView img_Archer2;

    @FXML
    private ImageView img_Archer3;

    @FXML
    private ImageView img_Jak;

    @FXML
    private HBox hbox_Heroes;

    @FXML
    private ImageView img_Wolf;

    @FXML
    private ImageView img_jag;

    @FXML
    private ImageView img_Raz;

    @FXML
    private ImageView img_ArcherTower1;

    @FXML
    private ImageView img_ArcherTower2;

    @FXML
    private ImageView img_ArcherTower3;

    @FXML
    private ImageView img_Map4;

    @FXML
    private Text txt_TroopsLimit;

    @FXML
    private Text txt_UserName;

    @FXML
    private ImageView img_TownHall;

    private ImageView Jug;

    private ImageView Jak;

    private ImageView wolf;

    private ImageView Raz;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        hbox_Heroes.setVisible(true);

        ArcherTower archerTower1=new ArcherTower(img_ArcherTower1.getLayoutX(), img_ArcherTower1.getLayoutY());
        ArcherTower archerTower2=new ArcherTower(img_ArcherTower2.getLayoutX(), img_ArcherTower2.getLayoutY());
        ArcherTower archerTower3=new ArcherTower(img_ArcherTower3.getLayoutX(), img_ArcherTower3.getLayoutY());

        Map4 map=new Map4();
        map.getBuildings().add(archerTower1);
        map.getBuildings().add(archerTower2);
        map.getBuildings().add(archerTower3);

        PlayerController.allMaps.add(map);

        txt_TroopsLimit.setText("Troops Limit:"+map.getTroopsLimit());
        txt_UserName.setText("UserName:"+AttackController.enemyName);
    }

    @FXML
    void start(ActionEvent actionEvent)
    {

    }


    @FXML
    void spawnJag(MouseEvent event) {

        Jug=new ImageView(new Image(Main.class.getResource("Images/jag.png").toString()));
        Jug.setFitHeight(100);
        Jug.setFitWidth(100);
        anch_Map4.getChildren().add(Jug);
        MakeDraggable.makeDraggable(Jug);

        Jug.setOnMouseReleased(event1 -> {
            Map map1=null;


            for (Map map:PlayerController.allMaps)
            {

                if (map.getMapImage().getImage().getUrl().equals(img_Map4.getImage().getUrl()))
                {
                    map1=map;

                }
            }
            Juggernuat juggernuat=new Juggernuat(map1,Jug,anch_Map4);
            Thread t = new Thread(juggernuat);
            t.start();
        });



    }

    @FXML
    void spawnJak(MouseEvent event) {

        Jak=new ImageView(new Image(img_Jak.getImage().getUrl()));
        Jak.setFitHeight(100);
        Jak.setFitWidth(100);
        anch_Map4.getChildren().add(Jak);
        MakeDraggable.makeDraggable(Jak);
    }

    @FXML
    void spawnRaz(MouseEvent event) {

        Raz=new ImageView(new Image(img_Raz.getImage().getUrl()));
        Raz.setFitHeight(100);
        Raz.setFitWidth(100);
        anch_Map4.getChildren().add(Raz);
        MakeDraggable.makeDraggable(Raz);

    }

    @FXML
    void spawnWolf(MouseEvent event) {

        wolf=new ImageView(new Image(img_Wolf.getImage().getUrl()));
        wolf.setFitHeight(100);
        wolf.setFitWidth(100);
        anch_Map4.getChildren().add(wolf);
        MakeDraggable.makeDraggable(wolf);

    }
}
