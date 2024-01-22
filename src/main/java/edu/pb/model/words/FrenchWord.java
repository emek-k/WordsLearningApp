package edu.pb.model.words;

import java.util.HashSet;
import java.util.Set;

public class FrenchWord extends Word{

    public static Set<Character> specialCharacters = initializeSpecialCharacters();

    public FrenchWord(String name, String definition, String translations){
        this.name = name;
        this.length = name.length();
        this.definition = definition;
        this.translation = translations;
    }
    private static Set<Character> initializeSpecialCharacters() {
        Set<Character> specialCharacters = new HashSet<>();

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
