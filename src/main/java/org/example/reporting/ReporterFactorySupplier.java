package org.example.reporting;

import dorkbox.annotation.AnnotationDefaults;
import dorkbox.annotation.AnnotationDetector;
import org.example.IFactory;
import org.example.ISupplier;
import org.example.annotations.ReporterFactory;

import java.lang.annotation.ElementType;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * In the event that we have to utilize a different reporting engine for the client, we can
 * extend this class to return a particular supplier determined by the Class supplied to the factory method.
 * <br>
 * By default, the ExtentReportSupplier is returned, but you can disable this by passing in the -Dreporter=disabled system property.
 */
public class ReporterFactorySupplier implements ISupplier<IFactory<? extends IReporter, ?>> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private static Map<String, IFactory<? extends IReporter, String>> registry;

    public ReporterFactorySupplier() {
        this("org.example");
    }

    public ReporterFactorySupplier(String classPath) {
        if(registry == null || registry.isEmpty()) {
            registry = new ConcurrentHashMap<>();
            detectReporterFactories(classPath);
        }
    }

    public IFactory<? extends IReporter, ?> supply() {
        String property = System.getProperty("reporter", "extent");
        logger.debug(String.format(
                "The default reporter supplier is extent report. " +
                "To specify an alternative, use the 'reporter' system property when executing the framework. \n " +
                "Valid values include: {'extent', 'disabled'} \n " +
                "Found the value: [%s]", property));
        return registry.get(property.toUpperCase());
    }

    public IFactory<? extends IReporter, ?> supply(String _name) {
        if(!registry.containsKey(_name)) {
            logger.error(String.format(
                    "Failed to locate an appropriate reporter supplier using the following key: [%s]. \n" +
                    "The default supplier will be returned to allow execution to continue.", _name)
            );
            return supply();
        }
        return registry.get(_name);
    }

    @SuppressWarnings("unchecked")
    private void detectReporterFactories(String classPath) {
        try {
            List<Class<?>> classModules = AnnotationDetector
                    .scanClassPath(classPath)
                    .forAnnotations(ReporterFactory.class)
                    .on(ElementType.TYPE)
                    .collect(AnnotationDefaults.getType);

            for (Class<?> clazz : classModules) {
                logger.debug(String.format("Creating an instance of the class [%s] and registering it to the reporter factory..", clazz.getSimpleName()));
                registry.put(
                        clazz.getDeclaredAnnotation(ReporterFactory.class).value().toUpperCase(),
                        (IFactory<? extends IReporter, String>) clazz.getConstructor().newInstance()
                );
            }
        } catch (Exception e) {
            logger.error("An error occurred while finding classes annotation with the ReporterFactory annotation", e);
        }
    }
}
