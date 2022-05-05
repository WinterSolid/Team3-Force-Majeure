package com.team3.forcemajeure.util;

import com.google.gson.Gson;

import com.team3.forcemajeure.Main;

import java.io.*;
import java.net.URL;
import java.util.Map;
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
        URL resource = getClassLoader().getResource(fileName);
        return resource != null;
    }

    public static URL getResourcePath(String fileName) {
//        System.out.println(getClassLoader().getResource("saved-data"));
        return Objects.requireNonNull(getClassLoader().getResource(fileName));
    }

    public static ClassLoader getClassLoader() {
        return Main.class.getClassLoader();
    }

    public static String buildResourcePath(String dirName, String fileName) {
        return getClassLoader().getResource(dirName) + fileName;
    }

    public static void writeMapToJsonAndSave(Map<String,?> map, String dirName, String fileName) {
//        try (PrintWriter out = new PrintWriter(new FileWriter(buildResourcePath(dirName, fileName)))) {
        try {
            Gson gson = new Gson();
//            System.out.println(buildResourcePath(dirName, fileName));
            System.out.println(getResourcePath("saved-data/placeholder"));
            gson.toJson(map, new FileWriter(buildResourcePath(dirName, fileName)));
        } catch (Exception e) {
            e.printStackTrace();
        }
//            String jsonString = gson.toJson(map);
//            out.write(jsonString);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}