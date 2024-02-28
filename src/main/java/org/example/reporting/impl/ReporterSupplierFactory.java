package org.example.reporting.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.reporting.IReporter;
import org.example.ISupplier;
import org.example.reporting.impl.disabled.DisabledReporter;
import org.example.reporting.impl.disabled.DisabledReporterSupplier;
import org.example.reporting.impl.extent.ExtentReporter;
import org.example.reporting.impl.extent.ExtentReporterSupplier;
import org.example.IFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * In the event that we have to utilize a different reporting engine for the client, we can
 * extend this class to return a particular supplier determined by the Class supplied to the factory method.
 * <br>
 * By default, the ExtentReportSupplier is returned but you can disable this by passing in the -Dreporter=disabled system property.
 */
public class ReporterSupplierFactory implements IFactory<ISupplier<? extends IReporter>, Class<?>> {

    private final Map<Class<?>, ISupplier<? extends IReporter>> registry = new HashMap<>();
    private final Logger                                        logger   = LogManager.getLogger();

    public ReporterSupplierFactory(){
        registry.put(DisabledReporter.class, new DisabledReporterSupplier());
        registry.put(ExtentReporter.class,   new ExtentReporterSupplier());
    }

    public ISupplier<? extends IReporter> create() {
        String property = System.getProperty("reporter", "extent");
        logger.info(String.format(
                "The default reporter supplier is extent report. \n " +
                "To specify an alternative, use the 'reporter' system property when executing the framework. \n " +
                "Valid values include: {'extent', 'disabled'} \n " +
                "Found the value: [%s]", property));
        return registry.get(convertPropertyNamesToClass(property));
    }

    @Override
    public ISupplier<? extends IReporter> create(Class<?> _clazz) {
        if(!registry.containsKey(_clazz)) {
            logger.error(String.format(
                    "Failed to locate an appropriate reporter supplier using the following key: [%s]. \n" +
                    "The default supplier will be returned to allow execution to continue.", _clazz.getSimpleName())
            );
            return create();
        }
        return registry.get(_clazz);
    }

    private Class<?> convertPropertyNamesToClass(String _property) {
        switch(_property) {
            case "disabled":
                return DisabledReporter.class;
            default:
            case "extent":
                return ExtentReporter.class;
        }
    }
}
