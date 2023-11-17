package org.example.actions.impl;

import org.example.annotation.ActionAnnotation;
import org.example.annotation.priority.Priority;
import org.example.actions.Actionnable;
import org.example.actions.types.impl.Courir;
import org.example.actions.types.impl.Manger;

import java.util.SplittableRandom;

public class B implements Actionnable {

    private final SplittableRandom splittableRandom = new SplittableRandom();

    @ActionAnnotation
    public void salut(final Manger manger){
        System.out.println("C -> salut : " + manger.getFood());
    }

    @ActionAnnotation(priority = Priority.HIGH)
    private void courir(final Courir courir){
        System.out.println("C -> courir : " + courir.getAmount());

        // 1/2
        if(this.splittableRandom.nextInt(0, 2) == 0){
            System.out.println("cancelled courir");
            courir.setCancelled();
        }
    }
}
