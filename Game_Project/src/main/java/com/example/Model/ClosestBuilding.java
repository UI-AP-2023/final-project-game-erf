package com.example.Model;

import com.example.Model.Buildings.Building;

public class ClosestBuilding  {

    private Building building;
    private double distance;

    public ClosestBuilding(Building building, double distance) {
        this.building = building;
        this.distance = distance;
    }

    public Building getBuilding() {
        return building;
    }

    public double getDistance() {
        return distance;
    }
}
