import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
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

//    public void getFileFromResourceAndPrint(String fileName)
//            throws URISyntaxException {
//        File file = getFileFromResource(fileName);
//        printFile(file);
//    }

//    public String getFileFromResourceAndReturnAsString(String fileName)
//            throws URISyntaxException, IOException {
//        File file = getFileFromResource(fileName);
//        return getFileAsString(file);
//    }

//    private File getFileFromResource(String fileName)
//            throws URISyntaxException {
//        ClassLoader classLoader = getClass().getClassLoader();
//        URL resource = classLoader.getResource(fileName);
//        if (resource == null) {
//            throw new IllegalArgumentException("file not found! " + fileName);
//        }
//        return new File(resource.toURI());
//    }

//    private void printFile(File file) {
//
//        List<String> lines;
//        try {
//            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
//            lines.forEach(System.out::println);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    private String getFileAsString(File file) throws IOException {
//        return Files.readString(file.toPath());
//    }
}