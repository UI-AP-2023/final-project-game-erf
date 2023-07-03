package com.example.Model.Buildings;

import com.example.Model.BuildingKind;
import com.example.game_project.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WizardTower extends Building{
    public WizardTower(   double x, double y) {
        super(BuildingKind.Defensive, 3000, 95, 7, x, y, new ImageView(new Image(Main.class.getResource("Images/wizard_tower_15.png").toString())));
    }
}
