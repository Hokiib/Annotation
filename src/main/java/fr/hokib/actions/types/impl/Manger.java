package fr.hokib.actions.types.impl;

import fr.hokib.actions.types.Action;

public class Manger extends Action {

    private final String food;

    public Manger(final String food){
        this.food = food;
    }

    public String getFood() {
        return food;
    }

}
