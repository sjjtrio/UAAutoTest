package Utilities;

import java.util.HashMap;

public class TestContext {
    private static volatile TestContext instance = null;
    private HashMap<String, Object> map = new HashMap<>();

    public static TestContext getInstance() {
        if (instance == null) {
            synchronized (TestContext.class) {
                if (instance == null) {
                    instance = new TestContext();
                }
            }
        }
        return instance;
    }

    public <T>T get(String Key){
        return (T)map.get(Key);
    }

    public <T> void set(String Key, T t){
        map.put(Key, t);
    }

}
