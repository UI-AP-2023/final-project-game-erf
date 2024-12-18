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


public class Juggernuat extends Hero implements Runnable{

    private Map map;
    private ImageView buildingImg;


    public Juggernuat() {

        super(6700, 50, false, 0, 2000, 16, 3);
        getImages().add(new ImageView(new Image(Main.class.getResource("Images/jag.png").toString())));

    }

    public Juggernuat(Map map1,ImageView hero) {

        super(6700, 50, false, 0, 2000, 16, 3);
        getImages().add(new ImageView(new Image(Main.class.getResource("Images/jag.png").toString())));
        this.setHeroImage(hero);
        this.map=map1;
        initHeroAttack();
        initHeroMoves();
        this.setHerox(hero.getLayoutX());
        this.setHeroy(hero.getLayoutY());
    }


    @Override
    public void run() {

        while(checkAnyBuildingRemain() && this.getHeroHealth()>0) {

            System.out.println("the image view Before movement position(X): "+this.getHeroImage().getLayoutX()+"  (Y):"+this.getHeroImage().getLayoutY());
            Building building=findTheClosestBuilding(this.map,this.getHeroImage());
            heroMovement(building,this.getHeroImage());
            System.out.println("the image view After movement position(X): "+this.getHeroImage().getTranslateX()+"  (Y):"+this.getHeroImage().getTranslateY());

            try {
                boolean attackResult=attackOnBuilding(building);

            } catch (InterruptedException e) {

                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Attack Failed!!");
                alert.setContentText("Attack Failed because of InterruptedException!!");
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


        for (Building building:map.getBuildings())
        {
            if (!building.isDestroyed() )
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

        this.buildingImg=building.getBuildingImage();
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
        Juggernuat juggernuat=new Juggernuat();
        t1.setDuration(Duration.millis(70000/juggernuat.getMovementSpeed()));

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

    public boolean attackOnBuilding(Building building) throws InterruptedException {
        while (building.getBuildingHealth()>0 && this.getHeroHealth()>0)
        {
            for (int i=0 ; i<getHeroAtt().size() ;i++)
            {
                this.getCurrentImage().setImage(getHeroAtt().get(i).getImage());
                Thread.sleep(100);
            }
            building.setBuildingHealth(building.getBuildingHealth()-this.getHeroAttackDamage());
            Thread.sleep(java.time.Duration.ofMillis(this.getAttackSpeed()));
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
    //========================================================================
    public void initHeroMoves()
    {
        getHeroMoves().add(new ImageView(new Image(Main.class.getResource("Images/jagMov/1.png").toString())));
        getHeroMoves().add(new ImageView(new Image(Main.class.getResource("Images/jagMov/2.png").toString())));
        getHeroMoves().add(new ImageView(new Image(Main.class.getResource("Images/jagMov/3.png").toString())));
        getHeroMoves().add(new ImageView(new Image(Main.class.getResource("Images/jagMov/4.png").toString())));
        getHeroMoves().add(new ImageView(new Image(Main.class.getResource("Images/jagMov/5.png").toString())));
        getHeroMoves().add(new ImageView(new Image(Main.class.getResource("Images/jagMov/6.png").toString())));
        getHeroMoves().add(new ImageView(new Image(Main.class.getResource("Images/jagMov/7.png").toString())));
        getHeroMoves().add(new ImageView(new Image(Main.class.getResource("Images/jagMov/8.png").toString())));
        getHeroMoves().add(new ImageView(new Image(Main.class.getResource("Images/jagMov/9.png").toString())));
        getHeroMoves().add(new ImageView(new Image(Main.class.getResource("Images/jagMov/10.png").toString())));
        getHeroMoves().add(new ImageView(new Image(Main.class.getResource("Images/jagMov/11.png").toString())));
    }

    public void initHeroAttack()
    {
        getHeroAtt().add(new ImageView(new Image(Main.class.getResource("Images/jagAtt/1.png").toString())));
        getHeroAtt().add(new ImageView(new Image(Main.class.getResource("Images/jagAtt/2.png").toString())));
        getHeroAtt().add(new ImageView(new Image(Main.class.getResource("Images/jagAtt/3.png").toString())));
        getHeroAtt().add(new ImageView(new Image(Main.class.getResource("Images/jagAtt/4.png").toString())));
    }

}
