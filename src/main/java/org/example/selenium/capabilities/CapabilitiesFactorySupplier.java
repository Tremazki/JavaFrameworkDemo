package org.example.selenium.capabilities;

import dorkbox.annotation.AnnotationDefaults;
import dorkbox.annotation.AnnotationDetector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.IFactory;
import org.example.ISupplier;
import org.example.annotations.CapabilitiesFactory;
import org.example.reporting.IReporter;
import org.example.selenium.capabilities.impl.DefaultCapabilitiesFactory;
import org.openqa.selenium.Capabilities;

import java.lang.annotation.ElementType;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * CapabilitiesFactorySupplier is responsible for storing all possible CapabilitiesFactory objects
 * and returning them based on the specified system property, 'capabilities' allowing for easy configuring
 * through the command line.
 */
public final class CapabilitiesFactorySupplier implements ISupplier<IFactory<Capabilities, ?>> {

    private static final Logger logger = LogManager.getLogger();

    private static Map<String, IFactory<Capabilities, ?>> registry;

    public CapabilitiesFactorySupplier() {
        this("org.example");
    }

    public CapabilitiesFactorySupplier(String classPath) {
        if(registry == null || registry.isEmpty()) {
            registry = new ConcurrentHashMap<>();
            detectCapabilitiesFactories(classPath);
        }
    }

    public IFactory<Capabilities, ?> supply() {
        String property = System.getProperty("capabilities", "default");
        logger.debug(String.format(
                "The default capabilities supplier is an empty options provider. " +
                "To specify an alternative, use the 'capabilities' system property when executing the framework. \n " +
                "Valid values include: {'default', 'xml'} \n " +
                "Found the value: [%s]", property));
        return registry.get(property.toUpperCase());
    }

    @SuppressWarnings("unchecked")
    private void detectCapabilitiesFactories(String classPath) {
        try {
            List<Class<?>> classModules = AnnotationDetector
                    .scanClassPath(classPath)
                    .forAnnotations(CapabilitiesFactory.class)
                    .on(ElementType.TYPE)
                    .collect(AnnotationDefaults.getType);

            for (Class<?> clazz : classModules) {
                logger.debug(String.format("Creating an instance of the class [%s] and registering it to the capabilities factory..", clazz.getSimpleName()));
                registry.put(
                        clazz.getDeclaredAnnotation(CapabilitiesFactory.class).value().toUpperCase(),
                        (DefaultCapabilitiesFactory) clazz.getConstructor().newInstance()
                );
            }
        } catch (Exception e) {
            logger.error("An error occurred while finding classes annotation with the ReporterFactory annotation", e);
        }
    }
}
