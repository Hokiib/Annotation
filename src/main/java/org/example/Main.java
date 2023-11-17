package org.example;

import org.example.annotation.ActionAnnotation;
import org.example.annotation.priority.Priority;
import org.example.actions.Actionnable;
import org.example.actions.impl.A;
import org.example.actions.impl.B;
import org.example.actions.types.Action;
import org.example.actions.types.impl.Courir;
import org.example.actions.types.impl.Manger;
import org.example.util.ActionnableTuple;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Main {

    public static Map<Priority, List<ActionnableTuple>> METHODS = new HashMap<>();

    public static void main(String[] args) {

        final A a = new A("first");
        final B b = new B();

        register(a);
        register(b);

        for (int i = 0; i < 10; i++) {
            register(new A(UUID.randomUUID().toString()));
        }

        try {
            execute(new Courir(5));
            execute(new Manger("paela"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void register(Actionnable actionnable) {
        for (final Method method : actionnable.getClass().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(ActionAnnotation.class)) continue;
            if (method.getParameterCount() != 1) continue;
            if (!method.getParameterTypes()[0].getSuperclass().isAssignableFrom(Action.class)) continue;

            final List<ActionnableTuple> prioritiesMethods = METHODS
                    .computeIfAbsent(method.getAnnotation(ActionAnnotation.class)
                            .priority(), priority -> new ArrayList<>());


            prioritiesMethods.add(new ActionnableTuple(actionnable, method));
        }
    }

    public static void execute(final Action action) throws InvocationTargetException, IllegalAccessException {

        for (final Priority priority : Priority.values()) {
            final List<ActionnableTuple> tuples = METHODS.get(priority);
            if (tuples == null) continue;

            for (final ActionnableTuple tuple : tuples) {
                if (action.isCancelled()) return;

                final Method method = tuple.method();

                final Class<?> type = method.getParameterTypes()[0];
                if (!type.equals(action.getClass())) continue;

                //Execute the method
                method.setAccessible(true);
                method.invoke(tuple.actionnable(), action);
            }
        }
    }
}