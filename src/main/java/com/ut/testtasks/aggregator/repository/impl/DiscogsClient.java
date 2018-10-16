package com.ut.testtasks.aggregator.repository.impl;

import java.util.Optional;

import com.ut.testtasks.aggregator.model.ArtistDescription;
import com.ut.testtasks.aggregator.model.ArtistInfo;
import com.ut.testtasks.aggregator.model.Relation;
import com.ut.testtasks.aggregator.repository.ArtistDescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class DiscogsClient implements ArtistDescriptionRepository {
    
    private static final String BASE_URL = "https://api.discogs.com/artists/";
    
    private RestTemplate restTemplate;
    
    @Autowired
    public DiscogsClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    private String createUrl(String id) {
        return BASE_URL + id;
    }
    
    @Override 
    public String getArtistDescription(String id) {
       ArtistDescription description = restTemplate.getForObject(createUrl(id), ArtistDescription.class);
       return  description == null ? "" : description.getProfile();
    }
    
    public Optional<String> extractId(ArtistInfo artistInfo) {
        Optional<Relation> discogRelation = artistInfo.getRelations().stream().filter(rel -> rel.getType().equals("discogs")).findFirst();
        if (!discogRelation.isPresent()) {
            return Optional.empty();
        } else {
          String[] urlParts = discogRelation.get().getUrl().split("/");
          return Optional.of(urlParts[urlParts.length - 1]);
        }
    }
}
