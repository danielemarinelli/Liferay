package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private WebDriver driver = null;

    public WebDriver getDriver(String browser) {
        switch(browser.toLowerCase()) {
            case "chrome"  :
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\drivers\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox" :
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\drivers\\geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "opera" :
                System.setProperty("webdriver.opera.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\drivers\\operadriver.exe");
                driver = new OperaDriver();
                break;
            default :
                System.out.println("Incorrect browser name provided "+ browser);
                System.out.println("Hence running in Chrome ");
                driver = new ChromeDriver();
                break;
        }
        driver.manage().timeouts().pageLoadTimeout(Long.parseLong(TestConfig.getProperty("pageLoadTimeout")), TimeUnit.SECONDS);  // One time config done for the whole project
        driver.manage().timeouts().implicitlyWait(Long.parseLong(TestConfig.getProperty("implicitWait")), TimeUnit.SECONDS); // One time config
        return driver;
    }

    public void quitDriver() {
        if(driver!=null) {
            driver.quit();
        }
    }
}