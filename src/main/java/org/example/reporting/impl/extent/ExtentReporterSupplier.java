package org.example.reporting.impl.extent;

import org.example.ISupplier;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ExtentReporterSupplier implements ISupplier<ExtentReporter> {

    private static ExtentReporter reporter;

    public ExtentReporter supply() {
        if(reporter == null) {
            String timeStamp      = new SimpleDateFormat("MMddyyyy-HHmmss").format(Calendar.getInstance().getTime());
            String reportName     = "Report_" + timeStamp + ".html";
            String reportLocation = "./reports";
            reporter = new ExtentReporter(reportLocation + "/" + reportName);
        }
        return reporter;
    }
}
