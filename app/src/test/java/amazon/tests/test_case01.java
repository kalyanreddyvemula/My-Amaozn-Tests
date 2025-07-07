package amazon.tests;



import java.util.List;

import org.checkerframework.checker.units.qual.s;
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

    @Test(priority = 1)
    public void testcase01() throws InterruptedException{

        soft = new SoftAssert();

        logInfo("Verifying the the PAGE TITLE");
        soft.assertTrue(hpage.verifyTitle(), "Title missmatched");
        
        logInfo("Enter product name in search");
        hpage.searchProduct("Laptops");

        logInfo("Clicked the value");
        hpage.searchValue();


        for(int i=1; i<=3; i++){
        logInfo("Scraping the prices of PAGE"+i);
        System.out.println(sResult.priceScrape());

        logInfo("Going to PAGE"+(i+1));
        sResult.clickNextPAGE();

        pause(10000);


        }
        

        soft.assertAll();
    }

    @Test(priority = 2)
    public void test_case02() throws InterruptedException{

        

       
        logInfo("Entering into amazon website");
        
        
        logInfo("Enter product name in search");
        hpage.searchProduct("HeadPhones");

        logInfo("Clicked the value");
        hpage.searchValue();

        Thread.sleep(5000);

        logInfo("Extract the ratings and converting it to float numbers");
        System.out.println(sResult.ratingOFproducts());

        Thread.sleep(4000);

        
    }
}
