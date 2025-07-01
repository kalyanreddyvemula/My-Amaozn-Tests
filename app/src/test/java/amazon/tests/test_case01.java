package amazon.tests;

import java.sql.Driver;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static amazon.utils.ExtentTestManager.*;
import static amazon.utils.ExtentTestManager.logInfo;

import amazon.pages.HomePage;



public class test_case01 extends BaseTest {

    HomePage hpage;
    SoftAssert soft;

    @BeforeMethod
    public void setUpPages(){   
        hpage = new HomePage(driver);
    }

    @Test
    public void testcase01(){

        soft = new SoftAssert();

        logInfo("Verifying the the PAGE TITLE");
        soft.assertTrue(hpage.verifyTitle(), "Title missmatched");
        
        logInfo("Enter produc name in search");
        hpage.searchProduct("Laptops");

        logInfo("Clicked the value");
        hpage.searchValue();

        soft.assertAll();
    }
}
