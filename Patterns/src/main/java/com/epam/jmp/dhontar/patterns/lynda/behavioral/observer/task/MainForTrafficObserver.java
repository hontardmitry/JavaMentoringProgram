package com.epam.jmp.dhontar.patterns.lynda.behavioral.observer.task;

public class MainForTrafficObserver {

    public static void main(String[] args) {
        City sunnyville = new City();
        City springfield = new City();
        TrafficUpdates trafficUpdates = new TrafficUpdates();

        sunnyville.addListener(trafficUpdates);
        springfield.addListener(trafficUpdates);
        sunnyville.updateTraffic("Congestion in town center");
        springfield.updateTraffic("Accident on the highway");

        trafficUpdates.getUpdates();
    }
}
