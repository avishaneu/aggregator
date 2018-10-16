package com.ut.testtasks.aggregator.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ArtistInfo {
    
    private String name;
    
    @JsonProperty("release-groups")
    private List<AlbumInfo> albums;
    
    private List<Relation> relations;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<AlbumInfo> getAlbums() {
        return albums;
    }
    
    public void setAlbums(List<AlbumInfo> albums) {
        this.albums = albums;
    }
    
    public List<Relation> getRelations() {
        return relations;
    }
    
    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }
}
