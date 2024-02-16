package org.example.junit;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

public class ExtentReportTest {

    protected ExtentReports       extent;
    protected ExtentSparkReporter sparkReporter;

    public ExtentReportTest() {
        extent          = new ExtentReports();
        sparkReporter   = new ExtentSparkReporter("TestReport.html");
        extent.attachReporter(sparkReporter);
    }

    @BeforeEach
    void setupTestReport(TestInfo testInfo) {
        extent.createTest(testInfo.getDisplayName());
    }

    @AfterEach
    void teardownTestReport() {
       extent.flush();
    }
}
