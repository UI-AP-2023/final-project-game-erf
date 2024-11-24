package com.example.Model;

import com.example.Model.Heroes.Hero;
import javafx.scene.image.ImageView;

public class ClosestHero {

    private double distance;
    private Hero hero;

    public ClosestHero(double distance, Hero hero) {
        this.distance = distance;
        this.hero = hero;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }
}
