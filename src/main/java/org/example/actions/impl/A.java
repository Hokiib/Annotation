package org.example.actions.impl;

import org.example.annotation.ActionAnnotation;
import org.example.actions.Actionnable;
import org.example.actions.types.impl.Courir;

public class A implements Actionnable {

    private final String name;

    public A(final String name) {
        this.name = name;
    }

    @ActionAnnotation
    public void aurevoir(final Courir courir){
        System.out.println(this.name + " -> aurevoir : " + courir.getAmount());
    }
}
