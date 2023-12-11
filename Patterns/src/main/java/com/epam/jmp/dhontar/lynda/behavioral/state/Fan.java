package com.epam.jmp.dhontar.lynda.behavioral.state;

public class Fan {

    private FanState state = new FanStateLow();

    public Fan(FanState state) {
        this.state = state;
    }

    public FanState getState() {
        return state;
    }

    public void setState(FanState state) {
        this.state = state;
    }

    public void turnUp(){
//        switch(state) {
//            case "low":
//                setState("medium");
//                System.out.println("Fan is on medium");
//                break;
//            case "medium":
//                setState("high");
//                System.out.println("Fan is now on high");
//                break;
//            case "high":
//                break;
//        }
        state.turnUp(this);
    }

    public void turnDown(){
//        switch(state) {
//            case "low":
//                break;
//            case "medium":
//                setState("low");
//                System.out.println("Fan is now on low");
//                break;
//            case "high":
//                setState("medium");
//                System.out.println("Fan is now on medium");
//                break;
//        }
        state.turnDown(this);
    }
}
