package org.example.reporting.impl;

import org.example.reporting.Reporter;
import org.example.reporting.Supplier;

public class ReporterSupplierFactory {

    // We can extend this in the future if we have to supply other forms of reports
    public static Supplier<? extends Reporter<?>> getSupplier() {
        return new ExtentReportSupplier();
    }

}
