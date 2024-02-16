package org.example.reporting.impl;

import org.example.reporting.Reporter;
import org.example.reporting.Supplier;
import org.example.reporting.impl.extent.ExtentReporter;
import org.example.reporting.impl.extent.ExtentReporterSupplier;
import org.example.selenium.driver.IFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * In the event that we have to utilize a different reporting engine for the client, we can
 * extend this class to return a particular supplier determined by the Class supplied to the factory method.
 * <br>
 * By default, the ExtentReportSupplier is returned.
 */
public class ReporterSupplierFactory implements IFactory<Supplier<? extends Reporter<?>>, Class<?>> {

    private final Map<Class<?>, Supplier<? extends Reporter<?>>> registry = new HashMap<>();

    public ReporterSupplierFactory(){
        registry.put(ExtentReporter.class, new ExtentReporterSupplier());
    }

    public Supplier<? extends Reporter<?>> create() {
        return registry.get(ExtentReporter.class);
    }

    @Override
    public Supplier<? extends Reporter<?>> create(Class<?> _condition) {
        return registry.get(_condition);
    }

}
