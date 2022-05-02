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
    public static FileResourceUtils fileResourceUtils;
    public static Map<String, String> textMap;
    public static final String[] TEXT_FILE_NAMES =
            new String[] {"help", "intro", "mainMenu", "playerMap"};
    public static Map<String, Room> roomMap;

    private Data() {}

    static {
        fileResourceUtils = FileResourceUtils.getInstance();
        textMap = new HashMap<>();
        roomMap = new HashMap<>();
        loadTextMap();
        loadRoomMap();
    }

    public static void loadTextMap() {
        for (String name : TEXT_FILE_NAMES) {
            String fileName = "asciiArt/" + name;
            try {
                String data =
                        fileResourceUtils.getFileAsStringFromResourceStream(fileName);
                textMap.put(name, data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void loadRoomMap() {
        Type type = new TypeToken<Map<String, Room>>() {}.getType();

        try {
            // create Gson instance
            Gson gson = new Gson();
            // get class loader
            ClassLoader classLoader = Data.class.getClassLoader();
            // get resource from classloader path
            InputStream inputStream = classLoader.getResourceAsStream("rooms.json");
            // read stream
            Reader reader = new InputStreamReader(Objects.requireNonNull(inputStream));
            // convert to map using gson
            roomMap = gson.fromJson(reader, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}