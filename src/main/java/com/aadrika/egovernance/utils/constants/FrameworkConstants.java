package com.aadrika.egovernance.utils.constants;

/**
 * Framework level constants
 */
public final class FrameworkConstants {
    
    private FrameworkConstants() {}
    
    // Timeouts
    public static final int EXPLICIT_WAIT_TIMEOUT = 10;
    public static final int IMPLICIT_WAIT_TIMEOUT = 5;
    public static final int PAGE_LOAD_TIMEOUT = 30;
    
    // Paths
    public static final String CONFIG_PATH = "src/test/resources/config/";
    public static final String TESTDATA_PATH = "src/test/resources/testdata/";
    public static final String REPORTS_PATH = "reports/";
    public static final String SCREENSHOTS_PATH = "screenshots/";
    
    // File Extensions
    public static final String PROPERTIES_EXTENSION = ".properties";
    public static final String EXCEL_EXTENSION = ".xlsx";
    public static final String JSON_EXTENSION = ".json";
}