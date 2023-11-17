package fr.hokib.annotation;

import fr.hokib.annotation.priority.Priority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionAnnotation {

    Priority priority() default Priority.NEUTRAL;
}
