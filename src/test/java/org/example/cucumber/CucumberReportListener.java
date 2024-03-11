package org.example.cucumber;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.*;
import org.example.reporting.IReporter;
import org.example.reporting.ReporterFactorySupplier;

public class CucumberReportListener implements EventListener {

    private final IReporter reporter;

    public CucumberReportListener() {
        reporter = new ReporterFactorySupplier().supply().create();
    }

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestCaseStarted.class,  this::handleTestCaseStarted);
        eventPublisher.registerHandlerFor(TestCaseFinished.class, this::handleTestCaseFinished);
        eventPublisher.registerHandlerFor(TestStepStarted.class,  this::handleTestStepStarted);
        eventPublisher.registerHandlerFor(TestStepFinished.class, this::handleTestStepFinished);
    }

    private void handleTestCaseStarted(TestCaseStarted event) {
        reporter.startTest(event.getTestCase().getName());
    }

    private void handleTestCaseFinished(TestCaseFinished event) {
        reporter.write();
    }

    private void handleTestStepStarted(TestStepStarted event) {
        reporter.beginStep(event.getTestStep().getCodeLocation());
    }

    private void handleTestStepFinished(TestStepFinished event) {
        if(event.getResult().getStatus().isOk()) {
            reporter.passStep("Test step passed successfully");
        } else {
            reporter.failStep("Test step has failed with the following exception:<br><br>" + event.getResult().getError().getMessage());
        }
    }
}
