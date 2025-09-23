package com.example.languagedictionary.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Definition {

    @SerializedName("definition")
    private String definition;

    @SerializedName("example")
    private String example;

    @SerializedName("synonyms")
    private List<String> synonyms;

    @SerializedName("antonyms")
    private List<String> antonyms;

    // Getter & Setter
    public String getDefinition() { return definition; }
    public void setDefinition(String definition) { this.definition = definition; }

    public String getExample() { return example; }
    public void setExample(String example) { this.example = example; }

    public List<String> getSynonyms() { return synonyms; }
    public void setSynonyms(List<String> synonyms) { this.synonyms = synonyms; }

    public List<String> getAntonyms() { return antonyms; }
    public void setAntonyms(List<String> antonyms) { this.antonyms = antonyms; }
}
