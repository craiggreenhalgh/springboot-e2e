package dnaCucumber.utils;

import com.google.gson.*;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vikram103069 on 11/10/2016.
 */
public class JsonUtil {

    private JsonObject parse;
    private DocumentContext context;

    public JsonUtil(){

    }
    public JsonUtil(String filePath) throws Exception{
        parse(filePath);
        context = JsonPath.parse(new File(filePath));
    }

    public <T> T evaluatePath(String path, Class<T> tClass){
        try {
            return context.read(path, tClass);
        }
        catch(Exception ex){
            throw ex;
        }
    }

    public List<String> getKeysByArrayValue(String value){
        List<String> keys = new ArrayList<>();
        for(Map.Entry<String, JsonElement> set : parse.entrySet()) {
            JsonArray array = null;
            if(set.getValue().isJsonArray()) {
                array = set.getValue().getAsJsonArray();
                for (JsonElement element : array) {
                    if (element.getAsString().toLowerCase().contains(value))
                        keys.add(set.getKey());
                    break;
                }
            }
            else if(set.getValue().isJsonObject()){
                HashMap map = new Gson().fromJson(set.getValue(),HashMap.class);
                if(map.containsValue(value)){

                }
            }
        }
        return keys;
    }

    public String getKeyByValue(String value){
        return getKeysByArrayValue(value).get(0);
    }

    public JsonElement getValueByKey(String key){
        return parse.get(key);
    }

    public static String getValueByKey(final String content, final String key) {

        String toRet = null;
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(content);
        if (jsonElement.isJsonArray()) {
            JsonObject object = jsonElement.getAsJsonArray().get(0).getAsJsonObject();
            toRet = object.get(key).getAsString();
        }
        else if (jsonElement.isJsonObject()) {
            toRet = jsonElement.getAsJsonObject().get(key).getAsString();
        }
        return toRet;
    }

    public List<String> getValuesByKey(String key){
        JsonElement ele = parse.get(key);
        List<String> items = new ArrayList<>();
        if(ele.isJsonArray()) {
            for (JsonElement element : ele.getAsJsonArray()) {
                items.add(element.getAsString());
            }
        }
        return items;
    }

    JsonObject parse(String filePath) throws Exception{
        JsonParser parser = new JsonParser();
        parse = (JsonObject) parser.parse(new FileReader(filePath));
        return parse;
    }

    public JsonObject getParsedObject() {
        return parse;
    }
}
