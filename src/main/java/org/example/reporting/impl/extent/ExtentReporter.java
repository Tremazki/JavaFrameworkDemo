package org.example.reporting.impl.extent;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.reporting.Reporter;

public class ExtentReporter implements Reporter<ExtentTest> {

    private final ExtentReports extent;

    private ExtentTest extentTest;

    public ExtentReporter(String _reportName) {
        extent = new ExtentReports();
        extent.attachReporter(new ExtentSparkReporter(_reportName));
    }

    public void startTest(String _name) {
        extentTest = extent.createTest(_name);
    }

    public void passStep(String _step, String _details) {
        extentTest.createNode(_step).pass(_details);
    }

    public void failStep(String _step, String _details) {
        extentTest.createNode(_step).fail(_details);
    }

    public void embedImage(String _path) {
        extentTest.addScreenCaptureFromPath(_path);
    }

    public void write() {
        extent.flush();
    }
}
