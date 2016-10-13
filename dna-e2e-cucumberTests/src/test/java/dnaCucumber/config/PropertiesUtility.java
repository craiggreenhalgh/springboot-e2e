package dnaCucumber.config;

import java.io.*;
import java.util.Properties;

public class PropertiesUtility {

    private static final String APACHE_LOGGER = "org.apache.commons.logging.Log";
    private static final String APACHE_JDK4LOGGER = "org.apache.commons.logging.impl.Jdk14Logger";


    public static void setProperty(String propertyname, String value) {
        System.setProperty(propertyname, value);
    }

    public static String getProperty(String propertyName) {
        if (propertyName.equals(""))
            return propertyName;
        return System.getProperty(propertyName, "");
    }

    public static boolean isPropertyNameEmpty(String propertyName) {
        return System.getProperty(propertyName, "").equals("") || propertyName.equals("");
    }

    public static void loadDefaultPropertyFiles() {
//        setUpProperties("", properties);
        System.setProperty(APACHE_LOGGER, APACHE_JDK4LOGGER);
    }

    public static void setUpProperties(String filePath, Properties properties) {
        InputStream propFile = null;
        try {

            if (new File(filePath).exists()) {
                propFile = new FileInputStream(filePath);
            } else {
                propFile = config.PropertiesUtil.class.getClassLoader().getResourceAsStream(filePath);
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException("Failed to read input stream from file: " + filePath, e);
        }
        if (propFile == null) {
            throw new RuntimeException("Failed to read input stream from file: " + filePath);
        }

        try {
            properties.load(propFile);
            System.setProperties(properties);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read run configuration properties from file:" + filePath, e);
        }
    }
}
