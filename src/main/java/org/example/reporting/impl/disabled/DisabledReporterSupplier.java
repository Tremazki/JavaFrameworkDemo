package org.example.reporting.impl.disabled;

import org.example.ISupplier;

public class DisabledReporterSupplier implements ISupplier<DisabledReporter> {

    private static DisabledReporter reporter;

    public DisabledReporter supply() {
        if(reporter == null) {
            reporter = new DisabledReporter();
        }
        return reporter;
    }
}
