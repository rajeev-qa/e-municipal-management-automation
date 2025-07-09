package com.aadrika.egovernance.enums;

/**
 * Enum for different test environments
 */
public enum Environment {
    DEV("dev"),
    STAGING("staging"),
    PROD("prod");
    
    private final String environmentName;
    
    Environment(String environmentName) {
        this.environmentName = environmentName;
    }
    
    public String getEnvironmentName() {
        return environmentName;
    }
}