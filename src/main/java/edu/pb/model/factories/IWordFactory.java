package edu.pb.model.factories;

import edu.pb.model.words.Word;

public interface IWordFactory {
    enum difficulty{easy, medium, hard};
    String setWordDifficulty(String name);
    Word createWord(String name, String definition, String translations);
//    void deleteWord();
//    Word getWord();
}
