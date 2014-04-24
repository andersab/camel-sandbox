package com.andersab.camel;

import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.ModelCamelContext;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.RoutesDefinition;
import org.junit.Test;

import java.io.InputStream;
import java.util.Collection;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EnvironmentLoaderTest {

    public static final String ROUTE_XML = "routes/myRoutes.xml";

    /**
     * The following will load configuration properties from the classpath
     *
     * @throws Exception
     */
    @Test
    public void testLoadingPropertiesFromClasspath() throws Exception {
        final String configurationPropertyFile = "config/loader.properties";
        final String propertyInFile = EnvironmentLoader.ROUTES_XML_PROP_NAME;

        System.setProperty(EnvironmentLoader.LOADER_SYSTEM_PROPERTY_NAME, configurationPropertyFile);
        EnvironmentLoader.loadProperties();
        String result = System.getProperty(propertyInFile);
        assertNotNull(result);

        String message = String.format("Assertion failure, check file ( %s ), looking for key ( %s )", configurationPropertyFile, propertyInFile);
        assertEquals(message, ROUTE_XML, result);
    }

    /**
     * Test loading the routes in the path to the CamelContext
     */
    /*@Test
    public void testLoadingRoutesFromClasspathToCamelContext() throws Exception {
        final String routesXml = ROUTE_XML;
        System.setProperty(EnvironmentLoader.ROUTES_XML_PROP_NAME, routesXml);
        FakeCamelContext fakeCamelContext = new FakeCamelContext();
        EnvironmentLoader.loadRoutes(fakeCamelContext);
        assertTrue(fakeCamelContext.isLoadRoutesCalled());
        assertTrue(fakeCamelContext.isAddRoutesCalled());
    }*/

    private class FakeCamelContext extends DefaultCamelContext {
        private boolean loadRoutesCalled = false;
        private boolean addRoutesCalled = false;

        @Override
        public synchronized RoutesDefinition loadRoutesDefinition(InputStream is) throws Exception {
            this.loadRoutesCalled = true;
            return super.loadRoutesDefinition(is);
        }

        @Override
        public synchronized void addRouteDefinitions(Collection<RouteDefinition> routeDefinitions) throws Exception {
            this.addRoutesCalled = true;
            super.addRouteDefinitions(routeDefinitions);    //To change body of overridden methods use File | Settings | File Templates.
        }

        private boolean isLoadRoutesCalled() {
            return loadRoutesCalled;
        }

        private boolean isAddRoutesCalled() {
            return addRoutesCalled;
        }
    }
}
