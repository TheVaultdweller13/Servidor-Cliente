/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Observer;

/**
 *
 * @author dam213
 */
import java.util.ArrayList;

public abstract class Subject {

    private ArrayList<Observer> registeredObservers;

    public Subject() {
        this.registeredObservers = new ArrayList<Observer>();
    }

    public void registerObserver(Observer observer) {
        this.registeredObservers.add(observer);
    }

    public void unregisterObserver(Observer observer) {
        this.registeredObservers.remove(observer);
    }

    protected void notifyObservers() {
        for (Observer observer : registeredObservers) {
            observer.update(this);
        }
    }
}
