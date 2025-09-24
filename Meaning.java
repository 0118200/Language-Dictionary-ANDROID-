package com.example.languagedictionary.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Meaning {
    @SerializedName("partOfSpeech")
    private String partOfSpeech;

    @SerializedName("definitions")
    private List<Definition> definitions;

    public String getPartOfSpeech() { return partOfSpeech; }
    public List<Definition> getDefinitions() { return definitions; }
}
