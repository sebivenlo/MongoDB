package com.workshop.mongodb.lamaoffice.model;


import com.google.inject.Singleton;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public final class PropertyContext {
    public final Logger logger;
    public final Properties properties;

    public PropertyContext() {
        logger = Logger.getLogger(this.getClass().getName());
        properties = new Properties();

        try {
            InputStream input = new FileInputStream("./properties/config.properties");
            // load a properties file
            properties.load(input);

        } catch (FileNotFoundException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        } catch (IOException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
    }

    public Properties getProperties() {
        return properties;
    }
}
