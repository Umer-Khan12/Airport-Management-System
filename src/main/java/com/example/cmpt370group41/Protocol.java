package com.example.cmpt370group41;

import java.util.ArrayList;

public class Protocol {
    public int id;
    // not sure what type actions are of if they are supposed to be another class
    public String currentAction;
    public String nextAction;
    public ArrayList<String> actionQueue;

    public Protocol(int id) {
        this.id = id;
        this.actionQueue = new ArrayList<>();
    }

    public static void main(String[] args) {}
}
