package com.team3.forcemajeure.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Room {
    private String name;
    private String description;
    private String item;
    private String story;
//    public HashMap<String, String> item;
    private HashMap<String, String> directions;
    private HashMap<String, String> looking;
    private List<String> npcs;

// Getter and setter
    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public String getStory() {
        return this.story;
    }
    public String getItem() {
        return this.item;
    }
    public void removeItem(String item){
        this.item = "none";
    }
    public String getRoomNameFromDirections(String direction) {
        return directions.get(direction);
    }
    public String getLookingDirection(String direction) {
        return looking.get(direction);
    }
    public List<String> getNpcs() {
        return this.npcs;
    }
    public void displayRoomInfo() {
        String npcStr = "";
        if (getNpcs() != null) {
            npcStr = List.of(getNpcs()) +
                    " is in the area" +
                    "\n";
        }
        String str = "You are at the " +
                getName() +
                "\n" +
                getDescription() +
                "\n" +
                npcStr +
                "Items seen: " +
                getItem();
        System.out.println(str);
    }
}
