package com.example.languagedictionary.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;
public class Word {

    @SerializedName("word")
    private String word;

    @SerializedName("phonetic")
    private String phonetic;

    @SerializedName("meanings")
    private List<Meaning> meanings;

//    Get set
    public String getWord() {return word;}
    public void setWord(String word) { this.word = word;}

    public String getPhonetic() {return phonetic;}
    public void setPhonetic(String phonetic) {this.phonetic = phonetic;}

    public List<Meaning> getMeanings() {return meanings;}
    public void setMeanings(List<Meaning> meanings) {this.meanings = meanings;}




}
