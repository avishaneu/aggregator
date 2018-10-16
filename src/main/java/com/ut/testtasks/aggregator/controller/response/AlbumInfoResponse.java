package com.ut.testtasks.aggregator.controller.response;

public class AlbumInfoResponse {
    
    private String id;
    private String title;
    private String image;
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    
    public String getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getImage() {
        return image;
    }
}
