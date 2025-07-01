package amazon.tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Driver;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import amazon.utils.ExtentTestManager;


import amazon.utils.DriverSingleton;
import amazon.utils.SeleniumUtils;

public class BaseTest {

    static WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void launchBrowser(){
        driver = DriverSingleton.getDriver("https://www.amazon.in/");
    }

    @BeforeMethod
    public void beforeMethod(Method m){
        ExtentTestManager.startTest(m.getName());   
    }
    
    @AfterMethod
    public void afterMethod(ITestResult iTestResult, Method m) throws IOException{
        if(iTestResult.getStatus() == iTestResult.SUCCESS){
            ExtentTestManager.logPass(m.getName()+" : PASSED");
        }else if (iTestResult.getStatus() == iTestResult.FAILURE){
            ExtentTestManager.logScreenshot(iTestResult.getThrowable().getMessage(), SeleniumUtils.capture(driver));
        }
        else{
            ExtentTestManager.logSkip(m.getName()+" : SKIPPED");
        }
        ExtentTestManager.endAndFlush();
    }

    @AfterSuite
    public void afterTestComplete(){
        if (driver != null) {
            driver.quit();
        }
    
        
        
    }
}
