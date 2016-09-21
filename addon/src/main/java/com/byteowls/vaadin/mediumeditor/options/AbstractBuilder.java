package com.byteowls.vaadin.mediumeditor.options;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import elemental.json.Json;
import elemental.json.JsonArray;
import elemental.json.JsonObject;
import elemental.json.JsonValue;

public abstract class AbstractBuilder<T> {

    protected void putNotNull(JsonObject obj, String key, Map<String, String> map) {
        if (map != null) {
            JsonObject mapObj = Json.createObject();
            for (Entry<String, String> entry : map.entrySet()) {
                mapObj.put(entry.getKey(), entry.getValue());
            }
            obj.put(key, mapObj);
        }
    }

    protected void putNotNull(JsonObject obj, String key, List<String> list) {
        if (list != null) {
            JsonArray arr = Json.createArray();
            for (String entry : list) {
                arr.set(arr.length(), entry);
            }
            obj.put(key, arr);
        }
    }

    protected void putNotNull(JsonObject obj, String key, Boolean value) {
        if (value != null) {
            obj.put(key, value);
        }
    }

    protected void putNotNull(JsonObject obj, String key, String value) {
        if (value != null) {
            obj.put(key, value);
        }
    }


    protected void putNotNull(JsonObject obj, String key, JsonValue value) {
        if (value != null) {
            obj.put(key, value);
        }
    }

    protected void putNotNull(JsonObject obj, String key, Number value) {
        if (value != null) {
            obj.put(key, (JsonValue) value);
        }
    }

    /**
     * For internal use only.
     */
    public abstract T build();

    /**
     * For internal use only.
     */
    public abstract JsonValue buildJson();

}
