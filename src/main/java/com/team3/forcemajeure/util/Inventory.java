package com.team3.forcemajeure.util;

import java.util.ArrayList;

public class Inventory {

//    Variables
    ArrayList<String> inventory;

//    Constructor
    public Inventory(String item){
        inventory = new ArrayList<>();
        inventory.add(item);
    }

//     Getter and Settter
    public ArrayList<String> getInventory() {
        return inventory;
    }
    public void setInventory(ArrayList<String> inventory) {
        this.inventory = inventory;
    }
    public void appendInventory(String item){
        this.inventory.add(item);
    }
    public void removeItemInventory(String item){
        this.inventory.remove(item);
    }
}
