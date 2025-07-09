package com.aadrika.egovernance.utils.helpers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * JSON data reader for test data management
 */
public final class JsonDataReader {
    
    private static JsonNode testData;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    static {
        loadTestData();
    }
    
    private JsonDataReader() {}
    
    /**
     * Loads test data from JSON file
     */
    private static void loadTestData() {
        try (InputStream input = JsonDataReader.class.getClassLoader()
                .getResourceAsStream("testdata/grievance_data.json")) {
            testData = objectMapper.readTree(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load test data", e);
        }
    }
    
    /**
     * Gets user data by key
     * @param userKey User data key
     * @return Map of user data
     */
    public static Map<String, String> getUserData(String userKey) {
        JsonNode userNode = testData.get("users").get(userKey);
        Map<String, String> userData = new HashMap<>();
        
        userNode.fields().forEachRemaining(entry -> 
            userData.put(entry.getKey(), entry.getValue().asText()));
        
        return userData;
    }
    
    /**
     * Gets grievance data by key
     * @param grievanceKey Grievance data key
     * @return Map of grievance data
     */
    public static Map<String, String> getGrievanceData(String grievanceKey) {
        JsonNode grievanceNode = testData.get("grievances").get(grievanceKey);
        Map<String, String> grievanceData = new HashMap<>();
        
        grievanceNode.fields().forEachRemaining(entry -> 
            grievanceData.put(entry.getKey(), entry.getValue().asText()));
        
        return grievanceData;
    }
}