package com.andersab.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.model.ModelCamelContext;
import org.apache.camel.model.RoutesDefinition;

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
     * Directory in classpath to load routes from
     */
    public static final String ROUTES_XML_PROP_NAME = "routesXml";

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
        Properties runtimeProps = System.getProperties();
        InputStream propertyFileResourceStream = getInputStreamFromClasspath(LOADER_SYSTEM_PROPERTY_NAME);
        runtimeProps.load(propertyFileResourceStream);
        System.setProperties(runtimeProps);
    }

    /**
     * Loads the routes from the xml in the classpath
     * @param camelContext
     */
    public static void loadRoutes(ModelCamelContext camelContext) throws Exception {
        String routesDir = System.getProperty(ROUTES_XML_PROP_NAME);
        InputStream routesInputStream = getInputStreamFromClasspath(ROUTES_XML_PROP_NAME);
        RoutesDefinition routes = camelContext.loadRoutesDefinition(routesInputStream);
        camelContext.addRouteDefinitions(routes.getRoutes());
    }

    private static InputStream getInputStreamFromClasspath(String artifactName) {
        String runtimePropertyLocation = System.getProperty(artifactName);
        return EnvironmentLoader.class.getClassLoader().getResourceAsStream(runtimePropertyLocation);
    }
}
