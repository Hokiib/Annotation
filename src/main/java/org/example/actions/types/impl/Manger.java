package org.example.actions.types.impl;

import org.example.actions.types.Action;

public class Manger extends Action {

    private final String food;

    public Manger(final String food){
        this.food = food;
    }

    public String getFood() {
        return food;
    }

}
