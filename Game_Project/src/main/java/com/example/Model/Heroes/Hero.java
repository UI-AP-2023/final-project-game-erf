package com.example.Model.Heroes;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

public abstract class Hero {

    private double heroHealth;
    private double heroAttackDamage;
    private boolean IsRangeAttack;
    private double range;
    private long attackSpeed;
    private double movementSpeed;
    private int capacity;
    public static ArrayList<ImageView> images=new ArrayList<>();
    public boolean Dead=false;
    public boolean blank=false;
    private ImageView currentImage;
    private double Herox;
    private double Heroy;
    private ArrayList<ImageView>heroMoves=new ArrayList<>();
    private ArrayList<ImageView>heroAtt=new ArrayList<>();
    private double initialHealth;




    public Hero(double heroHealth, double heroAttackDamage, boolean isRangeAttack, double range, long attackSpeed, double movementSpeed, int capacity) {
        this.heroHealth = heroHealth;
        this.heroAttackDamage = heroAttackDamage;
        IsRangeAttack = isRangeAttack;
        this.range = range;
        this.attackSpeed = attackSpeed;
        this.movementSpeed = movementSpeed;
        this.capacity = capacity;
        this.initialHealth=heroHealth;
    }
    //------------------------------------------------------------

    public double getHeroHealth() {
        return heroHealth;
    }

    public double getHeroAttackDamage() {
        return heroAttackDamage;
    }

    public boolean isRangeAttack() {
        return IsRangeAttack;
    }

    public double getRange() {
        return range;
    }

    public long getAttackSpeed() {
        return attackSpeed;
    }

    public double getMovementSpeed() {
        return movementSpeed;
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<ImageView> getImages() {
        return images;
    }

    @Override
    public String toString() {
        return
                "heroHealth=" + heroHealth +
                        ", heroAttackDamage=" + heroAttackDamage +
                        ", IsRangeAttack=" + IsRangeAttack +
                        ", range=" + range +
                        ", attackSpeed=" + attackSpeed +
                        ", movementSpeed=" + movementSpeed +
                        ", capacity=" + capacity +
                        ", images=" + images
                ;
    }

    public boolean isDead() {
        return Dead;
    }

    public void setDead(boolean dead) {
        Dead = dead;
    }

    public ImageView getHeroImage() {
        return currentImage;
    }

    public boolean isBlank() {
        return blank;
    }

    public void setBlank(boolean blank) {
        this.blank = blank;
    }

    public void setHeroImage(ImageView heroImage) {
        this.currentImage = heroImage;
    }

    public double getHerox() {
        return Herox;
    }

    public void setHerox(double herox) {
        Herox = herox;
    }

    public double getHeroy() {
        return Heroy;
    }

    public void setHeroy(double heroy) {
        Heroy = heroy;
    }

    public void setHeroHealth(double heroHealth) {
        this.heroHealth = heroHealth;


    }

    public ArrayList<ImageView> getHeroMoves() {
        return heroMoves;
    }

    public ArrayList<ImageView> getHeroAtt() {
        return heroAtt;
    }

    public ImageView getCurrentImage() {
        return currentImage;
    }

    public double getInitialHealth() {
        return initialHealth;
    }

    public void setInitialHealth(double initialHealth) {
        this.initialHealth = initialHealth;
    }
}
