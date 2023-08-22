package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);
    private static final String BASE_PATH = "src/test/resources/";
    private static final ConfigReader INSTANCE = new ConfigReader();
    private Properties properties;
    private ConfigReader() {
        properties = new Properties();
    }
    public static ConfigReader getInstance() {
        return INSTANCE;
    }
    private void loadProperties(String fileName) {
        try (FileInputStream stream = new FileInputStream(BASE_PATH + fileName)) {
            properties.load(stream);
        } catch (IOException e) {
            logger.error("Error reading property file: {}. Error {}", fileName, e.getMessage());
        }
    }
    /**
     * Retrieves a property value based on the file name and key provided.
     *
     * @param fileName The properties file name.
     * @param key The property key.
     * @return The property value.
     */
    public String getProperty(String fileName, String key) {
        if (properties.isEmpty()) {
            loadProperties(fileName);
        }
        return properties.getProperty(key);
    }


}
