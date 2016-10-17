package dnaCucumber.config;

import com.google.gson.*;
import com.mastek.dna.model.Individual;
import dnaCucumber.utils.RestClient;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;

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
        Config config = Config.getInstance();
        RestClient restClient = new RestClient(config.getBaseUrl()+URI, new HashMap<>());
        restClient.setAuthentication(config.getUsername(), config.getPassword());
//        Gson gson = new Gson();
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
            @Override
            public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                Instant instant = Instant.ofEpochMilli(json.getAsJsonPrimitive().getAsLong());
                return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            }
        }).create();

//            Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
        String personRecord = gson.toJson(dto);
        restClient.post(personRecord);
    }
}
