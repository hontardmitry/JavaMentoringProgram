package com.epam.jmp.dhontar.patterns.lynda.creational.singleton;

public class PrintsPooler {


//to prevent creating several instances the field was made final
//due to lazy initialization in java it is ok to create an object right in the class because
//PrintsPooler will not be initialized until the getInstance method is called
    private static final PrintsPooler SPOOLER = new PrintsPooler();
    private static boolean initialized = false;
    private PrintsPooler(){}

    private void init(){
        //Code for initialization
    }

//    in order to avoid init() method being called concurrently from different threads
//    it is better to make get instance synchronized
    public static synchronized PrintsPooler getInstance() {
        if (initialized) return SPOOLER;
        SPOOLER.init();
        initialized = true;
        System.out.println("initialized in thread: " + Thread.currentThread().getName());
        return SPOOLER;
    }

}
