package org.example.util;

import org.example.in.Actionnable;

import java.lang.reflect.Method;

public record ActionnableTuple(Actionnable actionnable, Method method) {
}
