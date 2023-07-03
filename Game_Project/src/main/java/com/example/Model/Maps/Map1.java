package com.example.Model.Maps;

import com.example.game_project.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Map1 extends Map{
    public Map1() {

        super(new ImageView(new Image(Main.class.getResource("Images/Map11.png").toString())),9);
    }
}
