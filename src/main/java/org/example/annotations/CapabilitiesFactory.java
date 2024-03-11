package org.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * CapabilitiesFactory annotation is used for the automatic detection and registration of a CapabilitiesFactory
 * object within the CapabilitiesFactorySupplier at run-time.
 * <br>
 * This way, an automator can create a new subclass and register it through the use of this annotation alone.
 * The value provided will be used as the key in the registry and is the value supplied on the command line/system property
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CapabilitiesFactory {
    String value();
}
