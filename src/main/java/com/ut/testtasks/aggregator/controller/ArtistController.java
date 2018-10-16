package com.ut.testtasks.aggregator.controller;

import java.util.concurrent.ExecutionException;

import com.ut.testtasks.aggregator.controller.response.ArtistInfoResponse;
import com.ut.testtasks.aggregator.service.ArtistInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArtistController {
    
    private ArtistInfoService artistInfoService;
    
    @Autowired
    public ArtistController(ArtistInfoService artistInfoService) {
        this.artistInfoService = artistInfoService;
    }
    
    @RequestMapping("/{mbid}")
    public ArtistInfoResponse getArtistInfo(@PathVariable String mbid) throws InterruptedException, ExecutionException {
        return artistInfoService.getAggregatedArtistInfo(mbid);
    }
}
