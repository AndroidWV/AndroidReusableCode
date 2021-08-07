package com.org.wvprojectstructure.viewModel;
import com.google.gson.JsonObject;

public class ResponseModel {
    private JsonObject json;
    private String from;

    public ResponseModel(JsonObject json, String from) {
        this.json = json;
        this.from = from;
    }

    public JsonObject getJson() {
        return json;
    }

    public String getFrom() {
        return from;
    }
}
