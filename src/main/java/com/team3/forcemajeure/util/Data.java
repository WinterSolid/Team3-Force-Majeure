package com.team3.forcemajeure.util;

import static com.team3.forcemajeure.util.FileResourceUtils.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/*
* This is a Singleton-like class that is used to statically load
* all game data. It can be referenced globally. All of the map loading methods
* are the same, I couldn't figure out how to make one reusable method.
* This class uses many helper methods from FileResourceUtils.
* */
public class Data {
    private static final Gson gson = new Gson();
    private static Map<String, String> textMap;
    private static Map<String, Room> roomMap;
    public static Map<String, Endings> endingMap;
    public static Map<String, NPC> npcMap;
    public static Inventory inventory;
    public static final String[] TEXT_FILE_NAMES =
            new String[] {"help", "intro", "mainMenu", "playerMap"};

    private Data() {}

    static {
        textMap = new HashMap<>();
        roomMap = new HashMap<>();
        npcMap = new HashMap<>();
        endingMap = new HashMap<>();
        inventory = new Inventory("map");

        loadRoomMap();
        loadNPCMap();
        loadTextMap();
        loadEndingsMap();
        loadInventory();
    }

    /*
    * Loop through text file names, and for each name, use the fileResourceUtils
    * method to get the text file content as a string. Then, add text file content
    * to map as a value, where its key is the file name.
    * */
    public static void loadTextMap() {
        for (String name : TEXT_FILE_NAMES) {
            String fileName = "asciiArt/" + name;
            try {
                String data =
                        getFileAsStringFromResourceStream(fileName);
                textMap.put(name, data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*
    * Uses GSON to convert JSON file to a HashMap.
    * All of the other map loading methods below are the same.
    * */
    public static void loadRoomMap() {
        // You must specify the type of the output HashMap
        Type type = new TypeToken<Map<String, Room>>() {}.getType();
        InputStream inputStream;
        Reader reader;

        // If saved directory exists, load the saved game data from there.
        if (directoryExists("saved")) {
            // Gets folder the JAR sits in
            String userDir = System.getProperty("user.dir") + File.separator;
            // Make a file from the specified path
            File rooms = new File(userDir + "saved/rooms.json");

            try {
                // Get resource methods don't work outside the JAR, use FileInputStream
                inputStream = new FileInputStream(rooms);
                reader = new InputStreamReader(new BufferedInputStream(inputStream));
                roomMap = gson.fromJson(reader, type);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            // If there is no saved directory, load starting game data
            try {
                inputStream = getClassLoader().getResourceAsStream("data/rooms.json");
                reader = new InputStreamReader(Objects.requireNonNull(inputStream));
                setRoomMap(gson.fromJson(reader, type));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void loadNPCMap() {
        Type type = new TypeToken<Map<String, NPC>>() {}.getType();
        InputStream inputStream;
        Reader reader;

        if (directoryExists("saved")) {
            String userDir = System.getProperty("user.dir") + File.separator;
            File rooms = new File(userDir + "saved/npcs.json");

            try {
                inputStream = new FileInputStream(rooms);
                reader = new InputStreamReader(new BufferedInputStream(inputStream));
                setNpcMap(gson.fromJson(reader, type));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                inputStream = getClassLoader().getResourceAsStream("data/npcs.json");
                reader = new InputStreamReader(Objects.requireNonNull(inputStream));
                setNpcMap(gson.fromJson(reader, type));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //  Endings map never changes, so load from resources
    public static void loadEndingsMap() {
        Type type = new TypeToken<Map<String, Endings>>() {}.getType();

        try {
            InputStream inputStream =
                    getClassLoader().getResourceAsStream("data/endings.json");
            Reader reader = new InputStreamReader(Objects.requireNonNull(inputStream));
            setEndingMap(gson.fromJson(reader, type));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadInventory() {
        Type type = new TypeToken<Inventory>() {}.getType();
        InputStream inputStream;
        Reader reader;

        if (directoryExists("saved")) {
            String userDir = System.getProperty("user.dir") + File.separator;
            File inventory = new File(userDir + "saved/inventory.json");

            try {
                inputStream = new FileInputStream(inventory);
                reader = new InputStreamReader(new BufferedInputStream(inputStream));
                setInventory(gson.fromJson(reader, type));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void clearInventory() {
        setInventory(new Inventory("map"));
    }

    /*
    * For saving the game data, the contained methods save room, npc, and inventory data.
    * Runs after the user quits the game. Creates saved directory if not already there.
    * */
    public static void saveGame() {
        System.out.println("Saving game...");
        if (!directoryExists("saved")) {
            createDirectory("saved");
        }
        saveRoomMap();
        saveNpcMap();
        saveInventory();
    }

    private static void saveRoomMap() {
        convertMapToJsonAndSaveToDir(roomMap, "saved/rooms.json");
    }

    private static void saveNpcMap() {
        convertMapToJsonAndSaveToDir(npcMap, "saved/npcs.json");
    }
    
    private static void saveInventory() {
        convertInventoryToJsonAndSaveToDir(getInventory(), "saved/inventory.json");
    }

    public static Map<String, String> getTextMap() {
        return textMap;
    }

    public static void setTextMap(Map<String, String> textMap) {
        Data.textMap = textMap;
    }

    public static Map<String, Room> getRoomMap() {
        return roomMap;
    }

    public static void setRoomMap(Map<String, Room> roomMap) {
        Data.roomMap = roomMap;
    }

    public static Map<String, NPC> getNpcMap() {
        return npcMap;
    }

    public static void setNpcMap(Map<String, NPC> npcMap) {
        Data.npcMap = npcMap;
    }

    public static Map<String, Endings> getEndingMap() {
        return endingMap;
    }

    public static void setEndingMap(Map<String,Endings> e) {
        endingMap = e;
    }

    public static Inventory getInventory() {
        return inventory;
    }

    public static void setInventory(Inventory inventory) {
        Data.inventory = inventory;
    }
}