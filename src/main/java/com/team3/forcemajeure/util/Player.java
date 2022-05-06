package com.team3.forcemajeure.util;

public class Player {
//    TODO prob should not hardcode this
//    Setting Beach since that is our starting location
//    Variables
   public  Room curRoom;
//    ArrayList<String> inventory = new ArrayList<>();
public Inventory inventory = new Inventory("map");
    String playerMap;

//    Constructor
    public Player() {
//        starting player off with map for right now
        this.playerMap = Data.getTextMap().get("playerMap");
    }

//    Business Methods
//    readMap prints out map for user and replaces location on map with currentRoom he is in with [X]
    public void readMap() {
        String playerLocation = getCurRoom().getName();
        if (inventory.getInventory().contains("map")){
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

    public void setCurRoom(Room room) {
        this.curRoom = room;
    }
}
