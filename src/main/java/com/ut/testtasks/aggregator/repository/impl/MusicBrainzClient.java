package com.ut.testtasks.aggregator.repository.impl;

import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import com.ut.testtasks.aggregator.model.ArtistInfo;
import com.ut.testtasks.aggregator.repository.ArtistInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class MusicBrainzClient implements ArtistInfoRepository {
    
    private static final String BASE_URL = "http://musicbrainz.org/ws/2/artist/%MBID%?&fmt=json&inc=url-rels+release-groups";
    
    private RestTemplate restTemplate;
    
    @Autowired
    public MusicBrainzClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    private String createUrl(String mbid) {
        return BASE_URL.replace("%MBID%", mbid);
    }
    
    @Override 
    public ArtistInfo getArtistInfo(String mbid) {
        return restTemplate.getForObject(createUrl(mbid), ArtistInfo.class);
    }
}
