package com.team3.forcemajeure;

import com.team3.forcemajeure.util.*;

import java.util.Map;

public class Game {
//     Variables
    Audio audio = Audio.getInstance();
    boolean gameRunning = true;

//    Business methods
//    primarly runs the game
    void runGame() {
//        Sets up all objects for the game
        Map<String, Room> roomMap = Data.roomMap;
        Map<String, NPC> npcMap = Data.npcMap;
        Map<String, Endings> endingsMap = Data.endingMap;
        // get starting room ("Water")
        Room startRoom = roomMap.get("WaterWreckage");
        // init com.team3.forcemajeure.util.Player
        Player player = new Player();
        // set player's current room to start room
        player.setCurRoom(startRoom);
//        Starts com.team3.forcemajeure.Game
        while (gameRunning) {
//            check if ending game condition have been met
            if (player.getPlayerInventory().getInventory().contains("endgame")){
                break;
            }
            if (player.getPlayerInventory().getInventory().contains("loopgame")){
                runGame();
            }

            // get current room
            Room curRoom = player.getCurRoom();
            // play room audio
            audio.play(curRoom.getName().toLowerCase());
            // display room info
            curRoom.displayRoomInfo();
//            prints story element for doctors office
            if ((!player.getPlayerInventory().getInventory().contains("larson") &&
                    !player.getPlayerInventory().getInventory().contains("karma"))){
                System.out.println(player.getCurRoom().getStory());
            }

//            prompt User
            String response = TextParser.gameScannerInput();
          
            if ("mute".equals(response)) {
                audio.toggleMute();
            } else {
                TextParser.gameScannerOutput(response, player, roomMap, npcMap, endingsMap);
            }
        }
    }

    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }
}
