package com.example.Model.Maps;

import com.example.Model.Buildings.Building;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Map {

    private ImageView mapImage;
    private ArrayList<Building>buildings=new ArrayList<Building>();
    private int troopsLimit;

    public Map(ImageView mapImage,  int troopsLimit) {
        this.mapImage = mapImage;
        this.troopsLimit = troopsLimit;
    }

    public ImageView getMapImage() {
        return mapImage;
    }

    public void setMapImage(ImageView mapImage) {
        this.mapImage = mapImage;
    }

    public int getTroopsLimit() {
        return troopsLimit;
    }

    public void setTroopsLimit(int troopsLimit) {
        this.troopsLimit = troopsLimit;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }
}
