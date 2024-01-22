package edu.pb.model.words;

import java.util.HashSet;
import java.util.Set;

public class GermanWord extends Word{
    public static Set<Character> specialCharacters = initializeSpecialCharacters();

    public GermanWord(String name, String definition, String translations){
        this.name = name;
        this.length = name.length();
        this.definition = definition;
        this.translation = translations;
    }
    private static Set<Character> initializeSpecialCharacters() {
        Set<Character> specialCharacters = new HashSet<>();

        specialCharacters.add('ä');
        specialCharacters.add('ö');
        specialCharacters.add('ü');
        specialCharacters.add('ß');

        return specialCharacters;
    }
}
