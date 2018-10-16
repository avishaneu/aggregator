package com.ut.testtasks.aggregator.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import javax.management.relation.Relation;

import com.ut.testtasks.aggregator.controller.response.AlbumInfoResponse;
import com.ut.testtasks.aggregator.controller.response.ArtistInfoResponse;
import com.ut.testtasks.aggregator.model.AlbumInfo;
import com.ut.testtasks.aggregator.model.ArtistInfo;
import com.ut.testtasks.aggregator.repository.AlbumCoverRepository;
import com.ut.testtasks.aggregator.repository.ArtistDescriptionRepository;
import com.ut.testtasks.aggregator.repository.ArtistInfoRepository;
import com.ut.testtasks.aggregator.repository.impl.DiscogsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistInfoService {
    
    private ArtistInfoRepository artistInfoRepository;
    private ArtistDescriptionRepository artistDescriptionRepository;
    private AlbumCoverRepository albumCoverRepository;
    
    private ExecutorService executor;
            
    @Autowired
    public ArtistInfoService(ArtistInfoRepository artistInfoRepository, 
            ArtistDescriptionRepository artistDescriptionRepository, AlbumCoverRepository albumCoverRepository,
            ExecutorService executor) {
        this.artistInfoRepository = artistInfoRepository;
        this.artistDescriptionRepository = artistDescriptionRepository;
        this.albumCoverRepository = albumCoverRepository;
        this.executor = executor;
    }
    
    public ArtistInfoResponse getAggregatedArtistInfo(String mbid) throws ExecutionException, InterruptedException {
        
        ArtistInfo artistInfo = artistInfoRepository.getArtistInfo(mbid);
       
        
        Optional<String> externalId = artistDescriptionRepository.extractId(artistInfo);
        Future<String> artistDescription =  CompletableFuture.completedFuture("");
        if (externalId.isPresent()) {
           artistDescription = executor.submit(() -> artistDescriptionRepository.getArtistDescription(externalId.get()));
        }
    
        Map<String, Future<String>> albumCovers = new HashMap<>();
        for (AlbumInfo album : artistInfo.getAlbums()) {
            albumCovers.put(album.getId(), executor.submit(() -> albumCoverRepository.getAlbumCover(album.getId())));
        }
        
        ArtistInfoResponse aggregatedInfo = new ArtistInfoResponse(mbid);
        aggregatedInfo.setDescription(artistDescription.get());
        aggregatedInfo.setName(artistInfo.getName());
    
        for (AlbumInfo album : artistInfo.getAlbums()) {
            AlbumInfoResponse albumInfoResponse = new AlbumInfoResponse();
            albumInfoResponse.setId(album.getId());
            albumInfoResponse.setTitle(album.getTitle());
            albumInfoResponse.setImage(albumCovers.get(album.getId()).get());
            aggregatedInfo.getAlbums().add(albumInfoResponse);
        }
    
        return aggregatedInfo;
    }
}
