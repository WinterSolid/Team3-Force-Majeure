
/*
 * TextParser class deals with all the user text input in the game
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TextParser {
//    INSTANCE VARIABLES
    private static final Scanner scanner = new Scanner(System.in);
    private final Audio audio = Audio.getInstance();

    //    Main Menu text parser
    public static String textInputMainMenu() {
        String userInput = "  ";
        while (1 != userInput.split(" ").length){
            System.out.print(">:");
            userInput = scanner.nextLine().toLowerCase().stripLeading().stripTrailing();
//            if user puts start in Main Menu start the game
            if (userInput.equals("start")){
                userInput = "game";
            }
//            if user puts quit in Main Menu cleanly exit game
            else if (userInput.equals("quit")){
                System.out.println("Exiting the game");
                System.exit(0);
            } else if ("mute".equals(userInput)) {
                return "mute";
            }
//            User did not put in valid response
            else{
                System.out.println(userInput + " Not valid command please put start or quit");
            }
        }
        return userInput;
    }

//    Game text parser runs during game takes 1 word or 2 word commands and if 1 word command executes them
//    if 2 word command it will pass to gameScannerOutput()
    public static String gameScannerInput() {
        String helpBanner = Data.textMap.get("help");
        String userInput = "";
        while (2 != userInput.split(" ").length){
            System.out.print(">:");
            userInput = scanner.nextLine().toLowerCase().strip();
            if (userInput.equals("help")){
                System.out.println(helpBanner);
            }
            else if (userInput.equals("quit")){
//                add exiting the game
                System.out.println("Exiting the game");
                System.exit(0);
            } else if ("mute".equals(userInput)) {
                return "mute";
            }
//            user put in invalid 1 word command
            else if (1 == userInput.split(" ").length){
                System.out.println(userInput + " not valid response if need help please say help");
            }
        }
        return userInput;
    }

//    Actions on 2 word commands must be used with gameScannerInput()
//    TODO combine with gameScannerInput()
    public static void gameScannerOutput(String gameInput, Player player, Map<String, Room> roomMap, Map<String, NPC> npcMap) {
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
        else if(verb.contains("use")){
            switch (noun){
                case "pepsimachine":
                    if (player.getCurRoom().description.contains("pepsimachine") &&
                            player.inventory.getInventory().contains("sanddollar")){
                        System.out.println("You got a pepsi");
                        player.inventory.appendInventory("pepsi");
                        player.inventory.removeItemInventory("sanddollar");
                        System.out.println("sanddollar removed");

                    }
                    else if(player.getCurRoom().description.contains("pepsimachine")
                            && !player.inventory.getInventory().contains("sanddollar")){
                        System.out.println("Vending Mactine takes sanddollars does not look like " +
                                "you have any in inventory");
                    }
                    else{
                        System.out.println("Vending Machine?");
                    }
                    break;
                case "larson":
                    if (player.getCurRoom().getItem().contains("larson")){
//                        TODO add flag that will figure out if user has given Larson pepsi
                        System.out.println("Larsons joins you");
                    }
                    else{
                        System.out.println("Larson?");
                    }
                    break;
//                    TODO need to add way to kick player back to main menu
//                Game endings
                case "boat":
                    if(player.getCurRoom().description.contains("boat") &&
                            player.inventory.getInventory().contains("boatkey")){
                        System.out.println("ending1");
                        System.exit(0);
                    }
                    else if (player.getCurRoom().description.contains("boat") &&
                            player.inventory.getInventory().contains("boatkey") &&
                            player.inventory.getInventory().contains("karma")){
                        System.out.println("ending2");
                        System.exit(0);
                    }
                    else if (player.getCurRoom().description.contains("boat") &&
                            player.inventory.getInventory().contains("boatkey") &&
                            player.inventory.getInventory().contains("lason")){
                        System.out.println("ending3");
                        System.exit(0);
                    }
                    else if (player.getCurRoom().description.contains("boat") &&
                            !player.inventory.getInventory().contains("boatkey"))
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
                    System.out.println(player.inventory.getInventory());
                    break;
                case "map":
                    player.readMap();
                    System.out.println("here");
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
            Room curRoom = player.curRoom;
            String titleCasedName = noun.substring(0, 1).toUpperCase() + noun.substring(1).toLowerCase();

            if (curRoom.getNpcs() == null || curRoom.getNpcs().size() == 0) {
                System.out.println("There is no one to talk to.");
            } else if (!curRoom.getNpcs().contains(titleCasedName)) {
                System.out.println("You cannot talk to that person.");
            } else {
                npcMap.get(titleCasedName).speak();
            }
        }
        else if(verb.contains("get")) {
//          Check if item in room
            if (noun.equals(player.getCurRoom().getItem())){
                System.out.println("You picked up " + noun);
//                add item to player inventory
                player.inventory.appendInventory(noun);
//                remove item from room
                player.curRoom.removeItem(noun);

            }
            else{
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
