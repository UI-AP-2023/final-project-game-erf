package com.example.game_project;

import com.example.Controller.MakeDraggable;
import com.example.Controller.PlayerController;
import com.example.Model.Buildings.ArcherTower;
import com.example.Model.Buildings.TownHall;
import com.example.Model.Heroes.Juggernuat;
import com.example.Model.Maps.Map;
import com.example.Model.Maps.Map2;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class Map2Controller implements Initializable {

    @FXML
    private ImageView img_Archer1;

    @FXML
    private ImageView img_Archer2;

    @FXML
    private ImageView img_ArcherTower1;

    @FXML
    private ImageView img_Jak;

    @FXML
    private HBox hbox_Heroes;

    @FXML
    private AnchorPane anch_Map2;

    @FXML
    private ImageView img_Wolf;

    @FXML
    private ImageView img_jag;

    @FXML
    private ImageView img_Raz;

    @FXML
    private ImageView img_ArcherTower2;

    @FXML
    private ImageView img_Map2;

    @FXML
    private ImageView img_TownHall;

    private ImageView Jug;

    private ImageView Jak;

    private ImageView wolf;

    private ImageView Raz;

    @FXML
    private Text txt_TroopsLimit;

    @FXML
    private Text txt_UserName;

    @FXML
    private Button btn_Start;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        hbox_Heroes.setVisible(true);

        ArcherTower archerTower1=new ArcherTower(img_Archer1.getLayoutX(), img_ArcherTower1.getLayoutY());
        ArcherTower archerTower2=new ArcherTower(img_Archer2.getLayoutX(), img_ArcherTower2.getLayoutY());
        TownHall townHall=new TownHall(img_TownHall.getLayoutX(), img_TownHall.getLayoutY());
        Map2 map=new Map2();

        map.getBuildings().add(townHall);
        map.getBuildings().add(archerTower1);
        map.getBuildings().add(archerTower2);

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
        anch_Map2.getChildren().add(Jug);
        MakeDraggable.makeDraggable(Jug);

        Jug.setOnMouseReleased(event1 -> {
            Map map1=null;

            for (Map map:PlayerController.allMaps)
            {
                System.out.println(map.getClass().getSimpleName()+ " is map name");
                if (map.getClass().getSimpleName().equals("Map2"))
                {
                    map1=map;

                }
            }
            Juggernuat juggernuat=new Juggernuat(map1,Jug,anch_Map2);
            Thread t = new Thread(juggernuat);
            t.start();
        });



    }

    @FXML
    void spawnJak(MouseEvent event) {

        Jak=new ImageView(new Image(img_Jak.getImage().getUrl()));
        Jak.setFitHeight(100);
        Jak.setFitWidth(100);
        anch_Map2.getChildren().add(Jak);
        MakeDraggable.makeDraggable(Jak);
    }

    @FXML
    void spawnRaz(MouseEvent event) {

        Raz=new ImageView(new Image(img_Raz.getImage().getUrl()));
        Raz.setFitHeight(100);
        Raz.setFitWidth(100);
        anch_Map2.getChildren().add(Raz);
        MakeDraggable.makeDraggable(Raz);

    }

    @FXML
    void spawnWolf(MouseEvent event) {

        wolf=new ImageView(new Image(img_Wolf.getImage().getUrl()));
        wolf.setFitHeight(100);
        wolf.setFitWidth(100);
        anch_Map2.getChildren().add(wolf);
        MakeDraggable.makeDraggable(wolf);

    }
}
