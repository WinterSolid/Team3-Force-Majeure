import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Data {
    public static Map<String, String> textMap;
    public static Map<String, Room> roomMap;
    public static Map<String, NPC> npcMap;
    public static final String[] TEXT_FILE_NAMES =
            new String[] {"help", "intro", "mainMenu", "playerMap"};

    private Data() {}

    static {
        textMap = new HashMap<>();
        roomMap = new HashMap<>();
        npcMap = new HashMap<>();
        loadTextMap();
        loadRoomMap();
        loadNPCMap();
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

        try {
            // create Gson instance
            Gson gson = new Gson();
            // get class loader
            ClassLoader classLoader = Data.class.getClassLoader();
            // get resource from relative classloader path
            InputStream inputStream = classLoader.getResourceAsStream("rooms.json");
            // read stream
            Reader reader = new InputStreamReader(Objects.requireNonNull(inputStream));
            // convert to map using gson
            roomMap = gson.fromJson(reader, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadNPCMap() {
        Type type = new TypeToken<Map<String, NPC>>() {}.getType();

        try {
            // create Gson instance
            Gson gson = new Gson();
            // get class loader
            ClassLoader classLoader = Data.class.getClassLoader();
            // get resource from relative classloader path
            InputStream inputStream = classLoader.getResourceAsStream("npcs.json");
            // read stream
            Reader reader = new InputStreamReader(Objects.requireNonNull(inputStream));
            // convert to map using gson
            npcMap = gson.fromJson(reader, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}