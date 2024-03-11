package org.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestStep {
    String  value();
    boolean reportArguments() default false;
    boolean screenshotPass()  default false;
    boolean screenshotFail()  default true;
}
