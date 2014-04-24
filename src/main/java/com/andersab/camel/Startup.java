package com.andersab.camel;


import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.main.Main;

import java.util.List;

public class Startup {

    private Main camelMain;

    /**
     * Protect the main from being instantiated outside
     */
    private Startup() {
    }

    /**
     * Main method
     *
     * @param args the arguments passed from command line
     * @throws Exception if the bootstrap cannot instantiate
     */
    public static void main(String[] args) throws Exception {
        Startup startup = new Startup();
        startup.boot();
    }

    /**
     * Boots the process up
     *
     * @throws Exception
     */
    private void boot() throws Exception {
        camelMain = new Main();
        camelMain.enableHangupSupport();
        DefaultCamelContext loaderContext = new DefaultCamelContext();

//        EnvironmentLoader.loadProperties();
//        EnvironmentLoader.loadRoutes(loaderContext);
        camelMain.getCamelContexts().add(loaderContext);

        camelMain.run();
    }
}
