package dnaCucumber.config;

import org.apache.commons.lang3.text.StrBuilder;
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
    private final String HOST = "postgres.host.name";
    private final String PORT = "postgres.port.num";
    private final String PG_PASSWORD = "postgres.database.password";
    private final String PG_USERNAME = "postgres.database.username";
    private final String DB = "postgres.database.name";
    private final String CONN_STR = "postgres.database.url";
    Properties properties;
    private static final Logger logger = Logger.getLogger(Config.class);

    public static Config getInstance() {
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

    public String getPostGresHost() {
        return String.valueOf(System.getProperty(HOST, properties.getProperty(HOST)));
    }

    public String getPostgresPortNumber() {
        return String.valueOf(System.getProperty(PORT, properties.getProperty(PORT)));
    }

    public String getPostgresuserName() {
        return String.valueOf(System.getProperty(PG_USERNAME, properties.getProperty(PG_USERNAME)));
    }

    public String getPostgresPassword() {
        return String.valueOf(System.getProperty(PG_PASSWORD, properties.getProperty(PG_PASSWORD)));
    }

    public String getPostgresDbName() {
        return String.valueOf(System.getProperty(DB, properties.getProperty(DB)));
    }

    public String getPostgresUrl() {
        return String.valueOf(System.getProperty(CONN_STR, properties.getProperty(CONN_STR)));
    }

}
