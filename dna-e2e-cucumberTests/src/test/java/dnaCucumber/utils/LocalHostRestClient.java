package dnaCucumber.utils;

import dnaCucumber.config.Config;

import java.util.HashMap;

/**
 * Created by Vikram103069 on 17/10/2016.
 */
public class LocalHostRestClient extends RestClient{

    public LocalHostRestClient(String URI) {
        super(Config.getInstance().getBaseUrl().concat(URI), new HashMap<>());
        setAuthentication(Config.getInstance().getUsername(), Config.getInstance().getPassword());
    }
}
