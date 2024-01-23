package edu.pb.model.dictionary;

import edu.pb.model.words.Word;

import java.util.List;

public interface IDictionaryComponent {
    List<Word> getDetails();
    List<Word> getDetails(String extra);
}
