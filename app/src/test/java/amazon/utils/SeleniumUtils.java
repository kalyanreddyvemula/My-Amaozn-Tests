package amazon.utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class SeleniumUtils {

    
    

    static final String screenshotPath = System.getProperty("user.dir")+File.separator+"reports";

    public static void clickElement(WebDriver driver, WebElement clickButton){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(clickButton));
        clickButton.click();
    }

    public static void enterText(WebDriver driver, WebElement enterKeys, String enterText){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(enterKeys));
        enterKeys.clear();

        enterKeys.sendKeys(enterText);

    }

    public static String capture(WebDriver driver) throws IOException{

        File sourcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(screenshotPath + File.separator+System.currentTimeMillis()+".png");

        String filepath = destinationFile.getAbsolutePath();
        FileUtils.copyFile(sourcFile, destinationFile);
        return  filepath;
    }

    public static void pause(long time) throws InterruptedException{
        Thread.sleep(time);
    }
}
