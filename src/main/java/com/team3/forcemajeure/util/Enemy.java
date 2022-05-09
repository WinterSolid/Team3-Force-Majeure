package com.team3.forcemajeure.util;

/*
 * Enemy class
 */

public class Enemy {
    // INSTANCE VARIABLES
    private String name;
    int health;
    private Room location;
    boolean hasMet = true;
    private String dialogueNormal;
    private String dialoguEnemyVictory;
    private String dialoguEnemyDefeated;
    String item;
    Player player;




    // CONSTRUCTOR
    public Enemy(String name, int health, Room location,
                 String dialogueNormal, String dialoguEnemyVictory,
                 String dialoguEnemyDefeated, String item, Player player){
        this.name = name;
        this.health = health;
        this.location = location;
        this.dialogueNormal = dialogueNormal;
        this.dialoguEnemyVictory = dialoguEnemyVictory;
        this.dialoguEnemyDefeated = dialoguEnemyDefeated;
        this.item = item;
        this.player = player;
    }

    // BUSINESS METHODS
    public int rollDiceEnemy(){
        int numberRolled;
        numberRolled = (int) (Math.random() * (7-1) + 1);
        return numberRolled;
    }
    public int rollDiceTotalEnemy(int count){
        int total = 0;
        for (int counter = 0; counter<count; counter++){
            int rolled = rollDiceEnemy();
            System.out.println(name + " on " + counter + " rolled a " + rolled + " his total is " + total);
            total = total + rolled;
        }
        return total;
    }

    public void talk(){
        if (hasMet == true){
            System.out.println("Enemy Name: " + name + ": says " + dialogueNormal);
            hasMet = false;
        }
        else if (getHealth() > 0){
            System.out.println("Enemy Name: " + name + ": says " + dialoguEnemyVictory);
        }
        else if (!player.getPlayerInventory().inventory.contains(item)){
            System.out.println("Enemy Name " + name + ": says " + dialoguEnemyDefeated);
            if (!player.getPlayerInventory().inventory.contains(item)){
                giveLoot();
            }
        }
    }

    public void giveLoot(){
        System.out.println("For defeating me you get item: " + item);
        player.getPlayerInventory().appendInventory(item);

    }

    // ACCESSOR METHODS
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public Room getLocation() {
        return location;
    }
}
