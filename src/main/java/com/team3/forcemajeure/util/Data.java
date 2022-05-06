package com.team3.forcemajeure.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
    * Loop through text file names. For each name, use the fileResourceUtils
    * method to get text file content as string. Then, add text file content
    * to map as a value, where its key is the file name.
    * */
    public static void loadTextMap() {
        for (String name : TEXT_FILE_NAMES) {
            String fileName = "asciiArt/" + name;
            try {
                String data =
                        FileResourceUtils.getFileAsStringFromResourceStream(fileName);
                textMap.put(name, data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*
    * Use gson to convert json file to a hashmap
    * */
    public static void loadRoomMap() {
        Type type = new TypeToken<Map<String, Room>>() {}.getType();
        InputStream inputStream;
        Reader reader;

        if (FileResourceUtils.directoryExists("saved")) {
            String userDir = System.getProperty("user.dir") + File.separator;
            File rooms = new File(userDir + "saved/rooms.json");

            try {
                inputStream = new FileInputStream(rooms);
                reader = new InputStreamReader(new BufferedInputStream(inputStream));
                roomMap = gson.fromJson(reader, type);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                // get class loader
                ClassLoader classLoader = FileResourceUtils.getClassLoader();
                // get resource from relative classloader path
                inputStream = classLoader.getResourceAsStream("data/" + "rooms.json");
                // read stream
                reader = new InputStreamReader(Objects.requireNonNull(inputStream));
                // convert to map using gson
                roomMap = gson.fromJson(reader, type);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
//  Endings
    public static void loadEndingsMap() {
        Type type = new TypeToken<Map<String, Endings>>() {}.getType();

        try {
            // get class loader
            ClassLoader classLoader = Data.class.getClassLoader();
            // get resource from relative classloader path
            InputStream inputStream =
                    classLoader.getResourceAsStream("data/endings.json");
            // read stream
            Reader reader = new InputStreamReader(Objects.requireNonNull(inputStream));
            // convert to map using gson
            endingMap = gson.fromJson(reader, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadNPCMap() {
        Type type = new TypeToken<Map<String, NPC>>() {}.getType();
        InputStream inputStream;
        Reader reader;

        if (FileResourceUtils.directoryExists("saved")) {
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
                // get class loader
                ClassLoader classLoader = FileResourceUtils.getClassLoader();
                // get resource from relative classloader path
                inputStream = classLoader.getResourceAsStream("data/" + "npcs.json");
                // read stream
                reader = new InputStreamReader(Objects.requireNonNull(inputStream));
                // convert to map using gson
                setNpcMap(gson.fromJson(reader, type));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void loadInventory() {
        Type type = new TypeToken<Inventory>() {}.getType();
        InputStream inputStream;
        Reader reader;

        if (FileResourceUtils.directoryExists("saved")) {
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

    public static void saveGame() {
        System.out.println("Saving game...");
        if (!FileResourceUtils.directoryExists("saved")) {
            FileResourceUtils.createDirectory("saved");
        }
        saveRoomMap();
        saveNpcMap();
        saveInventory();
    }

    private static void saveRoomMap() {
        FileResourceUtils.convertMapToJsonAndSaveToDir(roomMap, "saved/rooms.json");
    }

    private static void saveNpcMap() {
        FileResourceUtils.convertMapToJsonAndSaveToDir(npcMap, "saved/npcs.json");
    }
    
    private static void saveInventory() {
        FileResourceUtils.convertInventoryToJsonAndSaveToDir(getInventory(), "saved/inventory.json");
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

    public static Inventory getInventory() {
        return inventory;
    }

    public static void setInventory(Inventory inventory) {
        Data.inventory = inventory;
    }
}