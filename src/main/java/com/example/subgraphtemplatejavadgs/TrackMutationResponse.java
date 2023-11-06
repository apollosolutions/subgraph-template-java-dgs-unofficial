package com.example.subgraphtemplatejavadgs;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import reactor.core.publisher.MonoSink;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrackMutationResponse {
    @JsonProperty("code")
    public int code;
    @JsonProperty("success")
    public boolean success;
    @JsonProperty("message")
    public String message;
    @JsonProperty("track")
    public Track track;

    public TrackMutationResponse(int code, boolean success, String message, Track track) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.track = track;
    }
}
