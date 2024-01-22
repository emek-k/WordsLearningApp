package edu.pb.model.factories;

import edu.pb.model.words.FrenchWord;
import edu.pb.model.words.PolishWord;
import edu.pb.model.words.Word;

import java.util.Set;

public class FrenchWordFactory extends WordsFactory{
    public Word createWord(String name, String definition, String translations) {
        return new FrenchWord(name, definition, translations);
    }

    public String setWordDifficulty(String name) {
        if(containsSpecialCharacter(name, shareSpecial())) {
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

    public Set<Character> shareSpecial() {
        return FrenchWord.specialCharacters;
    }
}
