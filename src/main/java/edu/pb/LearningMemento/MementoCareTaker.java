package edu.pb.LearningMemento;

import java.util.HashMap;
import java.util.Map;

public class MementoCareTaker {
    private Map<String, LearningMemento> mementoMap = new HashMap<>();

    public void addMemento(String sessionId, LearningMemento memento) {
        mementoMap.put(sessionId, memento);
    }

    public LearningMemento getMemento(String sessionId) {
        return mementoMap.get(sessionId);
    }
}