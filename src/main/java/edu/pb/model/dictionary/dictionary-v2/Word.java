package edu.pb.model.words;

import edu.pb.model.dictionary.IDictionaryComponent;

import java.util.List;
import java.util.Collections;

public abstract class Word implements IDictionaryComponent {
    public String name;
    public String translation;
    protected int length;
    protected String definition;
    protected String difficulty;
    protected Word word;
    public String getName() {
        return name;
    }

    public String getTranslation() {
        return translation;
    }
    public String getDifficulty() {
        return difficulty;
    }

//    @Override
//    public void printDetails() {
//        System.out.println("Słowo: " + this.name + ", Definition: " + this.definition + ", translation: " + this.translation + ", difficulty: " + this.difficulty);
//    }
//
//    public void printDetails(String extra) {
//        System.out.println("parametr niedozwolony w wywołaniu printDetails dla elementu word");
//    }

    public List<Word> getDetails() {
        return Collections.singletonList(this);
    }

    public List<Word> getDetails(String extra) {
        return (List<Word>) this.word;
    }


    public String getDefinition() {
        return definition;
    }
}
