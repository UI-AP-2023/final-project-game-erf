package com.example.Model.Heroes;

import com.example.Model.BuildingKind;
import com.example.Model.Buildings.Building;
import com.example.Model.ClosestBuilding;
import com.example.Model.Maps.Map;
import com.example.game_project.Main;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;

public class Golem extends Hero implements Runnable{

    private Map map;
    private ImageView heroInBattleField;
    private ArrayList<ImageView>die=new ArrayList<>();
    private ArrayList<ImageView>hurt=new ArrayList<>();

    public Golem() {
        super(5100, 100, true, 3, 3500, 13, 6);
        getImages().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Idle/0_Golem_Idle_000.png").toString())));
    }

    public Golem(Map map1, ImageView hero) {

        super(5100, 100, true, 3, 3500, 13, 6);
        getImages().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Idle/0_Golem_Idle_000.png").toString())));
        this.setHeroImage(hero);
        this.map=map1;
        initHeroMoves();
        initHeroAttack();
        initHeroDying();
        initHeroHurting();
        this.setHerox(hero.getLayoutX());
        this.setHeroy(hero.getLayoutY());
    }

    @Override
    public void run() {

        while(checkAnyBuildingRemain() && this.getHeroHealth()>0) {

            System.out.println("the image view Before movement position(X): "+this.getHeroImage().getLayoutX()+"  (Y):"+this.getHeroImage().getLayoutY());
            healthCheck();
            Building building=findTheClosestBuilding(this.map,this.getHeroImage());
            heroMovement(building,this.getHeroImage());
            System.out.println("the image view After movement position(X): "+this.getHeroImage().getTranslateX()+"  (Y):"+this.getHeroImage().getTranslateY());

                boolean attackResult=attackOnBuilding(building);

        }

            for (ImageView imageView :getDie())
            {
                this.getCurrentImage().setImage(imageView.getImage());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Error while HeroDying!!");
                    alert.show();

                }
            }


    }

    public  Building findTheClosestBuilding(Map map,ImageView heroImageView)
    {
        double distance=0;
        double xDifference=0;
        double yDifference=0;


        ArrayList<ClosestBuilding>distances=new ArrayList<ClosestBuilding>();

        Bounds localBounds=heroImageView.localToParent(heroImageView.getBoundsInLocal());
        double x=localBounds.getCenterX();
        double y=localBounds.getCenterY();


        for (Building building:map.getBuildings() )
        {
            if (!building.isDestroyed()  && building.getBuildingKind()== BuildingKind.Normal)
            {

                xDifference=building.getxPosition()- heroImageView.getLayoutX();
                yDifference=building.getyPosition()- heroImageView.getLayoutY();
                xDifference=Math.pow(xDifference,2);
                yDifference=Math.pow(yDifference,2);

                distance=Math.sqrt(xDifference+yDifference);
                distances.add(new ClosestBuilding(building, distance));
            }

        }

        distance=0;

        distance=distances.get(0).getDistance();
        Building building=distances.get(0).getBuilding();

        for (int i=1; i<distances.size(); i++)
        {
            if (distances.get(i).getDistance()<=distance)
            {
                distance=distances.get(i).getDistance();
                building=distances.get(i).getBuilding();
            }
        }

        return building;
    }

    //=======================Hero moves=================================================================================
    public  void heroMovement(Building building,ImageView HeroImageView)  {

        Bounds localBounds=HeroImageView.localToParent(HeroImageView.getBoundsInLocal());

        TranslateTransition t1=new TranslateTransition();

        t1.setByX(building.getxPosition()-localBounds.getCenterX());
        t1.setByY(building.getyPosition()-localBounds.getCenterY());

        if (building.getxPosition()-localBounds.getCenterX()<0)
        {
            HeroImageView.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        }

        if (building.getxPosition()-localBounds.getCenterX()>0)
        {
            HeroImageView.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        }


        //edit the movementSpeed------------------------------------------------------------kqekcmecemc
        t1.setNode(HeroImageView);
        Golem golem=new Golem();

        t1.setDuration(Duration.millis(120000/golem.getMovementSpeed()));

        t1.setCycleCount(1);
        t1.play();

        while (!(t1.getStatus()== Animation.Status.STOPPED))
        {
            for (int i=0 ; i<getHeroMoves().size() ; i++)
            {
                this.getCurrentImage().setImage(getHeroMoves().get(i).getImage());
                this.setHerox(getCurrentImage().getLayoutX());
                this.setHeroy(getCurrentImage().getLayoutY());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Error while waiting for hero movement!!");
                    alert.show();
                }
            }
        }

        t1.setOnFinished(event -> {

            this.getCurrentImage().setImage(new Image(Main.class.getResource("Images/jagAtt/1.png").toString()));
            this.setHerox(getCurrentImage().getLayoutX());
            this.setHeroy(getCurrentImage().getLayoutY());

        });

        this.setHerox(HeroImageView.getLayoutX());
        this.setHeroy(HeroImageView.getLayoutY());
    }

    //=======================Hero Attack ===============================================================================

    public boolean attackOnBuilding(Building building)  {
        while (building.getBuildingHealth()>0 && this.getHeroHealth()>0)
        {
            for (int i=0 ; i<getHeroAtt().size() ;i++)
            {
                this.getCurrentImage().setImage(getHeroAtt().get(i).getImage());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Error while waiting for hero attack!!");
                    alert.show();
                }
            }
            building.setBuildingHealth(building.getBuildingHealth()-this.getHeroAttackDamage());
            try {
                Thread.sleep(java.time.Duration.ofMillis(this.getAttackSpeed()));
            } catch (InterruptedException e) {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Error while waiting for hero attack!!");
                alert.show();
            }
            System.out.println("building health: "+building.getBuildingHealth());
        }
        if (building.getBuildingHealth()<=0)
        {
            building.setDestroyed(true);
            return true;
        }
        else
            return false;
    }

    //=====================deleting image of the building===============================================================

    public void deleteBuilding(AnchorPane anchorPane, ImageView building)
    {
        anchorPane.getChildren().remove(building);
    }

    //======================check if any building is remain=============================================================

    public  boolean checkAnyBuildingRemain()
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


    public void initHeroMoves()
    {
        getHeroMoves().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Running/0_Golem_Running_000.png").toString())));
        getHeroMoves().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Running/0_Golem_Running_001.png").toString())));
        getHeroMoves().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Running/0_Golem_Running_002.png").toString())));
        getHeroMoves().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Running/0_Golem_Running_003.png").toString())));
        getHeroMoves().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Running/0_Golem_Running_004.png").toString())));
        getHeroMoves().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Running/0_Golem_Running_005.png").toString())));
        getHeroMoves().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Running/0_Golem_Running_006.png").toString())));
        getHeroMoves().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Running/0_Golem_Running_007.png").toString())));
        getHeroMoves().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Running/0_Golem_Running_008.png").toString())));
        getHeroMoves().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Running/0_Golem_Running_009.png").toString())));
        getHeroMoves().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Running/0_Golem_Running_010.png").toString())));
        getHeroMoves().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Running/0_Golem_Running_011.png").toString())));

    }

    public void initHeroAttack()
    {
        getHeroAtt().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Slashing/0_Golem_Slashing_000.png").toString())));
        getHeroAtt().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Slashing/0_Golem_Slashing_001.png").toString())));
        getHeroAtt().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Slashing/0_Golem_Slashing_002.png").toString())));
        getHeroAtt().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Slashing/0_Golem_Slashing_003.png").toString())));
        getHeroAtt().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Slashing/0_Golem_Slashing_004.png").toString())));
        getHeroAtt().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Slashing/0_Golem_Slashing_005.png").toString())));
        getHeroAtt().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Slashing/0_Golem_Slashing_006.png").toString())));
        getHeroAtt().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Slashing/0_Golem_Slashing_007.png").toString())));
        getHeroAtt().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Slashing/0_Golem_Slashing_008.png").toString())));
        getHeroAtt().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Slashing/0_Golem_Slashing_009.png").toString())));
        getHeroAtt().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Slashing/0_Golem_Slashing_010.png").toString())));
        getHeroAtt().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Slashing/0_Golem_Slashing_011.png").toString())));

    }

    public void initHeroDying()
    {
        getDie().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Dying/0_Golem_Dying_000.png").toString())));
        getDie().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Dying/0_Golem_Dying_001.png").toString())));
        getDie().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Dying/0_Golem_Dying_002.png").toString())));
        getDie().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Dying/0_Golem_Dying_003.png").toString())));
        getDie().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Dying/0_Golem_Dying_004.png").toString())));
        getDie().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Dying/0_Golem_Dying_005.png").toString())));
        getDie().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Dying/0_Golem_Dying_006.png").toString())));
        getDie().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Dying/0_Golem_Dying_007.png").toString())));
        getDie().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Dying/0_Golem_Dying_008.png").toString())));
        getDie().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Dying/0_Golem_Dying_009.png").toString())));
        getDie().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Dying/0_Golem_Dying_010.png").toString())));
        getDie().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Dying/0_Golem_Dying_011.png").toString())));
        getDie().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Dying/0_Golem_Dying_012.png").toString())));
        getDie().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Dying/0_Golem_Dying_013.png").toString())));
        getDie().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Dying/0_Golem_Dying_014.png").toString())));

    }

    public void initHeroHurting()
    {
        getHurt().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Hurt/0_Golem_Hurt_000.png").toString())));
        getHurt().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Hurt/0_Golem_Hurt_001.png").toString())));
        getHurt().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Hurt/0_Golem_Hurt_002.png").toString())));
        getHurt().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Hurt/0_Golem_Hurt_003.png").toString())));
        getHurt().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Hurt/0_Golem_Hurt_004.png").toString())));
        getHurt().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Hurt/0_Golem_Hurt_005.png").toString())));
        getHurt().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Hurt/0_Golem_Hurt_006.png").toString())));
        getHurt().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Hurt/0_Golem_Hurt_007.png").toString())));
        getHurt().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Hurt/0_Golem_Hurt_008.png").toString())));
        getHurt().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Hurt/0_Golem_Hurt_009.png").toString())));
        getHurt().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Hurt/0_Golem_Hurt_010.png").toString())));
        getHurt().add(new ImageView(new Image(Main.class.getResource("Images/Golem_2/PNG/PNG Sequences/Hurt/0_Golem_Hurt_011.png").toString())));
    }

    public void healthCheck()  {

        if (this.getHeroHealth()<this.getInitialHealth())
        {
            for (ImageView imageView :getHurt())
            {
                this.getCurrentImage().setImage(imageView.getImage());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
            }
            this.setInitialHealth(this.getHeroHealth());
        }
    }


    public ArrayList<ImageView> getHurt() {
        return hurt;
    }

    public ArrayList<ImageView> getDie() {
        return die;
    }
}
