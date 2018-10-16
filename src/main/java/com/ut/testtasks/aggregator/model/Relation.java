package com.ut.testtasks.aggregator.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Relation {
    
    private String type;
    private String url;
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    @JsonProperty("url")
    private void unpackNameFromNestedObject(Map<String, String> url) {
        this.url = url.get("resource");
    }
}
