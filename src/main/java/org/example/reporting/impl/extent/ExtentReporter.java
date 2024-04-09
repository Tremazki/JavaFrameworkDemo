package org.example.reporting.impl.extent;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.reporting.IReporter;

public class ExtentReporter implements IReporter {

    private final ExtentReports extent;

    private final ThreadLocal<ExtentTest> extentTest;
    private final ThreadLocal<ExtentTest> node;

    public ExtentReporter(String _reportName) {
        extent     = new ExtentReports();
        extentTest = new ThreadLocal<>();
        node       = new ThreadLocal<>();
        extent.attachReporter(new ExtentSparkReporter(_reportName));
    }

    public void startTest(String _name) {
        extentTest.set(extent.createTest(_name));
    }

    public void beginStep(String _step) {
        node.set(extentTest.get().createNode(_step));
    }

    public void passStep(String _details) {
        node.get().pass(_details);
    }

    public void infoStep(String _details) {
        node.get().info(_details);
    }

    public void failStep(String _details) {
        node.get().fail(_details);
    }

    public void embedImage(String _base64) {
        node.get().addScreenCaptureFromBase64String(_base64);
    }

    public void write() {
        extent.flush();
    }
}
