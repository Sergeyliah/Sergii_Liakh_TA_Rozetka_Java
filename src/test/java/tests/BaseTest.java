package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import pages.HomePage;
import pages.ProductPage;
import pages.SearchResultsPage;
import pages.ShoppingCartPage;
import utility.Utility;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class BaseTest {
    public static ThreadLocal<WebDriver> dr = new ThreadLocal<>();

    @BeforeTest
    public void profileSetUp() {
        chromedriver().setup();
    }


    public BaseTest() {
    }

    @BeforeMethod
    public void testsSetUp() throws IOException {
        WebDriver driver;
        if (Utility.fetchPropertyValue ("browser").toString().equalsIgnoreCase("Chrome")){
            driver = new ChromeDriver();
            setWebDriver(driver);

        }
        else if (Utility.fetchPropertyValue("browser").toString().equalsIgnoreCase("Firefox")){
            driver = new FirefoxDriver();
            setWebDriver(driver);

        }
        else if (Utility.fetchPropertyValue("browser").toString().equalsIgnoreCase("ie")){
            driver = new InternetExplorerDriver();
            setWebDriver(driver);

        }
        else {
            driver = new ChromeDriver();
            setWebDriver(driver);

        }

        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
        getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        getDriver().get(Utility.fetchPropertyValue("applicationURL").toString());
    }
    @AfterMethod
    public void tearDown() {
        getDriver().close();
    }

    public static WebDriver getDriver() {
        return dr.get();
    }
    public void setWebDriver(WebDriver driver){
        dr.set(driver);
    }

    public HomePage getHomePage() {
        return new HomePage(getDriver());
    }
    public SearchResultsPage getSearchResultsPage() {
        return new SearchResultsPage(getDriver());
    }
    public static ShoppingCartPage getShoppingCartPage() {
        return new ShoppingCartPage(getDriver());
    }
    public ProductPage getProductPage() {
        return new ProductPage(getDriver());
    }

}
