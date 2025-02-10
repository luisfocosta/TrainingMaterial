package com.amica.help;

public class Synonym {
    public String tag;
    public String synonym;

    public Synonym(String tag, String synonym) {
        this.tag = tag;
        this.synonym = synonym;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag.toLowerCase();
    }

    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

}
