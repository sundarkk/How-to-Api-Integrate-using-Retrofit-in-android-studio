package com.sundar.shopping.interfaces;

import org.json.JSONArray;
import org.json.JSONObject;

public interface ApiResponseHelper {
    void response(JSONObject value);

    void response(JSONArray value);

    void response(boolean value);
}
