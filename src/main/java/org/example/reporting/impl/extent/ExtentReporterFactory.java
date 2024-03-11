package org.example.reporting.impl.extent;

import org.example.IFactory;
import org.example.annotations.ReporterFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@ReporterFactory("Extent")
public class ExtentReporterFactory implements IFactory<ExtentReporter, String> {

    private static ExtentReporter reporter;

    @Override
    public ExtentReporter create() {
        String timeStamp      = new SimpleDateFormat("MMddyyyy-HHmmss").format(Calendar.getInstance().getTime());
        String reportName     = "Report_" + timeStamp + ".html";
        String reportLocation = "./reports";
        return create(reportLocation + "/" + reportName);
    }

    @Override
    public ExtentReporter create(String name) {
        if(reporter == null) {
            reporter = new ExtentReporter(name);
        }
        return reporter;
    }
}
