package me.idarkyy.common.annotation;

/**
 * This annotation is used for methods
 * that are not finished / stable yet
 * <p>
 * Work in progress
 */
public @interface WIP {
    String value() default "";
}
