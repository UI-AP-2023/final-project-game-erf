package com.example.Model.Buildings;

import com.example.Model.BuildingKind;
import com.example.Model.ClosestHero;
import com.example.Model.Heroes.Hero;
import com.example.game_project.Main;
import com.example.game_project.Map1Controller;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;

public class Tesla extends Building{

    private ArrayList<ImageView> electro=new ArrayList<>();

    public Tesla( double x, double y) {
        super(BuildingKind.Defensive, 13500, 60, 60, x, y, new ImageView(new Image(Main.class.getResource("Images/hidden_tesla_13.png").toString())));
    }

    public Tesla( double x, double y,ImageView electric) {
        super(BuildingKind.Defensive, 13500, 60, 60, x, y, new ImageView(new Image(Main.class.getResource("Images/hidden_tesla_13.png").toString())));
        initElectric();
        this.setCurrentImage(electric);
    }

    public void startAll() {

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

    //==================check the range of building=====================================================================
    public Hero checkBuildingRange()
    {
        double distance=0;
        double xDifference=0;
        double yDifference=0;

        System.out.println("enter the range method");

        ArrayList<ClosestHero> distances=new ArrayList<ClosestHero>();
        System.out.println(" hero size: "+Map1Controller.heroes.size());

        Hero hero=null;


        for (Hero hero1:Map1Controller.heroes)
        {
            if (!hero1.isDead() && !hero1.isBlank())
            {
                Bounds bounds=hero1.getCurrentImage().localToParent(hero1.getCurrentImage().getBoundsInLocal());

                xDifference=this.getxPosition()-bounds.getCenterX() ;
                yDifference=this.getyPosition()- bounds.getCenterY();
                xDifference=Math.pow(xDifference,2);
                yDifference=Math.pow(yDifference,2);

                distance=Math.sqrt(xDifference+yDifference);
                distances.add(new ClosestHero(distance, hero1));
                System.out.println("size: "+distances.size());
            }

        }

        if (distances.size()>0)
        {

            distance=distances.get(0).getDistance();
            hero=distances.get(0).getHero();

            for (ClosestHero closestHero : distances) {

                if (closestHero.getDistance() <= distance ) {
                    distance = closestHero.getDistance();
                    System.out.println("distance: " + distance);
                    hero = closestHero.getHero();
                    System.out.println("hero: " + hero.toString());

                }
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


            while(hero.getHeroHealth()>0 && this.getBuildingHealth()>0)
            {

//                TranslateTransition attack=new TranslateTransition();
//
//                Bounds bounds1=hero.getHeroImage().localToParent(hero.getCurrentImage().getBoundsInLocal());
//
//                attack.setByX(bounds1.getCenterX()-this.getxPosition());
//                attack.setByY(bounds1.getCenterY()-this.getyPosition());
//
//
//                if (bounds1.getCenterX()-this.getxPosition()<0)
//                {
//                    getCurrentImage().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
//                }
//
//
//                if (bounds1.getCenterX()-this.getxPosition()>0)
//                {
//                    this.getCurrentImage().setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
//                }
//
//                attack.setNode(getCurrentImage());
//                attack.setDuration(Duration.millis(2000));
//                attack.setCycleCount(1);
//
//                attack.play();
//                Thread.sleep(2000);
//
//                attack.setOnFinished(event -> {
//
                    hero.setHeroHealth(hero.getHeroHealth()-this.getAttackDamage());
                    System.out.println("hero health: "+hero.getHeroHealth() );
                    Thread.sleep(2000);
//
//                });
//
//
//                TranslateTransition reverse=new TranslateTransition();
//                reverse.setNode(getCurrentImage());
//
//                reverse.setByX(-attack.getByX());
//                reverse.setByY(-attack.getByY());
//
//                reverse.setDuration(Duration.millis(10));
//                reverse.setCycleCount(1);
//                reverse.play();
//                Thread.sleep(10);
            }





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

        if (Map1Controller.heroes.size()==0)
        {
            return true;
        }

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

    public ArrayList<ImageView> getElectro() {
        return electro;
    }

    public void initElectric()
    {
        getElectro().add(new ImageView(new Image(Main.class.getResource("Images/electro/9.png").toString())));
    }
}

