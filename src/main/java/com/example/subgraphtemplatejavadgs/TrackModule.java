package com.example.subgraphtemplatejavadgs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrackModule {
    @JsonProperty("id")
    public String id;
    @JsonProperty("title")
    public String title;

    public String getId() {
        return id;
    }
}
