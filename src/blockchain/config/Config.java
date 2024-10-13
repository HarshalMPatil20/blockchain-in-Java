package blockchain.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Properties properties = new Properties();

    static {
        try {
            FileInputStream input = new FileInputStream("config/config.properties");
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get a value from the config file
    public static String get(String key) {
        return properties.getProperty(key);
    }

    // Get a value with a default
    public static String get(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
