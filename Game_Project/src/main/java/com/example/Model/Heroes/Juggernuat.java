package com.example.Model.Heroes;

import com.example.Model.Buildings.Building;
import com.example.Model.ClosestBuilding;
import com.example.Model.Maps.Map;
import com.example.game_project.Main;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;


public class Juggernuat extends Hero implements Runnable{

    private Map map;
    private ImageView heroInBattleField;
    private ImageView buildingImg;


    public Juggernuat() {

        super(6700, 400, false, 0, 2000, 16, 6);
        getImages().add(new ImageView(new Image(Main.class.getResource("Images/jag.png").toString())));

    }

    public Juggernuat(Map map1,ImageView hero) {

        super(6700, 400, false, 0, 2000, 16, 6);
        getImages().add(new ImageView(new Image(Main.class.getResource("Images/jag.png").toString())));
        this.heroInBattleField=hero;
        this.map=map1;
    }


    @Override
    public void run() {

        while(map.getBuildings().size() > 0 && this.getHeroHealth()>0) {

            System.out.println("the image view Before movement position(X): "+this.heroInBattleField.getLayoutX()+"  (Y):"+this.heroInBattleField.getLayoutY());
            Building building=findTheClosestBuilding(this.map,this.heroInBattleField);
            heroMovement(building,this.heroInBattleField);
            System.out.println("the image view After movement position(X): "+this.heroInBattleField.getTranslateX()+"  (Y):"+this.heroInBattleField.getTranslateY());

            try {
                boolean attackResult=attackOnBuilding(building);
                if (attackResult)
                {
                    this.map.getBuildings().remove(building);
                    buildingImg=null;
                }
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

        for (Building building:map.getBuildings())
        {
            xDifference=Math.abs(building.getxPosition()- heroImageView.getLayoutX());
            yDifference=Math.abs(building.getyPosition()- heroImageView.getLayoutY());
            xDifference=Math.pow(xDifference,2);
            yDifference=Math.pow(yDifference,2);

            distance=Math.sqrt(xDifference+yDifference);
            distances.add(new ClosestBuilding(building, distance));
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
    public  void heroMovement(Building building,ImageView HeroImageView)
    {
        TranslateTransition t1=new TranslateTransition();
        t1.setByX(building.getxPosition()-HeroImageView.getLayoutX());
        t1.setByY(building.getyPosition()-HeroImageView.getLayoutY());

        
        //edit the movementSpeed------------------------------------------------------------kqekcmecemc
        t1.setNode(HeroImageView);
        Juggernuat juggernuat=new Juggernuat();
        t1.setDuration(Duration.millis(7000/juggernuat.getMovementSpeed()));

        t1.setCycleCount(1);
        t1.play();
    }

    //=======================Hero Attack ===============================================================================

    public boolean attackOnBuilding(Building building) throws InterruptedException {
        while (building.getBuildingHealth()>0 && this.getHeroHealth()>0)
        {
            building.setBuildingHealth(building.getBuildingHealth()-this.getHeroAttackDamage());
            Thread.sleep(java.time.Duration.ofMillis(this.getAttackSpeed()));
            System.out.println("building health: "+building.getBuildingHealth());
        }
        if (building.getBuildingHealth()<=0)
        {
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
}
