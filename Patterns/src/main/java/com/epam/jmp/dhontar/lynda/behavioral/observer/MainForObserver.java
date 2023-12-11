package com.epam.jmp.dhontar.lynda.behavioral.observer;

public class MainForObserver {

    public static void main(String[] args) {
        var sally = new Connection();
        var bob = new Connection();
        SocialMediaFeed feed = new SocialMediaFeed();

        sally.addPropertyChangeListener(feed);
        bob.addPropertyChangeListener(feed);

        sally.setStatus("going for a walk");
        bob.setStatus("eating my lunch");

        feed.printStatuses();
    }
}
