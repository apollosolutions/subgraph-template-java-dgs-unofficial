package com.example.subgraphtemplatejavadgs;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Track {
    @JsonProperty("id")
    public String id;
    @JsonProperty("title")
    public String title;
    @JsonProperty("authorId")
    public String authorId;

    @JsonProperty("numberOfViews")
    public int numberOfViews;

    @JsonProperty("modules")
    public String[] modules;

    public String getId() {
        return id;
    }
}
