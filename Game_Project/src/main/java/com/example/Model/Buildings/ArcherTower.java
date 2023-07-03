package com.example.Model.Buildings;

import com.example.Model.BuildingKind;
import com.example.game_project.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ArcherTower extends Building{

    public ArcherTower( double x, double y) {
        super(BuildingKind.Defensive, 1800, 145, 10, x, y, new ImageView(new Image(Main.class.getResource("Images/archer_tower_21.png").toString())));
    }
}
