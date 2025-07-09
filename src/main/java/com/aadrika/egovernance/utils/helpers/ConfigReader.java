package com.aadrika.egovernance.utils.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import com.aadrika.egovernance.enums.Environment;
import com.aadrika.egovernance.utils.constants.FrameworkConstants;

/**
 * Configuration reader for environment-specific properties
 */
public final class ConfigReader {
    
    private static Properties properties;
    private static String currentEnvironment;
    
    static {
        loadProperties();
    }
    
    private ConfigReader() {}
    
    /**
     * Loads properties based on environment
     */
    private static void loadProperties() {
        currentEnvironment = System.getProperty("env", Environment.DEV.getEnvironmentName());
        String configFile = currentEnvironment + FrameworkConstants.PROPERTIES_EXTENSION;
        
        properties = new Properties();
        try (InputStream input = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config/" + configFile)) {
            if (input != null) {
                properties.load(input);
            } else {
                throw new RuntimeException("Config file not found: " + configFile);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config properties", e);
        }
    }
    
    /**
     * Gets property value by key
     * @param key Property key
     * @return Property value
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    /**
     * Gets base URL for current environment
     * @return Base URL
     */
    public static String getBaseUrl() {
        return getProperty("base.url");
    }
    
    /**
     * Gets browser type from configuration
     * @return Browser type
     */
    public static String getBrowserType() {
        return getProperty("browser.type");
    }
    
    /**
     * Gets current environment name
     * @return Environment name
     */
    public static String getCurrentEnvironment() {
        return currentEnvironment;
    }
}