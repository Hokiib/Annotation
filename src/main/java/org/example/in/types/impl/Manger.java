package org.example.in.types.impl;

import org.example.in.types.Action;

public class Manger extends Action {

    private final String food;

    public Manger(final String food){
        this.food = food;
    }

    public String getFood() {
        return food;
    }

}
