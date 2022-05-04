import java.io.*;
import java.net.URL;
import java.util.Objects;

public class FileResourceUtils {

    private FileResourceUtils() {}

    public static String getFileAsStringFromResourceStream(String fileName) {
        ClassLoader classLoader = Main.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        Reader reader = new InputStreamReader(Objects.requireNonNull(inputStream));

        try {
            char[] buffer = new char[4096];
            StringBuilder builder = new StringBuilder();
            int numChars;

            while ((numChars = reader.read(buffer)) >= 0) {
                builder.append(buffer, 0, numChars);
            }
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static InputStream getInputStreamFromResource(String fileName) {
        ClassLoader classLoader = Main.class.getClassLoader();

        return new BufferedInputStream(
                Objects.requireNonNull(classLoader.getResourceAsStream(fileName)));
    }

    public static boolean resourceExists(String fileName) {
        URL resource = getUrlFromResource(fileName);
        return resource != null;
    }

    public static URL getUrlFromResource(String fileName) {
        ClassLoader classLoader = Main.class.getClassLoader();
        return classLoader.getResource(fileName);
    }
}