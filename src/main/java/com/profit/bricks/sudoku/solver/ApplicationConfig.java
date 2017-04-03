/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.profit.bricks.sudoku.solver;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

/**
 *It represents jersey implementation of in custom application model.
 * For simple there is no need web.xml
 * @ApplicationPath annotation can be used to annotate the user defined application class
 * 
 */
@ApplicationPath("rest")
public class ApplicationConfig extends ResourceConfig {
    
    private final static Logger logger = Logger.getLogger(ApplicationConfig.class.getName());

    public ApplicationConfig() {
        property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, "true");
        packages("com.profit.bricks.sudoku.solver");
        register(JacksonJsonProvider.class); 
        
        logger.log(Level.INFO, "Resource config loaded...");
    }

   
}
