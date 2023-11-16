package org.example;

import org.example.anno.ActionAnnotation;
import org.example.anno.priority.Priority;
import org.example.in.Actionnable;
import org.example.in.impl.A;
import org.example.in.impl.B;
import org.example.in.types.Action;
import org.example.in.types.impl.Courir;
import org.example.in.types.impl.Manger;
import org.example.util.ActionnableTuple;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static Map<Priority, List<ActionnableTuple>> METHODS = new HashMap<>();

    public static void main(String[] args) {

        final A a = new A("first");
        final A a2 = new A("second");
        final B b = new B();

        register(a);
        register(a2);
        register(b);

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

                final Method method = tuple.method();

                final Class<?> type = method.getParameterTypes()[0];
                if (!type.equals(action.getClass())) continue;

                if (action.isCancelled()) return;

                //Execute the method
                method.setAccessible(true);
                method.invoke(tuple.actionnable(), action);
            }
        }
    }
}