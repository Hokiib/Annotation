package fr.hokib.actions.impl;

import fr.hokib.annotation.ActionAnnotation;
import fr.hokib.actions.Actionnable;
import fr.hokib.actions.types.impl.Courir;

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
