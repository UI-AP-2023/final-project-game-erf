package com.example.Controller;

import com.example.Exception.InvalidPassword;
import com.example.Model.*;
import com.example.Model.Heroes.Jakiro;
import com.example.Model.Heroes.Juggernuat;
import com.example.Model.Heroes.Razor;
import com.example.Model.Heroes.WereWolf;
import com.example.Model.Maps.*;
import com.example.game_project.Main;
import com.example.game_project.SignUpController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerController {

    public static ArrayList<Map>allMaps=new ArrayList<>();

    public static int playerLevel;

    public static ArrayList<Player>players=new ArrayList<>();


    public String signUp(String userName,String password) throws  ClassNotFoundException,SQLException, InvalidPassword {

        Pattern pattern2 = Pattern.compile("(\\S){8,}");
        Pattern pattern20 = Pattern.compile("[a-z]+");
        Pattern pattern21 = Pattern.compile("[A-Z]+");
        Pattern pattern22 = Pattern.compile("[0-9]+");
        Matcher matcher2 = pattern2.matcher(password);
        Matcher matcher20 = pattern20.matcher(password);
        Matcher matcher21 = pattern21.matcher(password);
        Matcher matcher22 = pattern22.matcher(password);


        if ( matcher2.find() && matcher20.find() && matcher21.find() && matcher22.find()) {
            boolean checkB=checkForDuplicateUserName(userName);
            boolean checkA=loginPlayersFromDatabase(userName,password);
            if (checkA)
            {
                return "This account already exists you can login now!";
            }

            else
            {
                if (!checkB)
                {

                    return "Sign up was successfully";
                }
                if (checkB)
                {
                    return "This username already exists";
                }
            }
        }
        else
        {
            throw new InvalidPassword();
        }
        return "Invalid Password";
    }

    //======================Login=======================================================================================

    public String logIn(String userName, String password) throws SQLException, ClassNotFoundException {

        boolean check=loginPlayersFromDatabase(userName,password);

        if (check)
        {
            return "Login was successful";
        }
        else
            return "Login failed";
    }

    //=================Write player username and password in data base==================================================

    public static void writePlayersToDatabase(String username,String password) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/clashofclans", "erf", "4013613056");
        String sqlCom="INSERT INTO players (UserName,Password) VALUES ('"+username+"'"+","+"'"+password+"')";
        Statement statement=connection.prepareStatement(sqlCom);
        statement.execute(sqlCom);
        connection.close();

    }

    //=================Read player username and password from  data base================================================

    public static boolean loginPlayersFromDatabase(String username,String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/clashofclans", "erf", "4013613056");
        String sqlCom="select UserName,Password FROM players";
        Statement s=connection.prepareStatement(sqlCom);
        ResultSet rs=s.executeQuery(sqlCom);
        while(rs.next())
        {
            if (rs.getString("UserName").equals(username) && rs.getString("Password").equals(password))
            {
                connection.close();
                return true;
            }
        }
        connection.close();
        return false;
    }
    //------------------------------------------------------------------------------------------------------------------
    public static boolean checkForDuplicateUserName(String username) throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/clashofclans", "erf", "4013613056");
        String sqlCom="select UserName FROM players";
        Statement s=connection.prepareStatement(sqlCom);
        ResultSet rs=s.executeQuery(sqlCom);
        while(rs.next())
        {
            if (rs.getString("UserName").equals(username))
            {
                connection.close();
                return true;
            }
        }
        connection.close();
        return false;
    }

    //===================================an ArrayList of all Maps Images================================================
    public static ArrayList<ImageView> allMapImages()
    {

        ArrayList<ImageView> images = new ArrayList<ImageView>();
        images.add(new ImageView(new Image(Main.class.getResource("Images/Map11.png").toString())));
        images.add(new ImageView(new Image(Main.class.getResource("Images/Map21.png").toString())));
        images.add(new ImageView(new Image(Main.class.getResource("Images/Map41.png").toString())));
        images.add(new ImageView(new Image(Main.class.getResource("Images/Map31.png").toString())));

        System.out.println(images.size()+" images size");

        return images;
    }

    //===================================selection of map===============================================================
    public static void mapSelection(ImageView mapImage) throws IOException {
        String name=SignUpController.userName;

        if (mapImage.getImage().getUrl().contains("Map11"))
        {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Map1.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),1366 , 763);

            createPlayerAfterMapSelection(allMaps.get(allMaps.size()-1));

        }

        if (mapImage.getImage().getUrl().contains("Map21"))
        {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Map2.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),1366 , 763);
            createPlayerAfterMapSelection(allMaps.get(allMaps.size()-1));

        }

        if (mapImage.getImage().getUrl().contains("Map31"))
        {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Map3.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),1366 , 763);
            createPlayerAfterMapSelection(allMaps.get(allMaps.size()-1));

        }

        if (mapImage.getImage().getUrl().contains("Map41"))
        {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Map4.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),1366 , 763);
            createPlayerAfterMapSelection(allMaps.get(allMaps.size()-1));

        }

    }

    //====================write player information in database==========================================================
    public static void writePlayerInfoInDataBase(Player player) throws ClassNotFoundException, SQLException {

        String username= player.getUserName();
        String password= player.getPassword();
        String mapName= player.getMap().getClass().getSimpleName();
        int level= player.getLevel();
        int wins= player.getPlayerWins();
        int losses= player.getPlayerLosses();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/clashofclans", "erf", "4013613056");
        String sqlCom="INSERT INTO players (UserName,Password,Level,Wins,Losses,MapName) VALUES ('"+username+"'"+","+"'"+password+"'"+","+"'"+ level +"'"+","+"'"+wins+"'"+","+"'"+losses+"'"+","+"'"+mapName+"')";
        Statement statement=connection.prepareStatement(sqlCom);
        statement.execute(sqlCom);
        connection.close();
    }

    //=========================creating player after map selection======================================================
    public static void createPlayerAfterMapSelection(Map map)
    {

        Player player=new Player(SignUpController.userName,SignUpController.Password,1,0,0,map);
        try {
            PlayerController.writePlayerInfoInDataBase(player);
        } catch (ClassNotFoundException e) {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Something went wrong during writePlayerInfoInDataBase");
            alert.setContentText("Something went wrong during writePlayerInfoInDataBase caused by Class not found exception!!");
            alert.show();

        } catch (SQLException e) {

            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Something went wrong during writePlayerInfoInDataBase");
            alert.setContentText("Something went wrong during writePlayerInfoInDataBase caused by sql exception!!");
            alert.show();
        }
    }

    //========================show player heroes base on player level===================================================
    public static ArrayList<ImageView>showPlayerHeroes(String userName) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/clashofclans", "erf", "4013613056");
        String sqlCom="select Level FROM players";
        Statement s=connection.prepareStatement(sqlCom);
        ResultSet rs=s.executeQuery(sqlCom);

        while(rs.next())
        {
            if (rs.getString("UserName").equals(userName))
            {
                playerLevel=rs.getInt("Level");
                break;
            }
        }
        connection.close();

        ArrayList<ImageView>heroes=new ArrayList<ImageView>();
        heroes.add(Jakiro.images.get(0));
        heroes.add(WereWolf.images.get(0));
        heroes.add(Juggernuat.images.get(0));
        heroes.add(Razor.images.get(0));

        return heroes;
    }
    //==========================find the person with the given username by reading from the database====================
    public static Player findByUsername(String username) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/clashofclans", "erf", "4013613056");
        String sqlCom="select * FROM players";
        Statement s=connection.prepareStatement(sqlCom);
        ResultSet rs=s.executeQuery(sqlCom);
        Player player = null;
        while(rs.next())
        {
            if (rs.getString("UserName").equals(username))
            {

                if (rs.getString("MapName").equals("Map1"))
                {
                    Map1 map=new Map1();
                     player=new Player(rs.getString("UserName"),rs.getString("Password"),rs.getInt("Level"),rs.getInt("Wins"), rs.getInt("Losses"),map);
                }
                if (rs.getString("MapName").equals("Map2"))
                {
                    Map2 map=new Map2();
                     player=new Player(rs.getString("UserName"),rs.getString("Password"),rs.getInt("Level"),rs.getInt("Wins"), rs.getInt("Losses"),map);
                }
                if (rs.getString("MapName").equals("Map3"))
                {
                    Map3 map=new Map3();
                     player=new Player(rs.getString("UserName"),rs.getString("Password"),rs.getInt("Level"),rs.getInt("Wins"), rs.getInt("Losses"),map);
                }
                if (rs.getString("MapName").equals("Map4"))
                {
                    Map4 map=new Map4();
                     player=new Player(rs.getString("UserName"),rs.getString("Password"),rs.getInt("Level"),rs.getInt("Wins"), rs.getInt("Losses"),map);
                }
            }
        }
    return player;
    }

    //========================give troops limit by only the image of map================================================

    public static int getMapTroopsLimit(ImageView mapImage)
    {
        ArrayList<Map>tempMapList = new ArrayList<Map>();
        Map1 map1=new Map1();
        Map2 map2=new Map2();
        Map3 map3=new Map3();
        Map4 map4=new Map4();
        tempMapList.add(map1);
        tempMapList.add(map2);
        tempMapList.add(map3);
        tempMapList.add(map4);

        int troopsLimit=0;
        for (Map map:tempMapList)
        {
            if (map.getMapImage().getImage().getUrl().equals(mapImage.getImage().getUrl()))
            {
                troopsLimit=map.getTroopsLimit();
                break;
            }
        }
        return troopsLimit;
    }

    //====================initializing all Players arrayList from data base=============================================

    public static void initializePlayers() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/clashofclans", "erf", "4013613056");
        String sqlCom="select * FROM players";
        Statement s=connection.prepareStatement(sqlCom);
        ResultSet rs=s.executeQuery(sqlCom);
        players.clear();
        while(rs.next())
        {
            if (rs.getString("MapName").equals("Map1"))
            {
                Map1 map=new Map1();
                Player player=new Player(rs.getString("UserName"),rs.getString("Password"),rs.getInt("Level"),rs.getInt("Wins"), rs.getInt("Losses"),map);
                players.add(player);
            }
            if (rs.getString("MapName").equals("Map2"))
            {
                Map2 map=new Map2();
                Player player=new Player(rs.getString("UserName"),rs.getString("Password"),rs.getInt("Level"),rs.getInt("Wins"), rs.getInt("Losses"),map);
                players.add(player);
            }
            if (rs.getString("MapName").equals("Map3"))
            {
                Map3 map=new Map3();
                Player player=new Player(rs.getString("UserName"),rs.getString("Password"),rs.getInt("Level"),rs.getInt("Wins"), rs.getInt("Losses"),map);
                players.add(player);
            }
            if (rs.getString("MapName").equals("Map4"))
            {
                Map4 map=new Map4();
                Player player=new Player(rs.getString("UserName"),rs.getString("Password"),rs.getInt("Level"),rs.getInt("Wins"), rs.getInt("Losses"),map);
                players.add(player);
            }
            players.removeIf(p -> p.getUserName().equals(SignUpController.userName));
        }
    }

    //==============================all maps read from database=========================================================

    public static void initializaAllMaps() throws ClassNotFoundException, SQLException, IOException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/clashofclans", "erf", "4013613056");
        String sqlCom="select * FROM players";
        Statement s=connection.prepareStatement(sqlCom);
        ResultSet rs=s.executeQuery(sqlCom);
        allMaps.clear();
        while(rs.next())
        {
            if (rs.getString("MapName").equals("Map1"))
            {

                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Map1.fxml"));
                Scene scene = new Scene(fxmlLoader.load(),1366 , 763);

            }
            if (rs.getString("MapName").equals("Map2"))
            {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Map2.fxml"));
                Scene scene = new Scene(fxmlLoader.load(),1366 , 763);
            }
            if (rs.getString("MapName").equals("Map3"))
            {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Map3.fxml"));
                Scene scene = new Scene(fxmlLoader.load(),1366 , 763);
            }
            if (rs.getString("MapName").equals("Map4"))
            {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Map4.fxml"));
                Scene scene = new Scene(fxmlLoader.load(),1366 , 763);
            }
        }
    }
    //===========================================================================
    public static void saveWinToDatabase(Player winner, Player loser) throws SQLException, ClassNotFoundException {

        winner.setPlayerWins(winner.getPlayerWins() + 1);
        loser.setPlayerLoses(loser.getPlayerLosses() + 1);

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/clashofclans", "erf", "4013613056");

        String sql = "UPDATE players SET Wins='" + winner.getPlayerWins() + "' WHERE UserName='" + winner.getUserName() + "';";
        Statement s = connection.prepareStatement(sql);
        s.execute(sql);

        String sql2 = "UPDATE players SET Losses='" + loser.getPlayerLosses() + "' WHERE UserName='" + loser.getPlayerLosses() + "';";
        Statement s2 = connection.prepareStatement(sql2);
        s2.execute(sql2);
        connection.close();

    }

    //============================================================



}




