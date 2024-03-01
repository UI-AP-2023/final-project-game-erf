module com.example.game_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.game_project to javafx.fxml;
    exports com.example.game_project;
    exports com.example.Controller;
}