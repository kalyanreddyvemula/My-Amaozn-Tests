package amazon.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocator;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SearchResult {
    WebDriver driver;
    public SearchResult(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
    }

    @FindBy(xpath = "//span[@class = 'a-price']/span[@class='a-offscreen']")
    List<WebElement> priceList;


    @FindBy(xpath =    "(//li[@class = 's-list-item-margin-right-adjustment'])[4]")
    WebElement nextPage;

    
    public List<WebElement> getPriceElements() {
        return priceList;
    }
    public List<String> priceScrape() {
        List<String> prices = new ArrayList<>();

        for (WebElement price : priceList) {
            String rawPrice = price.getAttribute("innerText").trim();  

            if (!rawPrice.isEmpty()) {
                // Remove currency symbol and commas, keep only digits
                String cleanedPrice = rawPrice.replaceAll("[^0-9]", "");
                prices.add(cleanedPrice);
            }
        }

        return prices;
    }


    public void clickNextPAGE(){
        nextPage.click();
    }
}
