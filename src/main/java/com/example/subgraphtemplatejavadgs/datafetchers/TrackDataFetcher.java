package com.example.subgraphtemplatejavadgs.datafetchers;

import com.example.subgraphtemplatejavadgs.Track;
import com.example.subgraphtemplatejavadgs.TrackModule;
import com.example.subgraphtemplatejavadgs.TrackMutationResponse;
import com.netflix.graphql.dgs.*;
import com.example.subgraphtemplatejavadgs.services.RestService;
import org.dataloader.DataLoader;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@DgsComponent
public class TrackDataFetcher {

    private final RestService tracksService;

    public TrackDataFetcher(RestService tracksService) {
        this.tracksService = tracksService;
    }

    @DgsQuery
    public List<Track> tracks() throws IOException, InterruptedException {
        return tracksService.load();
    }

    // set up data loader pattern in its own data fetcher
    @DgsData(parentType="Track")
    public List<TrackModule> modules(DgsDataFetchingEnvironment dfe) throws IOException, InterruptedException {
        Track track = dfe.getSource();
        return tracksService.loadModules(track.getId());
    };

    @DgsMutation
    public TrackMutationResponse incrementTrackViews(@InputArgument String id) throws IOException, InterruptedException {
        return tracksService.incrementTrackViews(id);
    }


}
