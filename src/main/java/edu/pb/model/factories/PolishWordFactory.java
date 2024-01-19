package edu.pb.model.factories;

import edu.pb.model.words.PolishWord;
import edu.pb.model.words.Word;

import java.util.Set;

public class PolishWordFactory implements IWordFactory{

    @Override
    public String setWordDifficulty(String name) {
        return "";
    }

    @Override
    public Word createWord(String name, String definition, String translations) {
        return new PolishWord(name, definition, translations);
    }

//    @Override
//    public void deleteWord() {
//
//    }
//
//    @Override
//    public Word getWord() {
//        return null;
//    }

    public Set<Character> shareAlphabet() {
        return PolishWord.specialCharacters;
    }
}
