import com.google.gson.Gson;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;

public class GameData {
    void loadGame() {
        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            Path path = Path.of(Objects.requireNonNull(GameData.class.getResource("/locations.json")).toURI());
            Reader reader = Files.newBufferedReader(path);

            // convert JSON file to map
            Map<?, ?> map = gson.fromJson(reader, Map.class);

            // loop thru map entries
            for (var entry : map.entrySet()) {
                System.out.println(entry.getKey());

                Map<?, ?> nestedMap = (Map<?, ?>) entry.getValue();

                // loop thru nested map entries
                for (var nested : nestedMap.entrySet()) {
                    System.out.println(nested);
                }
                System.out.println();
            }

            // close reader
            reader.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}