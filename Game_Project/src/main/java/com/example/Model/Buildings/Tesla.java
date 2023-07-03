package com.example.Model.Buildings;

import com.example.Model.BuildingKind;
import com.example.game_project.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tesla extends Building{
    public Tesla( double x, double y) {
        super(BuildingKind.Defensive, 1350, 150, 6, x, y, new ImageView(new Image(Main.class.getResource("Images/hidden_tesla_13.png").toString())));
    }
}
