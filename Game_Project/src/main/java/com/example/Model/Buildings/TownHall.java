package com.example.Model.Buildings;

import com.example.Model.BuildingKind;
import com.example.game_project.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TownHall extends Building{
    public TownHall(  double x, double y) {
        super(BuildingKind.Normal, 9600, 0,0, x, y, new ImageView(new Image(Main.class.getResource("Images/town_hall_15.png").toString())));
    }
}
