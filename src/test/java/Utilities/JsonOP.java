package Utilities;

import Utilities.cloud.fw.ResourceReader;
import com.google.gson.Gson;
import org.json.JSONObject;

import java.io.IOException;

public final class JsonOP {

    private static volatile JSONObject jsonInstance = null;
    private static volatile JsonOP instance = null;

    public void loadJsonFile(String filePath) throws IOException {
        jsonInstance = new JSONObject(ResourceReader.readFileAsString(filePath));
    }

    public <T> T loadJsonFile(String filePath, Class<T> t) throws IOException {
        Gson g = new Gson();
        String jsonStr = ResourceReader.readFileAsString(filePath);
        return g.fromJson(jsonStr ,t);
    }

    public void loadJsonString(String json){
        jsonInstance= new JSONObject(json);
    }

    public <T> T deserialiseJson(String json, Class<T> t){
        Gson g = new Gson();
        return g.fromJson(json, t);
    }

    public <T> String serialiseJson(T t){
        Gson g = new Gson();
        return g.toJson(t);
    }


    public JSONObject getJsonObjectByKeys(String keys){
        // separate by .
        String[] keyarr = keys.split("\\.");
        JSONObject obj = jsonInstance.getJSONObject(keyarr[0]);
        for(int i=1; i< keyarr.length; i++){
            obj = obj.getJSONObject(keyarr[i]);
        }
        return obj;
    }

    public JSONObject setJsonValue(String path, String key, String value){
        return getJsonObjectByKeys(path).put(key,value);
    }

    public String getJsonAsString(){
        return jsonInstance.toString();
    }

    public static JsonOP getInstance(){
        if (instance == null) {
            synchronized (JsonOP.class) {
                if (instance == null) {
                    instance = new JsonOP();
                }
            }
        }

        return instance;
    }
}
