package com.example.Model.Maps;

import com.example.game_project.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Map4 extends Map{
    public Map4() {

        super(new ImageView(new Image(Main.class.getResource("Images/Map41.png").toString())), 8);
    }
}
