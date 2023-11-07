package com.example.subgraphtemplatejavadgs.services;

import com.example.subgraphtemplatejavadgs.Track;
import com.example.subgraphtemplatejavadgs.TrackModule;
import com.example.subgraphtemplatejavadgs.TrackMutationResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.List;




@Service
public class RestService {

   public List<Track> load() throws IOException, InterruptedException {
       HttpRequest postRequest = (HttpRequest) HttpRequest.newBuilder()
           .uri(URI.create("https://odyssey-lift-off-rest-api.herokuapp.com/tracks")).build();

       HttpClient httpClient = HttpClient.newHttpClient();

       HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());

       String jsonString = postResponse.body();

       ObjectMapper mapper = new ObjectMapper();

       return mapper.readerForListOf(Track.class).readValue(jsonString);
   };

   public List<TrackModule> loadModules(String id) throws IOException, InterruptedException {
       HttpRequest postRequest = (HttpRequest) HttpRequest.newBuilder()
           .uri(URI.create("https://odyssey-lift-off-rest-api.herokuapp.com/track/" + id + "/modules")).build();

       HttpClient httpClient = HttpClient.newHttpClient();

       HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());

       String jsonString = postResponse.body();


       ObjectMapper mapper = new ObjectMapper();

       return mapper.readerForListOf(TrackModule.class).readValue(jsonString);
   };

   public TrackMutationResponse incrementTrackViews(String id) throws IOException, InterruptedException {

       HttpRequest patchRequest = (HttpRequest) HttpRequest.newBuilder()
           .uri(URI.create("https://odyssey-lift-off-rest-api.herokuapp.com/track/" + id + "/numberOfViews")).method("PATCH", HttpRequest.BodyPublishers.ofString("id")).build();


       HttpClient httpClient = HttpClient.newHttpClient();

       HttpResponse<String> patchResponse = httpClient.send(patchRequest, HttpResponse.BodyHandlers.ofString());

       String jsonString = patchResponse.body();

       int status = patchResponse.statusCode();

       if (status != 200) {
           String error = patchResponse.body();
           return new TrackMutationResponse(status, false, error, null);
       }


       ObjectMapper mapper = new ObjectMapper();

       Track track = mapper.readerFor(Track.class).readValue(jsonString);

       return new TrackMutationResponse(status, true, "Successfully updated number of views", track);


   }


}
