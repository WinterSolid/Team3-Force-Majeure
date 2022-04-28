import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

class GameData {
    void loadGame() {
        Map<String, Room> roomMap = null;
        Map<String, NPC> npcMap = null;

        try {
            roomMap =
                    (Map<String, Room>) readJsonFileAndConvertToMap("data.json", "room");
            npcMap =
                    (Map<String, NPC>) readJsonFileAndConvertToMap("npcs.json", "npc");
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (roomMap == null || npcMap == null) {
            throw new RuntimeException("Unable to load game data");
        }

        for (var entry : roomMap.entrySet()) {
            System.out.println(entry.getValue().getNpc());
            if ("npc".equals(entry.getKey())) {
                System.out.println("got npc");
            }
        }
    }

    Map<String, ?> readJsonFileAndConvertToMap(String fileName, String objectType) {
        Type type = null;

        if ("room".equals(objectType)) {
            type = new TypeToken<Map<String, Room>>() {}.getType();
        } else if ("npc".equals(objectType)) {
            type = new TypeToken<Map<String, NPC>>() {}.getType();
        } else {
            throw new IllegalArgumentException("Object type does not exist");
        }

        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            Path path = Path.of(Objects.requireNonNull(
                    Main.class.getResource(String.format("./%s", fileName))).toURI());

            Reader reader = Files.newBufferedReader(path);

            // can't close reader here

            return gson.fromJson(reader, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}