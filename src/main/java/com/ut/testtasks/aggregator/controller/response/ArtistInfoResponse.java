package com.ut.testtasks.aggregator.controller.response;

import java.util.ArrayList;
import java.util.List;

public class ArtistInfoResponse {
    
    private String mbid;
    private String name;
    private String description;
    private List<AlbumInfoResponse> albums = new ArrayList<>();
    
    public ArtistInfoResponse(String mbid) {
        this.mbid = mbid;
    }
    
    public String getMbid() {
        return mbid;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public List<AlbumInfoResponse> getAlbums() {
        return albums;
    }
    
    public void setAlbums(List<AlbumInfoResponse> albums) {
        this.albums = albums;
    }
}
