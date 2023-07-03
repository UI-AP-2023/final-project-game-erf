package com.example.game_project;

import com.example.Controller.MakeDraggable;
import com.example.Controller.PlayerController;
import com.example.Model.Buildings.*;
import com.example.Model.Heroes.*;
import com.example.Model.Maps.Map;
import com.example.Model.Maps.Map1;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Map1Controller implements Initializable {

    @FXML
    private AnchorPane anch_Map;

    @FXML
    private ImageView img_Archer;

    @FXML
    private ImageView img_ArcherTower;

    @FXML
    private ImageView img_Map;

    @FXML
    private ImageView img_Tesla;

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
    private ImageView img_TownHall;

    @FXML
    private ImageView img_Wizard;

    @FXML
    private ImageView img_WizardTower;

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

    private Map1 map;

    private ArrayList<Hero>heroes = new ArrayList<Hero>();

    private int TroopsLimit=9;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        hbox_Heroes.setVisible(true);


        WizardTower wizardTower=new WizardTower(img_WizardTower.getLayoutX(), img_WizardTower.getLayoutY());
        ArcherTower archerTower=new ArcherTower(img_ArcherTower.getLayoutX(), img_ArcherTower.getLayoutY());
        TownHall townHall=new TownHall(img_TownHall.getX(), img_TownHall.getY());
        Tesla tesla=new Tesla(img_Tesla.getLayoutX(), img_Tesla.getLayoutY());
        map=new Map1();

        map.getBuildings().add(townHall);
        map.getBuildings().add(wizardTower);
        map.getBuildings().add(archerTower);
        map.getBuildings().add(tesla);

        PlayerController.allMaps.add(map);

        txt_TroopsLimit.setText("Troops Limit:"+map.getTroopsLimit());
        txt_UserName.setText(AttackController.enemyName);

    }

    @FXML
    void start(ActionEvent event) {

                System.out.println("Starting....");

                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {

                        while (map.getBuildings().size()>0) {
                            anch_Map.getChildren().removeAll(img_ArcherTower,img_WizardTower,img_Tesla,img_TownHall);

                            for (Building building : map.getBuildings())
                            {

                                if (building instanceof ArcherTower)
                                {
                                    img_ArcherTower.setLayoutX(building.getxPosition());
                                    img_ArcherTower.setLayoutY(building.getyPosition());
                                    anch_Map.getChildren().add(img_ArcherTower);
                                }

                                if (building instanceof WizardTower)
                                {
                                    img_WizardTower.setLayoutX(building.getxPosition());
                                    img_WizardTower.setLayoutY(building.getyPosition());
                                    anch_Map.getChildren().add(img_WizardTower);
                                }

                                if (building instanceof Tesla)
                                {
                                    img_Tesla.setLayoutX(building.getxPosition());
                                    img_Tesla.setLayoutY(building.getyPosition());
                                    anch_Map.getChildren().add(img_Tesla);
                                }

                                if (building instanceof ArcherTower)
                                {
                                    img_TownHall.setLayoutX(building.getxPosition());
                                    img_TownHall.setLayoutY(building.getyPosition());
                                    anch_Map.getChildren().add(img_TownHall);
                                }
                                System.out.println("hanoz edame dare");
                            }

                            for (Hero hero:heroes) {
                                if (hero.getHeroHealth() <= 0) {
                                    hero.getImages().get(0).setVisible(false);
                                    //-----------------------------------------remHDel
                                }
                            }

                        }
                    }
                });


    }

    @FXML
    void spawnJag(MouseEvent event) {

        Jug=new ImageView(new Image(Main.class.getResource("Images/jag.png").toString()));
        Jug.setFitHeight(100);
        Jug.setFitWidth(100);
        Juggernuat juggernuat1=new Juggernuat();

        if (TroopsLimit>juggernuat1.getCapacity())
        {
            anch_Map.getChildren().add(Jug);
            MakeDraggable.makeDraggable(Jug);

            Jug.setOnMouseReleased(event1 -> {
                Map map1=null;

                for (Map map:PlayerController.allMaps)
                {

                    if (map instanceof Map1)
                    {
                        map1=map;
                    }
                }
                Juggernuat juggernuat=new Juggernuat(map1,Jug);

                juggernuat.getImages().clear();
                juggernuat.getImages().add(Jug);
                heroes.add(juggernuat);
                Thread t = new Thread(juggernuat);
                t.start();
                TroopsLimit-=juggernuat.getCapacity();

            });
        }
        else
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error while creating hero!!");
            alert.setContentText("your have created heroes and reach the map troops limit!!");
            alert.show();
        }


    }

    @FXML
    void spawnJak(MouseEvent event) {

        Jak=new ImageView(new Image(Main.class.getResource("Images/jakiro.png").toString()));
        Jak.setFitHeight(100);
        Jak.setFitWidth(100);

        Jakiro jakiro1=new Jakiro();

        if (TroopsLimit>jakiro1.getCapacity())
        {
            anch_Map.getChildren().add(Jak);
            MakeDraggable.makeDraggable(Jak);

            Jak.setOnMouseReleased(event1 -> {
                Map map1=null;

                for (Map map:PlayerController.allMaps)
                {

                    if (map instanceof Map1)
                    {
                        map1=map;
                    }
                }
                Jakiro jakiro=new Jakiro(map1,Jak);

                jakiro.getImages().clear();
                jakiro.getImages().add(Jak);
                heroes.add(jakiro);
                Thread t = new Thread(jakiro);
                t.start();
                TroopsLimit-=jakiro.getCapacity();

            });
        }
        else
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error while creating hero!!");
            alert.setContentText("your have created heroes and reach the map troops limit!!");
            alert.show();
        }

    }

    @FXML
    void spawnRaz(MouseEvent event) {

        Raz=new ImageView(new Image(Main.class.getResource("Images/raz.png").toString()));
        Raz.setFitHeight(100);
        Raz.setFitWidth(100);

        Razor razor1=new Razor();

        if (TroopsLimit>razor1.getCapacity())
        {
            anch_Map.getChildren().add(Raz);
            MakeDraggable.makeDraggable(Raz);

            Raz.setOnMouseReleased(event1 -> {
                Map map1=null;

                for (Map map:PlayerController.allMaps)
                {

                    if (map instanceof Map1)
                    {
                        map1=map;
                    }
                }
                Razor razor=new Razor(map1,Raz);

                razor.getImages().clear();
                razor.getImages().add(Raz);
                heroes.add(razor);
                Thread t = new Thread(razor);
                t.start();
                TroopsLimit-=razor.getCapacity();

            });
        }
        else
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error while creating hero!!");
            alert.setContentText("your have created heroes and reach the map troops limit!!");
            alert.show();
        }


    }

    @FXML
    void spawnWolf(MouseEvent event) {

        wolf=new ImageView(new Image(Main.class.getResource("Images/wolf.png").toString()));
        wolf.setFitHeight(100);
        wolf.setFitWidth(100);

        WereWolf wereWolf1=new WereWolf();

        if (TroopsLimit>wereWolf1.getCapacity())
        {
            anch_Map.getChildren().add(wolf);
            MakeDraggable.makeDraggable(wolf);

            wolf.setOnMouseReleased(event1 -> {
                Map map1=null;

                for (Map map:PlayerController.allMaps)
                {

                    if (map instanceof Map1)
                    {
                        map1=map;
                    }
                }
                WereWolf wereWolf=new WereWolf(map1,wolf);

                wereWolf.getImages().clear();
                wereWolf.getImages().add(wolf);
                heroes.add(wereWolf);
                Thread t = new Thread(wereWolf);
                t.start();
                TroopsLimit-=wereWolf.getCapacity();

            });
        }
        else
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error while creating hero!!");
            alert.setContentText("your have created heroes and reach the map troops limit!!");
            alert.show();
        }


    }

}
