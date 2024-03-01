package com.example.game_project;

import com.example.Controller.MakeDraggable;
import com.example.Controller.PlayerController;
import com.example.Model.Buildings.*;
import com.example.Model.Heroes.*;
import com.example.Model.Maps.Map;
import com.example.Model.Maps.Map1;
import com.example.Model.Maps.Map4;
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

    @FXML
    private Button btn_back;

    private ImageView Raz;

    private ArrayList<Hero> heroes = new ArrayList<Hero>();

    private int TroopsLimit=10;

    private Map map;

    @FXML
    private AnchorPane anch_map4;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        hbox_Heroes.setVisible(true);

        ArcherTower archerTower=new ArcherTower(img_Archer1.getLayoutX(),img_Archer1.getLayoutY());

        ArcherTower archerTower1=new ArcherTower(img_ArcherTower2.getLayoutX(),img_ArcherTower2.getLayoutY());

        ArcherTower archerTower2=new ArcherTower(img_ArcherTower3.getLayoutX(), img_ArcherTower3.getLayoutY());

        TownHall townHall=new TownHall(img_TownHall.getLayoutX(), img_TownHall.getLayoutY());


        map=new Map4();

        map.getBuildings().add(archerTower);
        map.getBuildings().add(archerTower1);
        map.getBuildings().add(archerTower2);
        map.getBuildings().add(townHall);

        PlayerController.allMaps.add(map);

        txt_TroopsLimit.setText("Troops Limit:"+map.getTroopsLimit());
        txt_UserName.setText(AttackController.enemyName);

        System.out.println("archerTower position: "+archerTower.getxPosition()+"   "+archerTower.getyPosition());
        System.out.println("archerTower1 position: "+archerTower1.getxPosition()+"   "+archerTower1.getyPosition());
        System.out.println("archerTower2 position: "+archerTower2.getxPosition()+"   "+archerTower2.getyPosition());


    }

    @FXML
    void start(ActionEvent event) {

        System.out.println("Starting....");

        Thread threadStart=new Thread(new Runnable() {
            @Override
            public void run() {
                while (map.getBuildings().size()>0) {
                    anch_Map4.getChildren().removeAll(img_ArcherTower1,img_ArcherTower2,img_ArcherTower3,img_TownHall);

                    for (Building building : map.getBuildings())
                    {

                        if (building instanceof ArcherTower)
                        {
                            if (building.getxPosition()==img_ArcherTower1.getLayoutX() && building.getyPosition()==img_ArcherTower1.getLayoutY())
                            {
                                img_ArcherTower1.setLayoutX(building.getxPosition());
                                img_ArcherTower1.setLayoutY(building.getyPosition());
                                anch_Map4.getChildren().add(img_ArcherTower1);
                            }

                            if (building.getxPosition()==img_ArcherTower2.getLayoutX() && building.getyPosition()==img_ArcherTower2.getLayoutY())
                            {
                                img_ArcherTower2.setLayoutX(building.getxPosition());
                                img_ArcherTower2.setLayoutY(building.getyPosition());
                                anch_Map4.getChildren().add(img_ArcherTower2);
                            }

                            if (building.getxPosition()==img_ArcherTower3.getLayoutX() && building.getyPosition()==img_ArcherTower3.getLayoutY())
                            {
                                img_ArcherTower3.setLayoutX(building.getxPosition());
                                img_ArcherTower3.setLayoutY(building.getyPosition());
                                anch_Map4.getChildren().add(img_ArcherTower3);
                            }

                        }

                        if (building instanceof TownHall)
                        {
                            img_TownHall.setLayoutX(building.getxPosition());
                            img_TownHall.setLayoutY(building.getyPosition());
                            anch_Map4.getChildren().add(img_TownHall);
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

        threadStart.start();

    }

    @FXML
    void spawnJag(MouseEvent event) {

        Jug=new ImageView(new Image(Main.class.getResource("Images/jag.png").toString()));
        Jug.setFitHeight(100);
        Jug.setFitWidth(100);
        Juggernuat juggernuat1=new Juggernuat();

        if (TroopsLimit>=juggernuat1.getCapacity())
        {
            anch_Map4.getChildren().add(Jug);
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

        Golem golem1 =new Golem();

        if (TroopsLimit>= golem1.getCapacity())
        {
            anch_Map4.getChildren().add(Jak);
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
                Golem golem =new Golem(map1,Jak);

                golem.getImages().clear();
                golem.getImages().add(Jak);
                heroes.add(golem);
                Thread t = new Thread(golem);
                t.start();
                TroopsLimit-= golem.getCapacity();

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

        Orc orc1 =new Orc();

        if (TroopsLimit>= orc1.getCapacity())
        {
            anch_Map4.getChildren().add(Raz);
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
                Orc orc =new Orc(map1,Raz);

                orc.getImages().clear();
                orc.getImages().add(Raz);
                heroes.add(orc);
                Thread t = new Thread(orc);
                t.start();
                TroopsLimit-= orc.getCapacity();

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

        if (TroopsLimit>=wereWolf1.getCapacity())
        {
            anch_Map4.getChildren().add(wolf);
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

    @FXML
    void back(ActionEvent event) {

    }
}
