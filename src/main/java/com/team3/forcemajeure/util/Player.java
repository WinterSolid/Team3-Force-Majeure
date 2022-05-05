package com.team3.forcemajeure.util;

public class Player {

//    Variables
   private   Room curRoom;



    private Inventory playerInventory = new Inventory("map");
    private String playerMap;
    //    ArrayList<String> inventory = new ArrayList<>();

//    Constructor
    public Player() {
//        starting player off with map for right now
        this.playerMap = Data.textMap.get("playerMap");
    }

//    Business Methods
//    readMap prints out map for user and replaces location on map with currentRoom he is in with [X]
    public void readMap() {
        String playerLocation = getCurRoom().getName();
        if (playerInventory.getInventory().contains("map")){
            System.out.println(playerMap.replace(playerLocation+"[ ]",
                    playerLocation+"[X]"));
        }
        else{
            System.out.println("I dont see map in your inventory");
        }
    }

    public Room getCurRoom() {
        return curRoom;
    }

    public Inventory getPlayerInventory() {
        return playerInventory;
    }

    public void setPlayerInventory(Inventory playerInventory) {
        this.playerInventory = playerInventory;
    }

    public String getPlayerMap() {
        return playerMap;
    }

    public void setPlayerMap(String playerMap) {
        this.playerMap = playerMap;
    }

    public void setCurRoom(Room room) {
        this.curRoom = room;
    }
}
