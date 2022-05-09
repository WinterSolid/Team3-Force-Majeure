package com.team3.forcemajeure.util;
/*
 * com.team3.forcemajeure.util.TextParser class deals with all the user text input in the game
 */

import java.util.Map;
import java.util.Scanner;

public class TextParser {
//    INSTANCE VARIABLES
    private static final Scanner scanner = new Scanner(System.in);
    //    com.team3.forcemajeure.Main Menu text parser
    public static String textInputMainMenu() {
        String userInput = "  ";
        while (1 != userInput.split(" ").length) {
            System.out.print(">:");
            userInput = scanner.nextLine().toLowerCase().stripLeading().stripTrailing();
//          if user puts start in com.team3.forcemajeure.Main Menu start the game
            if (userInput.equals("start")) {
                userInput = "game";
            }
//            if user puts quit in com.team3.forcemajeure.Main Menu cleanly exit game
            else if (userInput.equals("quit")) {
                Data.saveGame();
                System.out.println("Exiting the game");
                System.exit(0);
                } else if ("mute".equals(userInput)) {
                    return "mute";
                }
//            User did not put in valid response
            else {
                System.out.println(userInput + " Not valid command please put start or quit");
            }
        }
        return userInput;
    }
//    com.team3.forcemajeure.Game text parser runs during game takes 1 word or 2 word commands and if 1 word command executes them
//    if 2 word command it will pass to gameScannerOutput()
    public static String gameScannerInput() {
        String helpBanner = Data.getTextMap().get("help");
        String userInput = "";
        while (2 != userInput.split(" ").length) {
            System.out.print(">:");
            userInput = scanner.nextLine().toLowerCase().strip();
            if (userInput.equals("help")) {
                System.out.println(helpBanner);
            }
            else if (userInput.equals("quit")) {
//                add exiting the game
                Data.saveGame();
                System.out.println("Exiting game");
                System.exit(0);
            } else if ("mute".equals(userInput)) {
                return "mute";
            }
//            user put in invalid 1 word command
            else if (1 == userInput.split(" ").length) {
                System.out.println(userInput + " not valid response if need help please say help");
            }
        }
        return userInput;
    }

//    Actions on 2 word commands must be used with gameScannerInput()
//    TODO combine with gameScannerInput()
    public static void gameScannerOutput(String gameInput, Player player, Map<String, Room> roomMap, Map<String, NPC> npcMap, Map<String, Endings> endingsMap) {
//      Verb being first word and Noun second word
        String verb = gameInput.split(" ")[0];
        String noun = gameInput.split(" ")[1];
        Map<String,Integer> directions =
                Map.of("north", 1, "south", 1, "east", 1, "west", 1);
//      Go deals with movement in game verb+noun
        if (verb.contains("go") && directions.containsKey(noun)) {
            String nextRoomName = player.getCurRoom().getRoomNameFromDirections(noun);
            Room nextRoom = roomMap.get(nextRoomName);

            if (nextRoom == null || "nothing".equals(nextRoomName)) {
                System.out.println("Can't go that way");
                return;
            }
            player.setCurRoom(nextRoom);
        }

//        TODO need method to look through user Inventory
//        TODO need method to look through user com.team3.forcemajeure.util.Inventory
        else if(verb.contains("use")){
            switch (noun){
                case "pepsimachine":
                    if (player.getCurRoom().getDescription().contains("pepsimachine") &&
                            player.getPlayerInventory().getInventory().contains("sanddollar")){
                        System.out.println("You got a pepsi");
                        player.getPlayerInventory().appendInventory("pepsi");
                        player.getPlayerInventory().removeItemInventory("sanddollar");
                        System.out.println("sanddollar removed");
                    }
                    else if(player.getCurRoom().getDescription().contains("pepsimachine")
                            && !player.getPlayerInventory().getInventory().contains("sanddollar")){
                        System.out.println("Vending Mactine takes sanddollars does not look like " +
                                "you have any in inventory");
                    }
                    else{
                        System.out.println("Vending Machine?");
                    }
                    break;
                case "pepsi":
                    if (player.getCurRoom().getName().equals("DoctorsOffice") &&
                            player.getPlayerInventory().getInventory().contains("pepsi")){
                        player.getPlayerInventory().appendInventory("karma");
                        player.getPlayerInventory().removeItemInventory("pepsi");
                        System.out.println("Larson starts coming to remember the glory, the Java conventions, the concepts. You don't know what’s going on with him, but you are glad you helped a brother out. But you’ve got to get to the bachelor party, no time to hear Larson stumble through his thoughts. He asks if he can join you at the SDE bachelor Party, but you know the beach might be good thing for him, he has to get his environment working again.\\n\"");
                        System.out.println("type 'use larson' to get Larson to join you or do nothing and leave him (choice)");
                    }
                    break;
                case "larson":
                    if (player.getCurRoom().getName().equals("DoctorsOffice") &&
                    player.getPlayerInventory().getInventory().contains("karma")){
//                        TODO add flag that will figure out if user has given Larson pepsi
                        player.getPlayerInventory().appendInventory("larson");
                        player.getPlayerInventory().removeItemInventory("karma");
                        System.out.println("Larsons joins you");
                    }
                    break;
//                    TODO need to add way to kick player back to main menu
//                com.team3.forcemajeure.Game endings
                case "boatkey":
                    if(player.getCurRoom().getDescription().contains("boat") &&
                            player.getPlayerInventory().getInventory().contains("boatkey") &&
                            !player.getPlayerInventory().getInventory().contains("karma") &&
                            !player.getPlayerInventory().getInventory().contains("larson")){
                        System.out.println(endingsMap.get("endings").okcase);
                        player.getPlayerInventory().appendInventory("endgame");
                    }
                    else if (player.getCurRoom().getDescription().contains("boat") &&
                            player.getPlayerInventory().getInventory().contains("boatkey") &&
                            player.getPlayerInventory().getInventory().contains("karma")){
                        System.out.println(endingsMap.get("endings").bestcase);
                        player.getPlayerInventory().appendInventory("endgame");
                    }
                    else if (player.getCurRoom().getDescription().contains("boat") &&
                            player.getPlayerInventory().getInventory().contains("boatkey") &&
                            player.getPlayerInventory().getInventory().contains("larson")){
                        System.out.println(endingsMap.get("endings").worstcase);
                        player.getPlayerInventory().appendInventory("loopgame");
                    }
                    else if (player.getCurRoom().getDescription().contains("boat") &&
                            !player.getPlayerInventory().getInventory().contains("boatkey"))
                        System.out.println("Need key for boat");
                    else{
                        System.out.println("What boat?");
                    }
                    break;
                default:
                    System.out.println(noun + "?");
            }
        }
//        used to look around and look through items / inventory
        else if (verb.contains("look")) {
            switch (noun) {
                case "inventory":
                    System.out.println(player.getPlayerInventory().getInventory());
                    break;
                case "map":
                    player.readMap();
                    break;
                case "north":
                case "east":
                case "south":
                case "west":
                    String lookingDirection = player.getCurRoom().getLookingDirection(noun);
                    System.out.println(lookingDirection);
                    break;
                default:
                    System.out.println("You cannot interact with that item.");
            }
        } else if ("talk".equals(verb)) {
            Room curRoom = player.getCurRoom();
            String titleCasedName = noun.substring(0, 1).toUpperCase() + noun.substring(1).toLowerCase();

            if (curRoom.getNpcs() == null || curRoom.getNpcs().size() == 0) {
                System.out.println("There is no one to talk to.");
            } else if (!curRoom.getNpcs().contains(titleCasedName)) {
                System.out.println("You cannot talk to that person.");
            } else {
                npcMap.get(titleCasedName).speak();
            }
        }

        else if (verb.contains("get")) {
//          Check if item in room
            if (noun.equals(player.getCurRoom().getItem())){
                System.out.println("You picked up " + noun);
//                add item to player inventory
                player.getPlayerInventory().appendInventory(noun);
//                remove item from room
                player.getCurRoom().removeItem(noun);

            }
            else {
                System.out.println("no item in room");
                System.out.println(player.getCurRoom().getItem());
            }
        }

        else {
//            If its not a correct answer just tell user
            System.out.println(gameInput + " is not valid command, enter help to see commands");
        }
    }
}
