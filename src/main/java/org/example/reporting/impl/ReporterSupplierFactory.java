package org.example.reporting.impl;

import org.example.reporting.Reporter;
import org.example.reporting.Supplier;
import org.example.reporting.impl.extent.ExtentReporterSupplier;
import org.example.selenium.driver.IFactory;

public class ReporterSupplierFactory implements IFactory<Supplier<? extends Reporter<?>>> {

    // We can extend this in the future if we have to supply other forms of reports
    public Supplier<? extends Reporter<?>> create() {
        return new ExtentReporterSupplier();
    }

}
