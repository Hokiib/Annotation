package org.example.in.types.impl;

import org.example.in.types.Action;

public class Courir extends Action {

    private final int amount;

    public Courir(final int amount){
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
