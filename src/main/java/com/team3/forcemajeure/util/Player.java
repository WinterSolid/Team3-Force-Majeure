package com.team3.forcemajeure.util;

public class Player {

//    Variables
    public  Room curRoom;
    public Inventory inventory;
    private String playerMap;

//    Constructor
    public Player() {
//        starting player off with map for right now
        inventory = Data.getInventory();
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
    public int rollDicePlayer(){
        int numberRolled;
        numberRolled = (int) (Math.random() * (7-1) + 1);
        return numberRolled;
    }
    public int rollDiceTotalPlayer(int count){
        int total = 0;
        for (int counter = 0; counter<count; counter++){
            int rolled = rollDicePlayer();
            System.out.println("You roll " + counter + " rolled a " + rolled + " total is " + total);
            total = total + rolled;
        }
        return total;
    }

    public Room getCurRoom() {
        return curRoom;
    }

    public Inventory getPlayerInventory() {
        return inventory;
    }

    public void setPlayerInventory(Inventory playerInventory) {
        this.inventory = playerInventory;
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
