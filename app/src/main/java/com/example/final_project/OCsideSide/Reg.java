package com.example.final_project.OCsideSide;

public class Reg {
    String user;
    String event;
    String SubEvent;

    public Reg() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getSubEvent() {
        return SubEvent;
    }

    public void setSubEvent(String subEvent) {
        SubEvent = subEvent;
    }

    public Reg(String user, String event, String subEvent) {
        this.user = user;
        this.event = event;
        SubEvent = subEvent;
    }



}
