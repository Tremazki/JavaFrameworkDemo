package org.example.junit;

import org.example.reporting.ExtentReportUtilities;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

public class ExtentReportTest {

    ExtentReportTest() {
        ExtentReportUtilities.initialize();
    }

    @BeforeEach
    void setupTestReport(TestInfo _testInfo) {
        ExtentReportUtilities.createExtentTest(_testInfo.getDisplayName());
    }

    @AfterEach
    void teardownTestReport() {
        ExtentReportUtilities.flush();
    }
}
