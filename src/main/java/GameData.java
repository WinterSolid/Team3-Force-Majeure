import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GameData {
    void loadGame() {
        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            Path path = Path.of(Objects.requireNonNull(Main.class.getResource("/data.json")).toURI());
            Reader reader = Files.newBufferedReader(path);

            // convert JSON file to map
            Type type = new TypeToken<Map<String, Room>>() {}.getType();
            Map<String, Room> map = gson.fromJson(reader, type);
            Room room = map.get("Beach");
            System.out.println(room.name);
            // loop thru map entries
//            for (var entry : map.entrySet()) {
//                System.out.println(entry.getKey());

//                Map<?, ?> nestedMap = (Map<?, ?>) entry.getValue();
                // loop thru nested map entries
//                for (var nested : nestedMap.entrySet()) {
//                    System.out.println(nested);
//                    System.out.println();
//                }
//                System.out.println();
//            }

            // close reader
            reader.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}