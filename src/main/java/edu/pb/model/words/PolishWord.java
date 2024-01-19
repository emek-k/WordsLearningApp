package edu.pb.model.words;

import java.util.HashSet;
import java.util.Set;

public class PolishWord extends Word{
    public static Set<Character> specialCharacters = initializeSpecialCharacters();

    public PolishWord(String name, String definition, String translations){
        this.name = name;
        this.length = name.length();
        this.definition = definition;
        this.translation = translations;
    }
    private static Set<Character> initializeSpecialCharacters() {
        Set<Character> specialCharacters = new HashSet<>();

        // Adding Polish special characters to the set
        specialCharacters.add('ą');
        specialCharacters.add('ć');
        specialCharacters.add('ę');
        specialCharacters.add('ł');
        specialCharacters.add('ń');
        specialCharacters.add('ó');
        specialCharacters.add('ś');
        specialCharacters.add('ź');
        specialCharacters.add('ż');

        return specialCharacters;
    }
}
