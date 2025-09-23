package com.example.languagedictionary.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;
public class Meaning {

    @SerializedName("partOfSpeech")
    private String partOfSpeech;

    @SerializedName("definitions")
    private List<Definition> definitions;

    public String getPartOfSpeech() {return partOfSpeech;}
    public void setPartOfSpeech(String partOfSpeech) {this.partOfSpeech = partOfSpeech;}

    public List<Definition> getDefinitions() {return definitions;}
    public void setDefinitions(List<Definition> definitions) {this.definitions = definitions;}

}
