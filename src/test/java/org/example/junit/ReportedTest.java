package org.example.junit;

import org.example.reporting.IReporter;
import org.example.reporting.impl.ReporterFactorySupplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

public class ReportedTest {

    private final IReporter reporter;

    ReportedTest() {
        reporter = new ReporterFactorySupplier().supply().create();
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
