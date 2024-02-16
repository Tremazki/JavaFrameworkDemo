package org.example.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

//TODO Move this away from being static and into a manager class
public class ExtentReportUtilities {

    protected static ExtentReports              extent         = new ExtentReports();
    protected static ExtentSparkReporter        sparkReporter  = new ExtentSparkReporter("TestReport.html");
    protected static ThreadLocal<ExtentTest>    extentTest     = new ThreadLocal<>();

    public static void initialize() {
        extent.attachReporter(sparkReporter);
    }

    public static void createExtentTest(String _name) {
        extentTest.set(extent.createTest(_name));
    }

    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }

    public static void flush() {
        extent.flush();
    }
}
