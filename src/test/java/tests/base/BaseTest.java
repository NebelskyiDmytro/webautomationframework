package tests.base;

import common.CommonActions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.base.BasePage;

import static common.Config.CLEAR_COOKIES_AND_STORAGE;
import static constants.Constant.TimeoutVariables.THREAD_SLEEP;

public class BaseTest {

    protected WebDriver driver;
    protected CommonActions commonActions;

    protected BasePage basePage;

    @BeforeMethod
    public void initDriverInstance() {
        commonActions = new CommonActions();
        driver = commonActions.createDriver();
        initPages(driver);
    }

    @AfterMethod
    public void closeDriverInstance() throws InterruptedException {
        if (CLEAR_COOKIES_AND_STORAGE) {
            clearCookiesAndLocalStorage();
        }
        waitAfterMethod();
        driver.quit();
    }

    public void clearCookiesAndLocalStorage() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        driver.manage().deleteAllCookies();
        javascriptExecutor.executeScript("window.sessionStorage.clear()");
    }

    public void waitAfterMethod() throws InterruptedException {
        if (THREAD_SLEEP > 0) {
            Thread.sleep(THREAD_SLEEP * 1000);
        } else {
            Assert.fail("THREAD_SLEEP variable should be greater than zero");
        }
    }

    public void initPages(WebDriver driver) {
        basePage = PageFactory.initElements(driver, BasePage.class);
    }

}
