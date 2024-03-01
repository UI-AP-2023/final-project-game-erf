package com.example.Model;

import com.example.Model.Maps.Map;

public class Player {

    private String userName;
    private String password;
    private int level;
    private int playerWins=0;
    private int playerLosses=0;
    private Map map;

    public Player(String userName, String password, int level, int playerWins, int playerLoses, Map map) {
        this.userName = userName;
        this.password = password;
        this.level = level;
        this.playerWins = playerWins;
        this.playerLosses = playerLoses;
        this.map = map;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPlayerWins() {
        return playerWins;
    }

    public void setPlayerWins(int playerWins) {
        this.playerWins = playerWins;
    }

    public int getPlayerLosses() {
        return playerLosses;
    }

    public void setPlayerLoses(int playerLoses) {
        this.playerLosses = playerLoses;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
