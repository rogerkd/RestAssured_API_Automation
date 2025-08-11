package com.yourorg.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/target/ExtentReport.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setDocumentTitle("API Automation Report");
            spark.config().setReportName("Sprint-4 REST Assured API Tests");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Project", "Booking Management System");
            extent.setSystemInfo("Sprint", "4");
            extent.setSystemInfo("Tester", "Deepak Kumar");
        }
        return extent;
    }
}
