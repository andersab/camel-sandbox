package com.andersab.camel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Handles injection properties and other environmental
 * properties during runtime
 */
public class EnvironmentLoader {
    /**
     * The system property that the runtime properties are injected from
     */
    public static final String LOADER_SYSTEM_PROPERTY_NAME = "loaderProperties";

    /**
     * Private constructor to protect utility class
     */
    private EnvironmentLoader() {
    }

    /**
     * Loads properties from the system property
     *
     * @see EnvironmentLoader#LOADER_SYSTEM_PROPERTY_NAME
     */
    public static void loadProperties() throws IOException {
        Properties runtimeProps = new Properties();
        String runtimePropertyLocation = System.getProperty(LOADER_SYSTEM_PROPERTY_NAME);
        InputStream propertyFileResourceStream = EnvironmentLoader.class.getClassLoader().getResourceAsStream(runtimePropertyLocation);
        runtimeProps.load(propertyFileResourceStream);
        System.setProperties(runtimeProps);
    }


}
