package com.ut.testtasks.aggregator.repository;

import com.ut.testtasks.aggregator.model.ArtistInfo;

public interface ArtistInfoRepository {
    ArtistInfo getArtistInfo(String id);
}
