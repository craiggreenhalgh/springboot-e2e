package dnaCucumber.config;

import org.apache.log4j.Logger;

import java.util.Properties;

/**
 * Created by Vikram103069 on 13/10/2016.
 */
public class Config {

    private static Config config;
    private static final String PATH = "src/test/resources/dna/default.properties";
    private static final String BASEURL = "baseurl";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    Properties properties;
    private static final Logger logger = Logger.getLogger(Config.class);

    public static Config instane() {
        if(config==null)
            config = new Config();
        return config;
    }

    public Config() {
        if(properties == null) {
            properties = new Properties(System.getProperties());
        }
        PropertiesUtility.setUpProperties(PATH, properties);

    }

    public String getBaseUrl() {
        return String.valueOf(System.getProperty(BASEURL, properties.getProperty(BASEURL)));
    }

    public String getUsername() {
        return String.valueOf(System.getProperty(USERNAME, properties.getProperty(USERNAME)));
    }

    public String getPassword() {
        return String.valueOf(System.getProperty(PASSWORD, properties.getProperty(PASSWORD)));
    }

}
