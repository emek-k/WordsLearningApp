package edu.pb.model.words;

import java.util.HashSet;
import java.util.Set;

public class FrenchWord extends Word{

    public Set<Character> specialCharacters = initializeSpecialCharacters();
    private static Set<Character> initializeSpecialCharacters() {
        Set<Character> specialCharacters = new HashSet<>();

        // Adding French special characters to the set
        specialCharacters.add('à');
        specialCharacters.add('â');
        specialCharacters.add('ç');
        specialCharacters.add('é');
        specialCharacters.add('è');
        specialCharacters.add('ê');
        specialCharacters.add('ë');
        specialCharacters.add('î');
        specialCharacters.add('ï');
        specialCharacters.add('ô');
        specialCharacters.add('û');
        specialCharacters.add('ù');
        specialCharacters.add('ÿ');

        return specialCharacters;
    }
}
