package edu.pb.model.dictionary;

import java.util.ArrayList;
import java.util.List;

public class Language implements IDictionaryComponent {
    private String name;
    private List<IDictionaryComponent> levels = new ArrayList<>();

    public Language(String name) {
        this.name = name;
    }

    public void addLevel(IDictionaryComponent level) {
        levels.add(level);
    }

    @Override
    public void printDetails() {
        System.out.println("Język: " + name);
        for (IDictionaryComponent level : levels) {
            level.printDetails();
        }
    }
}