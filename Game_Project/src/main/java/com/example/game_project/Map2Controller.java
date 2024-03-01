package com.example.game_project;

import com.example.Controller.MakeDraggable;
import com.example.Controller.PlayerController;
import com.example.Model.Buildings.*;
import com.example.Model.Heroes.*;
import com.example.Model.Maps.Map2;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
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
    private Button btn_back;

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

    private ArrayList<Hero> heroes = new ArrayList<Hero>();

    public static ArrayList<ImageView>heroesImage=new ArrayList<ImageView>();

    private int TroopsLimit=10;

    private static Map2 map;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        hbox_Heroes.setVisible(true);


        Bounds bounds = img_ArcherTower1.localToParent(img_ArcherTower1.getBoundsInLocal());
        ArcherTower archerTower1=new ArcherTower(bounds.getCenterX(),bounds.getCenterY());

        Bounds bounds1 = img_ArcherTower2.localToParent(img_ArcherTower2.getBoundsInLocal());
        ArcherTower archerTower2=new ArcherTower(bounds1.getCenterX(),bounds1.getCenterY());

        Bounds bounds2 = img_TownHall.localToParent(img_TownHall.getBoundsInLocal());
        TownHall townHall=new TownHall(bounds2.getCenterX(),bounds2.getCenterY());

         map=new Map2();

        map.getBuildings().add(townHall);
        map.getBuildings().add(archerTower1);
        map.getBuildings().add(archerTower2);

        PlayerController.allMaps.add(map);

        txt_TroopsLimit.setText("Troops Limit:"+map.getTroopsLimit());
        txt_UserName.setText("UserName:"+AttackController.enemyName);
    }




    @FXML
    void start(ActionEvent event) {

        System.out.println("Starting....");


        new Thread(() -> {

            while (checkAnyBuildingRemain()  ) {

                for (Building building : map.getBuildings())
                {
                    System.out.println("building name: "+building.getClass().getSimpleName() +" isDestroyed:"+building.isDestroyed());

                    if (building.isDestroyed())
                    {
                        if (!building.getBuildingImage().getImage().equals(new Image(Main.class.getResource("Images/blank.png").toString())))
                        {
                            if (building instanceof ArcherTower)
                            {
                                Bounds bounds2 = img_ArcherTower2.localToParent(img_ArcherTower2.getBoundsInLocal());
                                Bounds bounds1 = img_ArcherTower1.localToParent(img_ArcherTower1.getBoundsInLocal());

                                if (building.getxPosition()==bounds1.getCenterX() && building.getyPosition()==bounds1.getCenterY())
                                {
                                    System.out.println("Archer tower is destroyed ");
                                    img_ArcherTower1.setImage(new Image(Main.class.getResource("Images/blank.png").toString()));
                                    img_Archer1.setImage(new Image(Main.class.getResource("Images/blank.png").toString()));
                                }

                                if (building.getxPosition()==bounds2.getCenterX() && building.getyPosition()==bounds2.getCenterY())
                                {
                                    System.out.println("Archer tower is destroyed ");
                                    img_ArcherTower2.setImage(new Image(Main.class.getResource("Images/blank.png").toString()));
                                    img_Archer2.setImage(new Image(Main.class.getResource("Images/blank.png").toString()));
                                }

                            }

                            if (building instanceof TownHall)
                            {
                                System.out.println("Townhall is destroyed");
                                img_TownHall.setImage(new Image(Main.class.getResource("Images/blank.png").toString()));
                            }

                        }

                    }

                }

                for (Hero hero:heroes) {
                    if (hero.isDead()) {
                        System.out.println("Hero " + hero.getClass().getSimpleName() + " is dead" );
                        hero.getHeroImage().setImage(new Image(Main.class.getResource("Images/blank.png").toString()));
                    }
                }
            }
            System.out.println("game finished!!");
            btn_back.setDisable(false);

        }).start();



    }

    @FXML
    void spawnJag(MouseEvent event) {

        Jug=new ImageView(new Image(Main.class.getResource("Images/jag.png").toString()));
        Jug.setFitHeight(100);
        Jug.setFitWidth(100);
        Juggernuat juggernuat1=new Juggernuat();

        if (TroopsLimit>=juggernuat1.getCapacity())
        {
            anch_Map2.getChildren().add(Jug);
            MakeDraggable.makeDraggable(Jug);

            Jug.setOnMouseReleased(event1 -> {

                Juggernuat juggernuat=new Juggernuat(map,Jug);

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
            anch_Map2.getChildren().add(Jak);
            MakeDraggable.makeDraggable(Jak);

            Jak.setOnMouseReleased(event1 -> {

                Golem golem =new Golem(map,Jak);

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
            anch_Map2.getChildren().add(Raz);
            MakeDraggable.makeDraggable(Raz);

            Raz.setOnMouseReleased(event1 -> {


                Orc orc =new Orc(map,Raz);

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
            anch_Map2.getChildren().add(wolf);
            MakeDraggable.makeDraggable(wolf);

            wolf.setOnMouseReleased(event1 -> {

                WereWolf wereWolf=new WereWolf(map,wolf);

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

    public static boolean checkAnyBuildingRemain()
    {
        int remainingNumbers=0;

        for (Building building: map.getBuildings())
        {
            if (!building.isDestroyed())
            {
                remainingNumbers++;
            }
        }

        if (remainingNumbers==0)
        {
            return false;
        }
        else
            return true;
    }


    public boolean checkAnyHeroIsAlive()
    {
        int remainingHeroes =0;

        for (Hero hero:heroes)
        {
            if (!hero.isDead())
            {
                remainingHeroes++;
            }
        }

        if (remainingHeroes==0)
        {
            return false;
        }
        else
            return true;
    }


    @FXML
    void back(ActionEvent event) {

    }
}
