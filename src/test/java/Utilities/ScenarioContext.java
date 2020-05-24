package Utilities;

import java.util.HashMap;

public class ScenarioContext {

    private static volatile ScenarioContext instance = null;
    private HashMap<String, Object> map = new HashMap<>();
    private String ScenarioName;

    public static ScenarioContext getInstance() {
        if (instance == null) {
            synchronized (ScenarioContext.class) {
                if (instance == null) {
                    instance = new ScenarioContext();
                }
            }
        }
        return instance;
    }

    public void setScenarioContext(String Name){
        if(ScenarioName == null){ ScenarioName = Name;}
        if(!ScenarioName.equals(Name)){
            map.clear();
            ScenarioName = Name;
        }
    }

    public <T>T get(String Key){
        return (T)map.get(Key);
    }

    public <T> void set(String Key, T t){
        map.put(Key, t);
    }
}
