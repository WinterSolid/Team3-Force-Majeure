
/*
 * TextParser class deals with all the user text input in the game
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class TextParser {
//    INSTANCE VARIABLES
    static Scanner scanner = new Scanner(System.in);

    //    Main Menu text parser
    public static String textInputMainMenu(){
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
    public static String gameScannerInput() throws IOException {
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
    public static void gameScannerOutput(String gameInput, Player player, Map<String, Room> roomMap) {
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
            System.out.println("USE needs a method that will search inventory");

        }
//        used to look around and look through items / inventory
        else if(verb.contains("look")) {
            switch (noun) {
                case "inventory":
                    System.out.println(player.getInventory());
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
                    System.out.println("I dont know about this items");

            }
        }

        else{
//            If its not a correct answer just tell user
            System.out.println(gameInput + " not valid response if need help please say help");
        }
    }
}
