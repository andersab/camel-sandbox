package com.andersab.camel;

import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class EnvironmentLoaderTest {

    /*@EndpointInject(uri = "mockResult")
    protected MockEndpoint resultEndpoint;
*/

    /**
     * The following will load configuration properties from the classpath
     *
     * @throws Exception
     */
    @Test
    public void testLoadingPropertiesFromClasspath() throws Exception {
        final String configurationPropertyFile = "config/loader.properties";
        final String propertyInFile = "routesDir";

        System.setProperty(EnvironmentLoader.LOADER_SYSTEM_PROPERTY_NAME, configurationPropertyFile);
        EnvironmentLoader loader = new EnvironmentLoader();
        loader.loadProperties();
        String result = System.getProperty(propertyInFile);
        assertNotNull(result);

        String message = String.format("Assertion failure, check file ( %s ), looking for key ( %s )", configurationPropertyFile, propertyInFile);
        assertEquals(message,"routes", result);
    }
}
