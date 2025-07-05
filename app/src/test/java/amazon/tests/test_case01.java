package amazon.tests;



import java.util.List;


import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static amazon.utils.SeleniumUtils.*;

import static amazon.utils.ExtentTestManager.*;
import static amazon.utils.ExtentTestManager.logInfo;

import amazon.pages.HomePage;
import amazon.pages.SearchResult;



public class test_case01 extends BaseTest {

    HomePage hpage;
    SoftAssert soft;
    SearchResult sResult;

    @BeforeMethod
    public void setUpPages(){   
        hpage = new HomePage(driver);
        sResult = new SearchResult(driver);
    }

    @Test
    public void testcase01() throws InterruptedException{

        soft = new SoftAssert();

        logInfo("Verifying the the PAGE TITLE");
        soft.assertTrue(hpage.verifyTitle(), "Title missmatched");
        
        logInfo("Enter produc name in search");
        hpage.searchProduct("Laptops");

        logInfo("Clicked the value");
        hpage.searchValue();


        for(int i=1; i<3; i++){
        logInfo("Scraping the prices of PAGE"+i);
        System.out.println(sResult.priceScrape());

        logInfo("Going to PAGE"+(i+1));
        sResult.clickNextPAGE();

        pause(10000);


        }
        

        soft.assertAll();
    }
}
