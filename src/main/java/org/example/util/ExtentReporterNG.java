package org.example.util;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentReporterNG {

    public static ExtentReports getReportObject() {
        String path = System.getProperty("user.dir") + File.separator + "reports" +File.separator+ "index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Results"); // giving name to the report
        reporter.config().setDocumentTitle("TestResults");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Sona Sh");
        return extent;
    }
}
