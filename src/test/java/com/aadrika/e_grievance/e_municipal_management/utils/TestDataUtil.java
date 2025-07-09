package com.aadrika.e_grievance.e_municipal_management.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class for managing test data from properties file
 * Provides centralized access to test configuration and data
 */
public class TestDataUtil {
    // Properties object to store configuration data
    private static Properties properties;
    
    // Static block to load properties on class initialization
    static {
        loadProperties();
    }
    
    /**
     * Loads properties from testdata.properties file
     * Throws RuntimeException if properties file cannot be loaded
     */
    private static void loadProperties() {
        properties = new Properties();
        try (InputStream input = TestDataUtil.class.getClassLoader()
                .getResourceAsStream("testdata.properties")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load test data properties", e);
        }
    }
    
    /**
     * Gets property value by key
     * @param key Property key to retrieve
     * @return Property value as String
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    /**
     * Gets base URL for the application
     * @return Base URL from properties
     */
    public static String getBaseUrl() {
        return getProperty("base.url");
    }
    
    /**
     * Gets test user name
     * @return User name from properties
     */
    public static String getUserName() {
        return getProperty("user.name");
    }
    
    /**
     * Gets test user phone number
     * @return Phone number from properties
     */
    public static String getUserPhone() {
        return getProperty("user.phone");
    }
    
    /**
     * Gets test user email
     * @return Email from properties
     */
    public static String getUserEmail() {
        return getProperty("user.email");
    }
    
    /**
     * Gets grievance description for testing
     * @return Grievance description from properties
     */
    public static String getGrievanceDescription() {
        return getProperty("grievance.description");
    }
    
    /**
     * Gets grievance location for testing
     * @return Grievance location from properties
     */
    public static String getGrievanceLocation() {
        return getProperty("grievance.location");
    }
}