package utils;

import java.util.ArrayList;

public class Observable {
    
    public interface Observer {
        void update(String msg);
    }

    private final ArrayList<Observer> observers = new ArrayList<>();

    public final void addObserver(Observer observer) {
        observers.add(observer);
    }

    public final void notifyObservers(String msg) {
        observers.forEach(observers -> observers.update(msg));
    }
}
