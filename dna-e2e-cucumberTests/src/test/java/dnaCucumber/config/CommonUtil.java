package dnaCucumber.config;

import com.google.gson.Gson;
import com.google.gson.internal.Streams;
import com.mastek.dna.model.Individual;
import dnaCucumber.dtos.IDto;
import dnaCucumber.utils.RestClient;

import java.util.HashMap;

import static jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java;

/**
 * Created by Vikram103069 on 14/10/2016.
 */
public class CommonUtil {

    private static CommonUtil instance = null;
    private CommonUtil() {

    }
    public static CommonUtil getInstance() {
        if(instance == null) {
            instance = new CommonUtil();
        }
        return instance;
    }

    public <T extends Individual> void  updateRecord(T dto, String URI) {
        Config config = Config.instane();
        RestClient restClient = new RestClient(config.getBaseUrl()+URI, new HashMap<>());
        restClient.setAuthentication(config.getUsername(), config.getPassword());
        Gson gson = new Gson();
        String personRecord = gson.toJson(dto);
        restClient.post(personRecord);
    }
}
