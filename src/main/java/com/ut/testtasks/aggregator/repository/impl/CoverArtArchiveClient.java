package com.ut.testtasks.aggregator.repository.impl;

import com.ut.testtasks.aggregator.model.AlbumCover;
import com.ut.testtasks.aggregator.repository.AlbumCoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Repository
public class CoverArtArchiveClient implements AlbumCoverRepository {
    
    private static final String BASE_URL = "http://coverartarchive.org/release-group/";
    
    private RestTemplate restTemplate;
    
    @Autowired
    public CoverArtArchiveClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    private String createUrl(String id) {
        return BASE_URL + id;
    }
    
    @Override public String getAlbumCover(String mbid) {
        AlbumCover cover = null;
        try {
            cover = restTemplate.getForObject(createUrl(mbid), AlbumCover.class);
        } catch (HttpClientErrorException e) {
            System.out.println(e);
        }
        return cover == null ? "" : cover.getUrl();
    }
}
