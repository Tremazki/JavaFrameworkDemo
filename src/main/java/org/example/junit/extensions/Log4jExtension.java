package org.example.junit.extensions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Optional;


public class Log4jExtension implements BeforeEachCallback, BeforeAllCallback, AfterEachCallback {

    protected Logger log;

    public Log4jExtension() {
        this.log = LogManager.getLogger(getClass());
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        log.info("**************************************************************************");
        log.info("Starting execution of the test suite: " + extensionContext.getDisplayName());
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        log.info("--------------------------------------------------------------------------");
        log.info("Starting execution of the test case: " + extensionContext.getDisplayName());
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) {
        Optional<Throwable> exception = extensionContext.getExecutionException();
        if(exception.isEmpty()) {
            log.info("Successfully executed the test case: " + extensionContext.getDisplayName());
        } else {
            log.error("Execution of the test case completed with failures - see the given exception: " + exception.get());
        }
    }
}
