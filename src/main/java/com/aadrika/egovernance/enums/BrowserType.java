package com.aadrika.egovernance.enums;

/**
 * Enum for supported browser types
 */
public enum BrowserType {
    CHROME("chrome"),
    FIREFOX("firefox"),
    EDGE("edge"),
    SAFARI("safari");
    
    private final String browserName;
    
    BrowserType(String browserName) {
        this.browserName = browserName;
    }
    
    public String getBrowserName() {
        return browserName;
    }
}