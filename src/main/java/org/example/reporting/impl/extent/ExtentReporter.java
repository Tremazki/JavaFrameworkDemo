package org.example.reporting.impl.extent;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.reporting.IReporter;

public class ExtentReporter implements IReporter {

    private final ExtentReports extent;

    private ExtentTest extentTest;
    private ExtentTest node;

    public ExtentReporter(String _reportName) {
        extent = new ExtentReports();
        extent.attachReporter(new ExtentSparkReporter(_reportName));
    }

    public void startTest(String _name) {
        extentTest = extent.createTest(_name);
    }

    public void beginStep(String _step) {
        node = extentTest.createNode(_step);
    }

    public void passStep(String _details) {
        node.pass(_details);
    }

    public void failStep(String _details) {
        node.fail(_details);
    }

    public void embedImage(String _base64) {
        node.addScreenCaptureFromBase64String(_base64);
    }

    public void write() {
        extent.flush();
    }
}
