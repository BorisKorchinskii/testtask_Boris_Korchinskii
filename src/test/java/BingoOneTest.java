import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObject.HomePage;
import resources.base;

import java.io.IOException;


public class BingoOneTest extends base {
    public WebDriver driver;


    @BeforeClass
    public void setup() throws IOException {
        driver = initializeDriver();
        //BasicConfigurator.configure();
        driver.get(prop.getProperty("url"));
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

    @Test(priority = 1, description = "Check if Home tab changing color on mouseover")
    public void homeColor()  {
        HomePage homePage = new HomePage(driver);
        homePage.checkHomeColor();
    }

    @Test(priority = 2, description = "Check if Products tab changing color on mouseover")
    public void productsColor()  {
        HomePage homePage = new HomePage(driver);
        homePage.checkProductsColor();
    }

    @Test(priority = 3, description = "Check if Client Hub tab changing color on mouseover")
    public void clientHubColor()   {
        HomePage homePage = new HomePage(driver);
        homePage.checkClientHubColor();
    }

    @Test(priority = 4, description = "Check if Company tab changing color on mouseover")
    public void companyColor()  {
        HomePage homePage = new HomePage(driver);
        homePage.checkCompanyColor();
    }

    @Test(priority = 5, description = "Check if News tab changing color on mouseover")
    public void newsColor() {
        HomePage homePage = new HomePage(driver);
        homePage.checkNewsColor();
    }

    @Test(priority = 6, description = "Check if Contact tab changing color on mouseover")
    public void contactColor()  {
        HomePage homePage = new HomePage(driver);
        homePage.checkContactColor();
    }

    @Test(priority = 7, description = "Check if Bingo option changing color on mouseover")
    public void bingoColor()  {
        HomePage homePage = new HomePage(driver);
        homePage.checkBingoColor();
    }

}
