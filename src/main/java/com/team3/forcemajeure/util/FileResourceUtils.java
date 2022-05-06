package com.team3.forcemajeure.util;

import com.google.gson.Gson;

import com.team3.forcemajeure.Main;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class FileResourceUtils {
    private FileResourceUtils() {
    }

    public static String getFileAsStringFromResourceStream(String fileName) {
        InputStream inputStream = getClassLoader().getResourceAsStream(fileName);
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
        return new BufferedInputStream(
                Objects.requireNonNull(getClassLoader().getResourceAsStream(fileName)));
    }

    public static boolean resourceExists(String fileName) {
        URL resource = getClassLoader().getResource(fileName);
        return resource != null;
    }

    public static URL getResourcePath(String fileName) {
        System.out.println(getClassLoader().getResource("saved"));
        return Objects.requireNonNull(getClassLoader().getResource(fileName));
    }

    public static ClassLoader getClassLoader() {
        return Main.class.getClassLoader();
    }

    public static String buildResourcePath(String dirName, String fileName) {
        System.out.println(getClassLoader());
        return getClassLoader().getResource(dirName) + fileName;
    }

    public static boolean createDirectory(String dirName) {
        File dir = new File(dirName);
        return dir.mkdir();
    }

    public static void convertMapToJsonAndSaveToDir(Map<String, ?> map, String dirName, String fileName) {
        String path = dirName + fileName;
        try {
            Writer writer = new FileWriter(path);
            new Gson().toJson(map, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void getJarDirectoryPath() {
//        String path = null;
//        try {
//            path = new File(Main.class.getProtectionDomain().getCodeSource().getLocation()
//                    .toURI()).getPath();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(path);
//    }

//    public static Path getFilePath(String filePath) {
//        Path path = Paths.get("saved");
//        return path.toAbsolutePath();
//    }


//    public static void getJarDirectory() {
//        String path = "saved";
//        File jarFile = null;
//        try {
//            jarFile = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        if (jarFile.isFile()) {
//            System.out.println("here");
//            try {
//                JarFile jar = new JarFile(jarFile);
//                Enumeration<JarEntry> entries = jar.entries();
//                while (entries.hasMoreElements()) {
//                    String name = entries.nextElement().getName();
//                    System.out.println(name);
//                    if (name.startsWith(path)) {
//                        System.out.println(name);
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public static boolean directoryExists(String dirName) {
        String userDir = System.getProperty("user.dir") + File.separator;
        File rooms = new File(userDir + "saved");
        return rooms.exists();
    }
}

