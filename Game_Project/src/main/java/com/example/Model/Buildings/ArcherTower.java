package com.example.Model.Buildings;

import com.example.Model.BuildingKind;
import com.example.Model.ClosestBuilding;
import com.example.Model.ClosestHero;
import com.example.Model.Heroes.Hero;
import com.example.game_project.Main;
import com.example.game_project.Map1Controller;
import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;

public class ArcherTower extends Building implements Runnable{

    ImageView arrow;

    public ArcherTower( double x, double y) {
        super(BuildingKind.Defensive, 1800, 2000, 200, x, y, new ImageView(new Image(Main.class.getResource("Images/archer_tower_21.png").toString())));
    }

    public ArcherTower( double x, double y,ImageView arrow) {
        super(BuildingKind.Defensive, 1800, 2000, 200, x, y, new ImageView(new Image(Main.class.getResource("Images/archer_tower_21.png").toString())));
        this.arrow=arrow;
    }

    @Override
    public void run() {

        while(checkAnyHeroRemain())
        {
            System.out.println("Archer tower thread is running!");
            Hero hero=checkBuildingRange();
            try {
                attackTheHeroes(hero);
                Thread.sleep(1000);
            } catch (InterruptedException e) {

                Alert alert =new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error while attacking the hero");
                alert.setContentText("the attack is interrupted because of InterruptedException!!");
                alert.show();
            }
        }

    }

    //==================check the range of building=====================================================================
    public Hero checkBuildingRange()
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

                    xDifference=this.getxPosition()- x;
                    yDifference=this.getyPosition()- y;
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
                if (distances.get(i).getDistance()<=distance && distances.get(i).getDistance()<=this.getBuildingRange())
                {
                    distance=distances.get(i).getDistance();
                    hero=distances.get(i).getHero();
                }
            }

            if (hero!=null)
            {
                System.out.println(hero.getClass().getSimpleName() + "Position: "+hero.getHeroImage().getLayoutX()+ ""+hero.getHeroImage().getLayoutY());

            }
            return hero;
    }

    //==============Attack the hero that enter the range of this tower==================================================

    public boolean attackTheHeroes(Hero hero) throws InterruptedException {


        if (hero!=null) {

            double x=hero.getHerox();
            double y=hero.getHeroy();

            Bounds localBoundsArrows=arrow.localToParent(arrow.getBoundsInLocal());


                TranslateTransition attack=new TranslateTransition();

                attack.setByX(x-this.getxPosition());
                attack.setByY(y-this.getyPosition());

                attack.setNode(arrow);
                attack.setDuration(Duration.millis(1000));
                attack.setCycleCount(100);

                attack.play();


                    hero.setHeroHealth(hero.getHeroHealth()-this.getAttackDamage());
                    TranslateTransition reverse=new TranslateTransition();
                    reverse.setNode(arrow);
                    reverse.setByX(this.getxPosition()-arrow.getLayoutX());
                    reverse.setByY(this.getyPosition()-arrow.getLayoutY());

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
