package amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import amazon.utils.SeleniumUtils;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);

    }

    @FindBy(name = "field-keywords")
    WebElement searchBar;

    @FindBy(id = "nav-search-submit-button")
    WebElement searchIcon;

    public void searchProduct(String product){
        SeleniumUtils.clickElement(driver, searchBar);
        SeleniumUtils.enterText(driver, searchBar, product); 

    }

    public void searchValue(){
        SeleniumUtils.clickElement(driver, searchIcon);
    }

    public  Boolean verifyTitle(){
        return driver.getTitle().contains("Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
    }

}
