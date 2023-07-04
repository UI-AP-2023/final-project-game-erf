package com.example.Model.Buildings;

import com.example.Model.BuildingKind;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public abstract class Building {

    private BuildingKind buildingKind;
    private double buildingHealth;
    private double attackDamage;
    private double buildingRange;
    private ImageView buildingImage;
    private double xPosition;
    private double yPosition;
    private boolean destroyed=false;
    private boolean blank=false;

    public Building(BuildingKind buildingKind, double buildingHealth, double attackDamage, double buildingRange, double x, double y , ImageView buildingImage) {
        this.buildingKind = buildingKind;
        this.buildingHealth = buildingHealth;
        this.attackDamage = attackDamage;
        this.buildingRange = buildingRange;
        this.buildingImage=buildingImage;
        this.xPosition = x;
        this.yPosition = y;
    }

    public BuildingKind getBuildingKind() {
        return buildingKind;
    }

    public double getBuildingHealth() {
        return buildingHealth;
    }

    public double getAttackDamage() {
        return attackDamage;
    }

    public double getBuildingRange() {
        return buildingRange;
    }
    
    public double getxPosition() {
        return xPosition;
    }

    public double getyPosition() {
        return yPosition;
    }

    public ImageView getBuildingImage() {
        return buildingImage;
    }

    public void setBuildingHealth(double buildingHealth) {
        this.buildingHealth = buildingHealth;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public boolean isBlank() {
        return blank;
    }

    public void setBlank(boolean blank) {
        this.blank = blank;
    }
}
