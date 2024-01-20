package edu.pb.model.words;

public class EnglishWord extends Word{
    //public String translations;

    public EnglishWord(String name, String definition, String translations) {
        this.name = name;
        this.definition = definition;
        this.translation = translations;
        this.length = name.length();
    }
}
