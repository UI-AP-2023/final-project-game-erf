package com.example.Controller;

import javafx.scene.Node;

public class MakeDraggable {

    public static double mouseAnchorX;

    public static double mouseAnchorY;

    public static double X;

    public static double Y;

    public static void makeDraggable(Node node)
    {
        node.setOnMousePressed(event -> {
            mouseAnchorX=event.getX();
            mouseAnchorY=event.getY();
        });

        node.setOnMouseDragged(event -> {
            node.setLayoutX(event.getSceneX() -mouseAnchorX);
            node.setLayoutY(event.getSceneY() -mouseAnchorY);

        });

        node.setOnMouseReleased(event -> {

            X= node.getLayoutX();
            Y= node.getLayoutY();

            node.setOnMousePressed(null);
            node.setOnMouseDragged(null);
            node.setOnMouseReleased(null);

        });

    }

}
