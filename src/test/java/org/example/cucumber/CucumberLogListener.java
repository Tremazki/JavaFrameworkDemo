package org.example.cucumber;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.reporting.IReporter;
import org.example.reporting.impl.ReporterSupplierFactory;

import java.util.Optional;

public class CucumberLogListener implements EventListener {

    private final Logger log;

    public CucumberLogListener() {
        this.log = LogManager.getLogger(getClass());
    }

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestCaseStarted.class,  this::handleTestCaseStarted);
        eventPublisher.registerHandlerFor(TestCaseFinished.class, this::handleTestCaseFinished);
        eventPublisher.registerHandlerFor(TestStepStarted.class,  this::handleTestStepStarted);
        eventPublisher.registerHandlerFor(TestStepFinished.class, this::handleTestStepFinished);
    }

    private void handleTestCaseStarted(TestCaseStarted event) {
        log.info("--------------------------------------------------------------------------");
        log.info(String.format("Starting execution of the test case: [%s]", event.getTestCase().getName()));
    }

    private void handleTestCaseFinished(TestCaseFinished event) {
        if(event.getResult().getStatus().isOk()) {
            log.info("Successfully executed the test case.");
        } else {
            log.error("Execution of the test case completed with failures - see the given exception details below",
                      event.getResult().getError());
        }
    }

    private void handleTestStepStarted(TestStepStarted event) {
        log.info("Executing the test step: " + event.getTestStep().getCodeLocation());
    }

    private void handleTestStepFinished(TestStepFinished event) {
        if(event.getResult().getStatus().isOk()) {
            log.info("Successfully executed the test step");
        } else {
            log.error("An exception has occurred during the execution of this test step - See details below");
        }
        log.info("");
    }
}
