package dnaCucumber.config;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

/**
 * Created by Vikram103069 on 14/10/2016.
 */
public class CommonUtil {

    private static CommonUtil instance = null;
    private String scenarioVal;

    private CommonUtil() {

    }
    public static CommonUtil getInstance() {
        if(instance == null) {
            instance = new CommonUtil();
        }
        return instance;
    }

    public static String convertToJson(Object dto, boolean localDateToString) {
        if(localDateToString) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
                @Override
                public JsonElement serialize(LocalDate localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
                    return new JsonPrimitive(localDateTime.toString());
                }
            });
            Gson gson = gsonBuilder.create();
            return gson.toJson(dto);
        }
        Gson gson = new Gson();
        return gson.toJson(dto);
    }

    public void setScenarioVal(String scenarioVal) {
        this.scenarioVal = scenarioVal;
    }

    public String getScenarioVal() {
        return scenarioVal;
    }
}