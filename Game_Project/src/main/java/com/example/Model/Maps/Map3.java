package com.example.Model.Maps;

import com.example.game_project.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Map3 extends Map{
    public Map3() {

        super(new ImageView(new Image(Main.class.getResource("Images/Map31.png").toString())),16);
    }
}
