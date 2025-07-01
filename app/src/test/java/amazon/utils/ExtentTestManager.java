package amazon.utils;

import java.util.HashMap;
import java.util.Map;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentTestManager {

    static ExtentReports ep = ExtentManager.getExtentReports();
    static Map<Object, Object> extentMap = new HashMap<>();

    public static ExtentTest startTest(String testname){
        ExtentTest extentTest = ep.startTest(testname);
        extentMap.put(Thread.currentThread().getId(), extentTest);
        return extentTest;
    }

    public static ExtentTest getExtentest(){
        return (ExtentTest) extentMap.get(Thread.currentThread().getId());
    }

    public static void logInfo(String info){
        getExtentest().log(LogStatus.INFO, info);
    }

    public static void logPass(String info){
        getExtentest().log(LogStatus.PASS, info);
    }

    public static void logFail(String info){
        getExtentest().log(LogStatus.FAIL, info);
    }

    public static void logSkip(String info){
        getExtentest().log(LogStatus.SKIP, info);
    }

    public static void logScreenshot(String errordetails, String errorscreenshotpath){
        logInfo("Error details: "+errordetails);
        getExtentest().log(LogStatus.FAIL, getExtentest().addScreenCapture(errorscreenshotpath));
    }

    public static void endAndFlush(){
        ep.endTest(getExtentest());
        ep.flush();
    }
    
}
