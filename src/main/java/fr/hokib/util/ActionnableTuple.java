package fr.hokib.util;

import fr.hokib.actions.Actionnable;

import java.lang.reflect.Method;

public record ActionnableTuple(Actionnable actionnable, Method method) {
}
