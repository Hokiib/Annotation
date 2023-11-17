package org.example.util;

import org.example.actions.Actionnable;

import java.lang.reflect.Method;

public record ActionnableTuple(Actionnable actionnable, Method method) {
}
