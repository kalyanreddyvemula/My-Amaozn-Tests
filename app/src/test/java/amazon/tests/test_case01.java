package amazon.tests;



import java.util.Arrays;
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
import amazon.pages.productDetails;



public class test_case01 extends BaseTest {

    HomePage hpage;
    SoftAssert soft;
    SearchResult sResult;
    productDetails pd;

    @BeforeMethod
    public void setUpPages(){   
        hpage = new HomePage(driver);
        sResult = new SearchResult(driver);
        pd = new productDetails(driver);
    }

    @Test(priority = 1 )
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

    @Test(priority = 2 )
    public void testcase02() throws InterruptedException{

        

       
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


    @Test(priority = 3)
    public void testcase03() throws InterruptedException{


        List<Integer> pincode = Arrays.asList(500081, 110001, 400001);
        

        logInfo("Entered webBrowser");
        logInfo("Enter product name in search");
        hpage.searchProduct("Table lamps");

        logInfo("Clicked the value");
        hpage.searchValue();

        pause(5000);
        

        logInfo("Clicking the product for more details");
        sResult.clickingProduct();
 

        for(int i=0; i<pincode.size(); i++){

            int pin = pincode.get(i);
            logInfo("Entering the PINCODE"+pin);
            pd.enterPincode(pin);


            

            logInfo("Prinitng Delivery information for: " + pin);
            pd.getnotePIN();

            pause(5000);
        }


    }

    @Test(priority = 4)
    public void testcase04()throws InterruptedException{

        soft = new SoftAssert();
        logInfo("Entered webBrowser");
        logInfo("Enter product name in search");
        hpage.searchProduct("external hard drive");

        logInfo("Clicked the value");
        hpage.searchValue();

        logInfo("Sorting the feature from LOW TO HIGH");
        sResult.sortByfeatures();

        pause(10000);
        logInfo("Extracting the Prices");
        System.out.println(sResult.lowtoHighPrice());
    }
}
    