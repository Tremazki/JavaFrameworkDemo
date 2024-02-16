package org.example.junit.extensions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;


public class DebugExtension implements BeforeEachCallback, AfterEachCallback {

    protected Logger log;

    public DebugExtension() {
        this.log = LogManager.getLogger(getClass());
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        log.info("Starting execution of the test case: " + extensionContext.getDisplayName());
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) {
        log.info("Finishing execution of the test case: " + extensionContext.getDisplayName());
    }
}
