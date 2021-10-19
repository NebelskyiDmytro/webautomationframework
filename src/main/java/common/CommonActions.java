package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

import static common.Config.*;
import static constants.Constant.TimeoutVariables.IMPLICIT_WAIT;

public class CommonActions {

    protected WebDriver driver = null;
    protected ChromeOptions options = null;

    /**
     * This is implementation of singleton pattern to use only single instance of browser
     */
    public synchronized WebDriver createDriver() {

        if (driver == null) {

            switch (PLATFORM_AND_BROWSER) {
                case "win_chrome":
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                    System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, String.valueOf(CHROME_CLIENT_LOG));
                    options = new ChromeOptions();
                    options.addArguments("--lang=" + CHROME_LANGUAGE);
                    driver = new ChromeDriver(options);
                    break;
                case "linux_chrome":
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
                    System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, String.valueOf(CHROME_CLIENT_LOG));
                    options = new ChromeOptions();
                    options.addArguments("--lang=" + CHROME_LANGUAGE);
                    driver = new ChromeDriver(options);
                    break;
                default:
                    Assert.fail("Incorrect value of PLATFORM_AND_BROWSER: " + PLATFORM_AND_BROWSER);
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);

        }

        return driver;

    }

}
