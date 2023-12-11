package com.epam.jmp.dhontar.lynda.behavioral.observer.task;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

//Observable
public class City {

    private String trafficUpdate = "";
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void updateTraffic(String trafficUpdate) {
        support.firePropertyChange("trafficUpdate", this.trafficUpdate, trafficUpdate);
        this.trafficUpdate = trafficUpdate;
    }

    public void addListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }
}
