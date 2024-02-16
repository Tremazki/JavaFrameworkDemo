package org.example.junit;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

public class ExtentReportTest {

    protected static ExtentReports       extent         = new ExtentReports();
    protected static ExtentSparkReporter sparkReporter  = new ExtentSparkReporter("TestReport.html");

    private ExtentTest currentTest;

    ExtentReportTest() {
        extent.attachReporter(sparkReporter);
    }

    public ExtentTest reportStep() {
        return currentTest;
    }

    @BeforeEach
    void setupTestReport(TestInfo _testInfo) {
        currentTest = extent.createTest(_testInfo.getDisplayName());
    }

    @AfterEach
    void teardownTestReport() {
       extent.flush();
    }
}
