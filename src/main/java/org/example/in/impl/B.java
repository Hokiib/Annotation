package org.example.in.impl;

import org.example.anno.ActionAnnotation;
import org.example.anno.priority.Priority;
import org.example.in.Actionnable;
import org.example.in.types.impl.Courir;
import org.example.in.types.impl.Manger;

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
        if(splittableRandom.nextInt(0, 2) == 0){
            System.out.println("cancelled courir");
            courir.setCancelled();
        }
    }
}
