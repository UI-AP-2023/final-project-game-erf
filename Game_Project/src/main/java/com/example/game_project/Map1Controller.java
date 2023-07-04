package com.example.game_project;

import com.example.Controller.MakeDraggable;
import com.example.Controller.PlayerController;
import com.example.Model.Buildings.*;
import com.example.Model.ClosestHero;
import com.example.Model.Heroes.*;
import com.example.Model.Maps.Map;
import com.example.Model.Maps.Map1;
import com.example.Model.Player;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
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
import javafx.util.Duration;

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

    private  static Map1 map;

    public static ArrayList<Hero>heroes = new ArrayList<Hero>();

    public static ArrayList<ImageView>heroesImage=new ArrayList<ImageView>();

    private int TroopsLimit=20;

    private ArcherTower archerTowertemp;

    @FXML
    private Button btn_back;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        hbox_Heroes.setVisible(true);

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


    }

    @FXML
    void start(ActionEvent event) {

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
//                        while(checkAnyHeroIsAlive() && checkAnyBuildingRemain()) {
//                            for (Hero hero : heroes) {
//                                if (hero.isDead()) {
//                                    if (!hero.isBlank()) {
//                                        System.out.println("Hero " + hero.getClass().getSimpleName() + " is dead");
//                                        hero.getHeroImage().setImage(new Image(Main.class.getResource("Images/blank.png").toString()));
//                                    }
//                                }
//                            }
//                        }
                    }while (checkAnyBuildingRemain());

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

                ImageView arrow=archerTowerArrows(archerTowertemp);

        new Thread(() ->
        {
            while(checkAnyHeroRemain())
            {
                System.out.println("Archer tower thread is running!");
                Hero hero=checkBuildingRange(archerTowertemp);
                try {
                    attackTheHeroes(archerTowertemp,hero,arrow);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                    Alert alert =new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error while attacking the hero");
                    alert.setContentText("the attack is interrupted because of InterruptedException!!");
                    alert.show();
                }
            }

        }).start();


        //ImageView arrow=archerTowerArrows(archerTowertemp);

//        new Thread(new ArcherTower(img_ArcherTower.getLayoutX(), img_ArcherTower.getLayoutY(),arrow)).start();


    }

    @FXML
    void spawnJag(MouseEvent event) {

        Jug=new ImageView(new Image(Main.class.getResource("Images/jag.png").toString()));
        Jug.setFitHeight(100);
        Jug.setFitWidth(100);
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
    void spawnJak(MouseEvent event) {

        Jak=new ImageView(new Image(Main.class.getResource("Images/jakiro.png").toString()));
        Jak.setFitHeight(100);
        Jak.setFitWidth(100);

        Jakiro jakiro1=new Jakiro();

        if (TroopsLimit>=jakiro1.getCapacity())
        {
            anch_Map.getChildren().add(Jak);
            MakeDraggable.makeDraggable(Jak);

            Jak.setOnMouseReleased(event1 -> {

                Jakiro jakiro=new Jakiro(map,Jak);

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
        Raz.setFitHeight(60);
        Raz.setFitWidth(60);

        Razor razor1=new Razor();

        if (TroopsLimit>=razor1.getCapacity())
        {
            anch_Map.getChildren().add(Raz);
            MakeDraggable.makeDraggable(Raz);

            Raz.setOnMouseReleased(event1 -> {


                Razor razor=new Razor(map,Raz);

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

        double x=archerTower.getxPosition();
        double y=archerTower.getyPosition();
        ImageView arrow=new ImageView(new Image(Main.class.getResource("Images/Arrow.png").toString()));

        arrow.setLayoutX(x);
        arrow.setLayoutY(y);

        arrow.setFitWidth(50);
        arrow.setFitHeight(50);

        anch_Map.getChildren().add(arrow);

        return arrow;
    }

    public Hero checkBuildingRange(Building building)
    {
        double distance=0;
        double xDifference=0;
        double yDifference=0;

        System.out.println("enter the range method");

        ArrayList<ClosestHero> distances=new ArrayList<ClosestHero>();

        for (Hero hero:Map1Controller.heroes)
        {
            if (!hero.isDead())
            {

                Bounds localBounds=hero.getHeroImage().localToParent(hero.getHeroImage().getBoundsInLocal());

                double x=localBounds.getCenterX();
                double y=localBounds.getCenterY();

                xDifference=building.getxPosition()- x;
                yDifference=building.getyPosition()- y;
                xDifference=Math.pow(xDifference,2);
                yDifference=Math.pow(yDifference,2);

                distance=Math.sqrt(xDifference+yDifference);
                distances.add(new ClosestHero(distance, hero));
                System.out.println("size: "+distances.size());
            }

        }


        distance=Double.MAX_VALUE;
        Hero hero=null;

        for (int i=0; i<distances.size(); i++)
        {
            if (distances.get(i).getDistance()<=distance && distances.get(i).getDistance()<=building.getBuildingRange())
            {
                distance=distances.get(i).getDistance();
                hero=distances.get(i).getHero();
            }
        }

        if (hero!=null)
        {
            System.out.println(hero.getClass().getSimpleName() + "Position: "+hero.getHeroImage().getLayoutX()+ " "+hero.getHeroImage().getLayoutY());

        }
        return hero;
    }

    //==============Attack the hero that enter the range of this tower==================================================

    public boolean attackTheHeroes(Building building,Hero hero,ImageView arrow) throws InterruptedException {


        if (hero!=null) {

            double x=hero.getHerox();
            double y=hero.getHeroy();

            Bounds localBoundsArrows=arrow.localToParent(arrow.getBoundsInLocal());


            TranslateTransition attack=new TranslateTransition();

            attack.setByX(x-building.getxPosition());
            attack.setByY(y-building.getyPosition());

            attack.setNode(arrow);
            attack.setDuration(Duration.millis(1000));
            attack.setCycleCount(100);

            attack.play();


            hero.setHeroHealth(hero.getHeroHealth()-building.getAttackDamage());
            TranslateTransition reverse=new TranslateTransition();
            reverse.setNode(arrow);
            reverse.setByX(building.getxPosition()-arrow.getLayoutX());
            reverse.setByY(building.getyPosition()-arrow.getLayoutY());

            reverse.setDuration(Duration.millis(100));
            reverse.setCycleCount(1);
            reverse.play();



            System.out.println("hero Health: " + hero.getHeroHealth());


            if (hero.getHeroHealth()<=0)
            {
                hero.setDead(true);
                return true;
            }
            else
                return false;
        }
        return false;

    }

    //---------------------------------------------------------------

    public  boolean checkAnyHeroRemain()
    {
        int remainingNumbers=0;

        for (Hero hero : Map1Controller.heroes)
        {
            if (!hero.isDead())
            {
                remainingNumbers++;
            }
        }

        if (remainingNumbers==0)
        {
            return true;
        }
        else
            return false;
    }


}


