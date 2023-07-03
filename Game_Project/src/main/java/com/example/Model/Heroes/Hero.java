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


    public Hero(double heroHealth, double heroAttackDamage, boolean isRangeAttack, double range, long attackSpeed, double movementSpeed, int capacity) {
        this.heroHealth = heroHealth;
        this.heroAttackDamage = heroAttackDamage;
        IsRangeAttack = isRangeAttack;
        this.range = range;
        this.attackSpeed = attackSpeed;
        this.movementSpeed = movementSpeed;
        this.capacity = capacity;
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

}
