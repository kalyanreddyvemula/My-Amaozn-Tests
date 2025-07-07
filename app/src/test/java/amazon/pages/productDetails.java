package amazon.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class productDetails {

    WebDriver driver;

    public productDetails(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(id = "contextualIngressPtLabel_deliveryShortLine")
    WebElement pincodeForDelivery;

    @FindBy(id = "GLUXZipUpdateInput")
    WebElement entercode;

    @FindBy(xpath = "//input[@aria-labelledby=\"GLUXZipUpdate-announce\"]")
    WebElement applyButton;

    @FindBy(xpath = "//*[@id=\"mir-layout-DELIVERY_BLOCK-slot-SECONDARY_DELIVERY_MESSAGE_LARGE\"]/span")
    WebElement textafterPIN;


  
 
    public void enterPincode(int PIN)throws InterruptedException{

        pincodeForDelivery.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(entercode));
        entercode.click();
        entercode.clear();
        entercode.sendKeys(String.valueOf(PIN));
        applyButton.click();
    }

    public void getnotePIN(){
        String message = textafterPIN.getText();
        System.out.println(message);
    }
   


    


    
}
