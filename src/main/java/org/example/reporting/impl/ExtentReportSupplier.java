package org.example.reporting.impl;

import org.example.reporting.Supplier;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ExtentReportSupplier implements Supplier<ExtentReporter> {

    private static ExtentReporter reporter;

    public ExtentReporter supply() {
        if(reporter == null) {
            String timeStamp  = new SimpleDateFormat("MMddyyyy-HHmmss").format(Calendar.getInstance().getTime());
            String reportName = "Report_" + timeStamp + ".html";
            reporter = new ExtentReporter(reportName);
        }
        return reporter;
    }
}
