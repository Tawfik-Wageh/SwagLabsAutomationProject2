package com.swaglabs.Utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;


public class PropertiesUtil {

    // Private constructor to prevent instantiation
    private PropertiesUtil() {

    }

    public final static String PROPERTIES_PATH = "src/main/resources";

    // Loads properties from all .properties files in the specified directory.
    public static Properties loadProperties(){
        try{
            Properties properties = new Properties();
            Collection<File> propertiesFilesList ;
            propertiesFilesList = FileUtils.listFiles(new File(PROPERTIES_PATH), new String[]{"properties"} , true );
            propertiesFilesList.forEach(propertyFile -> {
                try {
                    properties.load(new FileInputStream(propertyFile));
                } catch (IOException ioe) {
                    LogsUtil.error(ioe.getMessage());
                }
                properties.putAll(System.getProperties());
                System.getProperties().putAll(properties);
            });
            LogsUtil.info("Loading Properties File Data");
            return properties;
        } catch (Exception e) {
            LogsUtil.error("Failed to load properties: " + e.getMessage());
            return null;
        }
    }

    // Returns the value of a system property by its key.
    public static String getPropertyValue(String key) {
        try{
            return System.getProperty(key);
        } catch (Exception e) {
            LogsUtil.error(e.getMessage());
            return "";
        }

    }
}
