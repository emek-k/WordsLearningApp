package edu.pb.model.words;

import java.util.HashSet;
import java.util.Set;

public class PolishWord extends Word{
    public static Set<Character> specialCharacters = initializeSpecialCharacters();
    //dwuznaki
    public static Set<String> diagraphs = initializeDiagraphs();
    public static Set<Character> vowels = initializeVowels();

    public PolishWord(String name, String definition, String translations){
        this.name = name;
        this.length = name.length();
        this.definition = definition;
        this.translation = translations;
    }
    private static Set<Character> initializeSpecialCharacters() {
        Set<Character> specialCharacters = new HashSet<>();

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

    private static Set<String> initializeDiagraphs() {
        Set<String> diagraph = new HashSet<>();

        diagraph.add("rz");
        diagraph.add("sz");
        diagraph.add("cz");
        diagraph.add("dz");
        diagraph.add("dź");
        diagraph.add("dż");
        diagraph.add("ch");

        return diagraph;
    }

    private static Set<Character> initializeVowels() {
        Set<Character> vowels = new HashSet<>();

        vowels.add('a');
        vowels.add('e');
        vowels.add('o');
        vowels.add('u');
        vowels.add('y');
        vowels.add('i');

        return vowels;
    }
}
