package edu.pb.model.words;

import java.util.HashSet;
import java.util.Set;

public class GermanWord extends Word{
    public Set<Character> specialCharacters = initializeSpecialCharacters();
    private static Set<Character> initializeSpecialCharacters() {
        Set<Character> specialCharacters = new HashSet<>();

        // Adding German special characters to the set
        specialCharacters.add('ä');
        specialCharacters.add('ö');
        specialCharacters.add('ü');
        specialCharacters.add('ß');

        return specialCharacters;
    }
}
