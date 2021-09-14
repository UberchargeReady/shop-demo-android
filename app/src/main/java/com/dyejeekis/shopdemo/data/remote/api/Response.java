package com.dyejeekis.shopdemo.data.remote.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public abstract class Response {

    protected abstract void parseJSONObject(JSONObject jsonObject) throws JSONException;

    protected String statusCode, message;

    public String getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    protected void parseResponse(String json) throws JSONException {
        Object obj = new JSONTokener(json).nextValue();
        if (obj instanceof JSONObject) {
            parseJSONObject((JSONObject) obj);
        } else if (obj instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) obj;
            for (int i=0; i<jsonArray.length(); i++) {
                parseJSONObject(jsonArray.getJSONObject(i));
            }
        }
    }
}
