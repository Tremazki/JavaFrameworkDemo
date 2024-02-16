package org.example.junit;

import org.example.reporting.impl.ExtentReportSupplier;
import org.example.reporting.impl.ExtentReporter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

public class ExtentReportTest {

    private final ExtentReporter reporter;

    ExtentReportTest() {
        reporter = new ExtentReportSupplier().supply();
    }

    @BeforeEach
    void setupTestReport(TestInfo _testInfo) {
        reporter.startTest(_testInfo.getDisplayName());
    }

    @AfterEach
    void teardownTestReport() {
        reporter.write();
    }
}
