package amazon.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSingleton {

    static WebDriver driver;

    public static WebDriver getDriver(String url){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
      //  driver.manage().timeouts().implicitlyWait(10);
        return driver;
    }
    
}
