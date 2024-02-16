package org.example.junit;

import org.example.reporting.impl.extent.ExtentReporterSupplier;
import org.example.reporting.impl.extent.ExtentReporter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

public class ExtentReportTest {

    private final ExtentReporter reporter;

    ExtentReportTest() {
        reporter = new ExtentReporterSupplier().supply();
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
