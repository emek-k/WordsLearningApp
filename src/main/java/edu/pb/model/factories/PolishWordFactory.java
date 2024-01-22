package edu.pb.model.factories;

import edu.pb.model.words.PolishWord;
import edu.pb.model.words.PolishWord;
import edu.pb.model.words.Word;

import java.util.Set;

public class PolishWordFactory extends WordsFactory{

    public Word createWord(String name, String definition, String translations) {
        return new PolishWord(name, definition, translations);
    }

    public String setWordDifficulty(String name) {
        if(containsSpecialCharacter(name, shareSpecial()) || containsAnyDiagraph(name, PolishWord.diagraphs)) {
            if (name.length()>6){
                return "hard";
            }
            else {
                return "medium";
            }
        }
        if (name.length()>6){
            if (name.length()>10) return "hard";
            return "medium";
        }
        return "easy";
    }

    private static boolean containsSpecialCharacter(String str, Set<Character> specialCharacters) {
        for (char ch : str.toCharArray()) {
            if (specialCharacters.contains(ch)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsAnyDiagraph(String str, Set<String> diagraphs) {
        for (String diagraph : diagraphs) {
            if (str.contains(diagraph)) {
                return true;
            }
        }
        return false;
    }

    public Set<Character> shareSpecial() {
        return PolishWord.specialCharacters;
    }
}
