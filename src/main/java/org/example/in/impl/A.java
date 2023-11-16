package org.example.in.impl;

import org.example.anno.ActionAnnotation;
import org.example.in.Actionnable;
import org.example.in.types.impl.Courir;

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
