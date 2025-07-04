package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

// This class loads properties from a given file path and returns them as a Properties object
public class propertyLoader {
    public static Properties loadProperties(String filePath) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file: " + filePath, e);
        }
        return properties;
    }
}