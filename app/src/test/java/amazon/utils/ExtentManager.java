package amazon.utils;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
    
    static ExtentReports extentReports = null;
    static final String reportPath = System.getProperty("user.dir")+File.separator+"reports"+File.separator+"Test_reports.html"+File.separator;

    public static synchronized ExtentReports getExtentReports(){
        if (extentReports == null) {
            extentReports = new ExtentReports(reportPath, true);
        }
        return extentReports;
    }
}
