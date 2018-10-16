package com.ut.testtasks.aggregator.repository;

import java.util.Optional;

import com.ut.testtasks.aggregator.model.ArtistInfo;

public interface ArtistDescriptionRepository {
    
    String getArtistDescription(String id);
    
    Optional<String> extractId(ArtistInfo artistInfo);
}
