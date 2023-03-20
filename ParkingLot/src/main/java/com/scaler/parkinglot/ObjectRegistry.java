package com.scaler.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ObjectRegistry {

    Map<String, Object> objectMap = new HashMap<>();

    public void register(String key, Object object) {
        objectMap.put(key, object);
    }

    public Object get(String key) {
        return objectMap.get(key);
    }
}
