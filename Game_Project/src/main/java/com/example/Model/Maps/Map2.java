package com.example.Model.Maps;

import com.example.game_project.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Map2 extends Map{
    public Map2() {

        super(new ImageView(new Image(Main.class.getResource("Images/Map21.png").toString())),3);
    }
}
