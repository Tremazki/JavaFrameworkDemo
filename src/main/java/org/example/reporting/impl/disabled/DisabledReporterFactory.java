package org.example.reporting.impl.disabled;

import org.example.IFactory;
import org.example.annotations.ReporterFactory;

@ReporterFactory("Disabled")
public class DisabledReporterFactory implements IFactory<DisabledReporter, String> {

    private static DisabledReporter reporter;

    @Override
    public DisabledReporter create() {
        return create("");
    }

    @Override
    public DisabledReporter create(String _condition) {
        if(reporter == null) {
            reporter = new DisabledReporter();
        }
        return reporter;
    }
}
