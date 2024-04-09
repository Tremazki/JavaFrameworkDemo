package org.example.reporting.impl.disabled;

import org.example.reporting.IReporter;

/**
 * This class is intentionally empty to be used as a default empty reporter with no functionality.
 * This maintains our connections to the reporting factories but allows us to disable any reporters.
 */
public class DisabledReporter implements IReporter {

    @Override
    public void startTest(String _name) {}

    @Override
    public void beginStep(String _title) {}

    @Override
    public void passStep(String _details) {}

    @Override
    public void infoStep(String _details) {}

    @Override
    public void failStep(String _details) {}

    @Override
    public void embedImage(String _path) {}

    @Override
    public void write() {}

}

