package com.example.game_project;

import com.example.Controller.MakeDraggable;
import com.example.Controller.PlayerController;
import com.example.Model.Buildings.*;
import com.example.Model.Heroes.*;
import com.example.Model.Maps.Map1;
import com.example.Model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    private ImageView img_Gol;

    @FXML
    private HBox hbox_Heroes;

    @FXML
    private ImageView img_Wolf;

    @FXML
    private ImageView img_jag;

    @FXML
    private ImageView img_Orc;

    @FXML
    private ImageView img_TownHall;

    @FXML
    private ImageView img_Wizard;

    @FXML
    private ImageView img_WizardTower;

    private ImageView Jug;

    private ImageView Jak;

    private ImageView wolf;

    private ImageView orc;

    @FXML
    private Text txt_TroopsLimit;

    @FXML
    private Text txt_UserName;

    @FXML
    private Button btn_Start;

    private  static Map1 map;

    public static ArrayList<Hero>heroes ;

    public static ArrayList<ImageView>heroesImage=new ArrayList<ImageView>();

    private int TroopsLimit=100;

    private ArcherTower archerTowertemp;
    private WizardTower wizardTowertemp;
    private Tesla teslaTemp;

    @FXML
    private Button btn_back;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        hbox_Heroes.setVisible(true);
        heroes=new ArrayList<>();

        Bounds bounds = img_WizardTower.localToParent(img_WizardTower.getBoundsInLocal());
        WizardTower wizardTower=new WizardTower(bounds.getCenterX(), bounds.getCenterY());

        Bounds bounds1 = img_ArcherTower.localToParent(img_ArcherTower.getBoundsInLocal());
        ArcherTower archerTower=new ArcherTower(bounds1.getCenterX(),bounds1.getCenterY());

        Bounds bounds2 = img_TownHall.localToParent(img_TownHall.getBoundsInLocal());
        TownHall townHall=new TownHall(bounds2.getCenterX(),bounds2.getCenterY());

        Bounds bounds3 = img_Tesla.localToParent(img_Tesla.getBoundsInLocal());
        Tesla tesla=new Tesla(bounds3.getCenterX(),bounds3.getCenterY());
        map=new Map1();

        map.getBuildings().add(townHall);
        map.getBuildings().add(wizardTower);
        map.getBuildings().add(archerTower);
        map.getBuildings().add(tesla);

        PlayerController.allMaps.add(map);

        txt_TroopsLimit.setText("Troops Limit:"+map.getTroopsLimit());
        txt_UserName.setText("Username:"+AttackController.enemyName);

         archerTowertemp=archerTower;
         wizardTowertemp=wizardTower;
         teslaTemp=tesla;


    }

    @FXML
    void start(ActionEvent event) {

//        ImageView arrow=archerTowerArrows(archerTowertemp);
//
//        new Thread(() ->
//        {
//
//            ArcherTower archerTowerNew=new ArcherTower(archerTowertemp.getxPosition(),archerTowertemp.getyPosition(),arrow);
//
//            while(checkAnyHeroRemain() && archerTowerNew.getBuildingHealth()>0)
//            {
//                if (archerTowerNew.getBuildingHealth()<=0)
//                {
//                    break;
//                }
//
//                archerTowerNew.startAll();
//            }
//
//        }).start();

//        ImageView fire=wizardTowerFireBall(wizardTowertemp);
//        new Thread(() ->
//        {
//
//            WizardTower wizardTowerNew=new WizardTower(wizardTowertemp.getxPosition(),wizardTowertemp.getyPosition(),fire);
//
//            while(checkAnyHeroRemain() && wizardTowerNew.getBuildingHealth()>0)
//            {
//                if (wizardTowerNew.getBuildingHealth()<=0)
//                {
//                    break;
//                }
//
//                wizardTowerNew.startAll();
//            }
//
//        }).start();


//        ImageView electricity=teslaElectricBall(teslaTemp);
//        new Thread(() ->
//        {
//
//            Tesla teslaNew=new Tesla(teslaTemp.getxPosition(),teslaTemp.getyPosition(),electricity);
//
//            while(checkAnyHeroRemain() && teslaNew.getBuildingHealth()>0)
//            {
//                if (teslaNew.getBuildingHealth()<=0)
//                {
//                    break;
//                }
//
//                teslaNew.startAll();
//            }
//
//        }).start();


            new Thread(() -> {

                    System.out.println("Starting....");



                    do {
                        for (Building building : map.getBuildings())
                        {

                            if (building.isDestroyed())
                            {
                                if (!building.isBlank())
                                {
                                    if (building instanceof ArcherTower)
                                    {
                                        img_ArcherTower.setImage(new Image(Main.class.getResource("Images/blank.png").toString()));
                                        img_Archer.setImage(new Image(Main.class.getResource("Images/blank.png").toString()));
                                        building.setBlank(true);
//                                        arrow.setImage(new Image(Main.class.getResource("Images/blank.png").toString()));
                                    }

                                    if (building instanceof WizardTower)
                                    {
                                        img_WizardTower.setImage(new Image(Main.class.getResource("Images/blank.png").toString()));
                                        img_Wizard.setImage(new Image(Main.class.getResource("Images/blank.png").toString()));
                                        building.setBlank(true);
                                    }

                                    if (building instanceof Tesla)
                                    {
                                        img_Tesla.setImage(new Image(Main.class.getResource("Images/blank.png").toString()));
                                        building.setBlank(true);
                                    }

                                    if (building instanceof TownHall)
                                    {
                                        img_TownHall.setImage(new Image(Main.class.getResource("Images/blank.png").toString()));
                                        building.setBlank(true);
                                    }

                                }

                            }

                        }

//                            for (Hero hero : heroes) {
//                                if (hero.isDead()) {
//                                    if (!hero.isBlank()) {
//                                        System.out.println("Hero " + hero.getClass().getSimpleName() + " is dead");
//                                        hero.getHeroImage().setImage(new Image(Main.class.getResource("Images/blank.png").toString()));
//                                        hero.setBlank(true);
//                                    }
//                                }
//                            }

                    }while (checkAnyBuildingRemain() && checkAnyHeroRemain());

                    for (Building building:map.getBuildings())
                    {

                        if (building.isDestroyed() && !building.isBlank())
                        {
                            if (building instanceof ArcherTower)
                            {
                                img_ArcherTower.setImage(new Image(Main.class.getResource("Images/blank.png").toString()));
                                img_Archer.setImage(new Image(Main.class.getResource("Images/blank.png").toString()));
                                building.setBlank(true);
                            }

                            if (building instanceof WizardTower)
                            {
                                img_WizardTower.setImage(new Image(Main.class.getResource("Images/blank.png").toString()));
                                img_Wizard.setImage(new Image(Main.class.getResource("Images/blank.png").toString()));
                                building.setBlank(true);
                            }

                            if (building instanceof Tesla)
                            {
                                img_Tesla.setImage(new Image(Main.class.getResource("Images/blank.png").toString()));
                                building.setBlank(true);
                            }

                            if (building instanceof TownHall)
                            {
                                img_TownHall.setImage(new Image(Main.class.getResource("Images/blank.png").toString()));
                                building.setBlank(true);
                            }
                        }
                    }

                    System.out.println("game finished!!");
                    btn_back.setDisable(false);

                }).start();




    }

    @FXML
    void spawnJag(MouseEvent event) {

        Jug=new ImageView(new Image(Main.class.getResource("Images/jagAtt/1.png").toString()));
        Jug.setFitHeight(300);
        Jug.setFitWidth(350);
        Juggernuat juggernuat1=new Juggernuat();


        if (TroopsLimit>=juggernuat1.getCapacity())
        {
            anch_Map.getChildren().add(Jug);
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
    void spawnGol(MouseEvent event) {

        Jak=new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Idle/0_Golem_Idle_000.png").toString()));
        Jak.setFitHeight(100);
        Jak.setFitWidth(100);

        Golem golem1 =new Golem();

        if (TroopsLimit>= golem1.getCapacity())
        {
            anch_Map.getChildren().add(Jak);
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
    void spawnOrc(MouseEvent event) {

        orc=new ImageView(new Image(Main.class.getResource("Images/Orc/PNG/PNG Sequences/Idle/0_Orc_Idle_000.png").toString()));
        orc.setFitHeight(100);
        orc.setFitWidth(100);

        Orc orc1 =new Orc();

        if (TroopsLimit>= orc1.getCapacity())
        {
            anch_Map.getChildren().add(orc);
            MakeDraggable.makeDraggable(orc);

            orc.setOnMouseReleased(event1 -> {


                Orc orc2 =new Orc(map,orc);

                orc2.getImages().clear();
                orc2.getImages().add(orc);
                heroes.add(orc2);
                Thread t = new Thread(orc2);
                t.start();
                TroopsLimit-= orc2.getCapacity();

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

        wolf=new ImageView(new Image(Main.class.getResource("Images/werewolfsNorm.png").toString()));
        wolf.setFitHeight(80);
        wolf.setFitWidth(80);

        WereWolf wereWolf1=new WereWolf();

        if (TroopsLimit>=wereWolf1.getCapacity())
        {
            anch_Map.getChildren().add(wolf);
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
        if (heroes.size()==0)
        {
            return true;
        }

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
    void back(ActionEvent event) throws IOException {

        try {
            if (!checkAnyBuildingRemain())
            {
                Player loser=PlayerController.findByUsername(AttackController.enemyName);
                Player winner=PlayerController.findByUsername(SignUpController.userName);
                PlayerController.saveWinToDatabase(winner,loser);
            }


        } catch (ClassNotFoundException e) {

            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error while loading players");
            alert.setContentText("Error while loading players because of ClassNotFoundException");
            alert.show();

        } catch (SQLException e) {

            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error while loading players");
            alert.setContentText("Error while loading players because of ClassNotFoundException");
            alert.show();
        }


        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1366 , 763);
        Main.primaryStage.setTitle("User Page");
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();


    }


    public  ImageView archerTowerArrows(Building archerTower)
    {

        Bounds bounds = archerTower.getBuildingImage().localToParent(archerTower.getBuildingImage().getBoundsInLocal());

        double x=bounds.getCenterX();
        double y=bounds.getCenterY();

        ImageView arrow=new ImageView(new Image(Main.class.getResource("Images/arrow/1.png").toString()));

        arrow.setLayoutX(x);
        arrow.setLayoutY(y);

        arrow.setFitWidth(150);
        arrow.setFitHeight(150);

        anch_Map.getChildren().add(arrow);

        return arrow;
    }

    //---------------------------------------------------------------

    public  boolean checkAnyHeroRemain()
    {
        int remainingNumbers=0;

        if(heroes.size()==0)
        {
            return true;
        }

        for (Hero hero : Map1Controller.heroes)
        {
            if (!hero.isDead() && !hero.isBlank())
            {
                remainingNumbers++;
            }
        }

        if (remainingNumbers==0 && TroopsLimit<2)
        {
            return false;
        }

            return true;
    }

    //--------------------------------------------------------------------------------------------------
    public  ImageView wizardTowerFireBall(Building wizardTower)
    {

        Bounds bounds = wizardTower.getBuildingImage().localToParent(wizardTower.getBuildingImage().getBoundsInLocal());

        double x=wizardTower.getxPosition();
        double y=wizardTower.getyPosition();

        ImageView fire=new ImageView(new Image(Main.class.getResource("Images/fire/1.png").toString()));

        fire.setLayoutX(x);
        fire.setLayoutY(y);

        fire.setFitWidth(200);
        fire.setFitHeight(200);

        //anch_Map.getChildren().add(fire);

        return fire;
    }
    //----------------------------------------------------------------------------------------------------------------
    public  ImageView teslaElectricBall(Building tesla)
    {

        Bounds bounds = tesla.getBuildingImage().localToParent(tesla.getBuildingImage().getBoundsInLocal());

        double x=tesla.getxPosition();
        double y=tesla.getyPosition();

        ImageView electro=new ImageView(new Image(Main.class.getResource("Images/electro/9.png").toString()));

        electro.setLayoutX(x);
        electro.setLayoutY(y);

        electro.setFitWidth(200);
        electro.setFitHeight(200);

       // anch_Map.getChildren().add(electro);

        return electro;
    }

}


