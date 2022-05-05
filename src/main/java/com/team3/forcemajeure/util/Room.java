package com.team3.forcemajeure.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Room {
    public String name;
    public String description;
    public String item;
    public String story;
//    public HashMap<String, String> item;
    public HashMap<String, String> directions;
    public HashMap<String, String> looking;
    public List<String> npcs;


    public String getName() {
        return this.name;
    }

    String getDescription() {
        return this.description;
    }
    public String getStory() {
        return this.story;
    }
    String getItem() {
        return this.item;
    }
    void removeItem(String item){
        this.item = "none";
    }

    String getRoomNameFromDirections(String direction) {
        return directions.get(direction);
    }

    String getLookingDirection(String direction) {
        return looking.get(direction);
    }

    List<String> getNpcs() {
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
                "You see: " +
                getItem();
        System.out.println(str);
    }
}
