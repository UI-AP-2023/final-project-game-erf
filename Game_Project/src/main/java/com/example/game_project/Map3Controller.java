package com.example.game_project;

import com.example.Controller.MakeDraggable;
import com.example.Controller.PlayerController;
import com.example.Model.Heroes.Juggernuat;
import com.example.Model.Maps.Map;
import com.example.Model.Maps.Map3;
import com.example.Model.Buildings.WizardTower;
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

public class Map3Controller implements Initializable {


    @FXML
    private ImageView img_Wizard1;

    @FXML
    private ImageView img_Wizard2;

    @FXML
    private ImageView img_Wizard3;

    @FXML
    private ImageView img_Wizard4;

    @FXML
    private ImageView img_Wizard5;

    @FXML
    private ImageView img_Wizard6;

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
    private ImageView img_WizardTower1;

    @FXML
    private ImageView img_WizardTower2;

    @FXML
    private ImageView img_WizardTower3;

    @FXML
    private ImageView img_WizardTower4;

    @FXML
    private ImageView img_WizardTower5;

    @FXML
    private ImageView img_WizardTower6;

    private ImageView Jug;

    private ImageView Jak;

    private ImageView wolf;

    private ImageView Raz;

    @FXML
    private AnchorPane anch_Map3;

    @FXML
    private ImageView img_Map3;

    @FXML
    private Text txt_TroopsLimit;

    @FXML
    private Text txt_UserName;

    @FXML
    private ImageView img_TownHall;

    @FXML
    private Button btn_Start;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        hbox_Heroes.setVisible(true);

        WizardTower wizardTower1=new WizardTower(img_WizardTower1.getLayoutX(), img_WizardTower1.getLayoutY());
        WizardTower wizardTower2=new WizardTower(img_WizardTower2.getLayoutX(), img_WizardTower2.getLayoutY());
        WizardTower wizardTower3=new WizardTower(img_WizardTower3.getLayoutX(), img_WizardTower3.getLayoutY());
        WizardTower wizardTower4=new WizardTower(img_WizardTower4.getLayoutX(), img_WizardTower4.getLayoutY());
        WizardTower wizardTower5=new WizardTower(img_WizardTower5.getLayoutX(), img_WizardTower5.getLayoutY());
        WizardTower wizardTower6=new WizardTower(img_WizardTower6.getLayoutX(), img_WizardTower6.getLayoutY());
        Map3 map=new Map3();

        map.getBuildings().add(wizardTower1);
        map.getBuildings().add(wizardTower2);
        map.getBuildings().add(wizardTower3);
        map.getBuildings().add(wizardTower4);
        map.getBuildings().add(wizardTower5);
        map.getBuildings().add(wizardTower6);

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
        anch_Map3.getChildren().add(Jug);
        MakeDraggable.makeDraggable(Jug);

        Jug.setOnMouseReleased(event1 -> {
            Map map1=null;


            for (Map map:PlayerController.allMaps)
            {

                if (map.getMapImage().getImage().getUrl().equals(img_Map3.getImage().getUrl()))
                {
                    map1=map;

                }
            }
            Juggernuat juggernuat=new Juggernuat(map1,Jug,anch_Map3);
            Thread t = new Thread(juggernuat);
            t.start();
        });



    }

    @FXML
    void spawnJak(MouseEvent event) {

        Jak=new ImageView(new Image(img_Jak.getImage().getUrl()));
        Jak.setFitHeight(100);
        Jak.setFitWidth(100);
        anch_Map3.getChildren().add(Jak);
        MakeDraggable.makeDraggable(Jak);
    }

    @FXML
    void spawnRaz(MouseEvent event) {

        Raz=new ImageView(new Image(img_Raz.getImage().getUrl()));
        Raz.setFitHeight(100);
        Raz.setFitWidth(100);
        anch_Map3.getChildren().add(Raz);
        MakeDraggable.makeDraggable(Raz);

    }

    @FXML
    void spawnWolf(MouseEvent event) {

        wolf=new ImageView(new Image(img_Wolf.getImage().getUrl()));
        wolf.setFitHeight(100);
        wolf.setFitWidth(100);
        anch_Map3.getChildren().add(wolf);
        MakeDraggable.makeDraggable(wolf);

    }
}
