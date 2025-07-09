package amazon.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocator;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    @FindBy(xpath = "//div[@class='puisg-row']//span[@class='a-icon-alt']")
    List<WebElement> ratings;

    @FindBy(xpath = "(//a[@class = 'a-link-normal s-line-clamp-4 s-link-style a-text-normal'])[3]")
    WebElement clickProduct;

    @FindBy(xpath = "//div[@class = 'puisg-row']//span[@class = 'a-price-whole']")
    List<WebElement> priceLH;

     @FindBy(xpath = "//h2[@class = 'a-size-medium a-spacing-none a-color-base a-text-normal']/span")
    List<WebElement> productNames;
    

   
    
    public List<String> priceScrape() {
        List<String> prices = new ArrayList<>();

        for (WebElement price : priceList) {
            String rawPrice = price.getAttribute("innerText").trim();  

            if (!rawPrice.isEmpty()) {
                
                String cleanedPrice = rawPrice.replaceAll("[^0-9]", "");
                prices.add(cleanedPrice);
            }
        }

        return prices;
    }


    public void clickNextPAGE() throws InterruptedException{

       Actions scrollAction = new Actions(driver);
        scrollAction.moveToElement(nextPage).perform();

        Thread.sleep(3000);

  //   ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextPage);

        
        
        nextPage.click();
    }


 public List<Float> ratingOFproducts() {
    List<Float> ratingOrder = new ArrayList<>();

    System.out.println("Total ratings found: " + ratings.size());

    for (WebElement likes : ratings) {
        String rates = likes.getAttribute("innerText"); 

        if (rates != null) {
            rates = rates.trim();
           // System.out.println("Raw rating text: '" + rates + "'");

            if (!rates.isEmpty() && rates.matches("^[0-9.]+.*")) {
                String floatPart = rates.split(" ")[0]; // extract "4.3"
                try {
                    Float rater = Float.parseFloat(floatPart);
                    ratingOrder.add(rater);
                } catch (NumberFormatException e) {
                    System.out.println("Failed to parse: " + floatPart);
                }
            }
        }
    }

    System.out.println("Final rating list: " + ratingOrder);
    return ratingOrder;
}

public void clickingProduct(){
    clickProduct.click();
}

public void sortByfeatures() throws InterruptedException{

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement dropdownTrigger  = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='a-dropdown-prompt']")));
    dropdownTrigger.click();

    WebElement sortBySelect = wait.until(ExpectedConditions.elementToBeClickable(
    By.xpath("//select[@id='s-result-sort-select']")));
    Select select = new Select(sortBySelect);
    select.selectByVisibleText("Price: Low to High");
    

}
 

public List<Integer> lowtoHighPrice(){
    List<Integer> prices = new ArrayList<>();
    for(WebElement price : priceLH){
        
        String ex = price.getAttribute("innerText").trim();
        String ex1 = ex.replaceAll("[^0-9]", "");
        if(!ex1.isEmpty()){
            try {
                int pricevalue = Integer.parseInt(ex1);
                prices.add(pricevalue);
            } catch (Exception e) {
                System.out.println("Skipping invalid price: " + ex);
            }
        }
        
    }
    return prices;
}


public List<String> earBuds(){
    List<String> earB = new ArrayList<>();
    for(WebElement ebuds : productNames){
        String ears = ebuds.getText().toLowerCase().trim();
        earB.add(ears);
    }
    return earB;
}



}
