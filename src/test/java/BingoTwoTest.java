import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObject.HomePage;
import resources.base;

import java.io.IOException;

import static resources.base.prop;

public class BingoTwoTest {

    public WebDriver driver;

    @BeforeClass
    public void setup() throws IOException {
        //BasicConfigurator.configure();
        driver = base.initializeDriver();
        driver.get(prop.getProperty("url"));
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

    @Test(priority = 1, description = "Check if 9 Bingo themes visible")
    public void nineElements() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.checkNineElements();
    }

    @Test(priority = 2, description = "Check if elements aren't duplicated")
    public void elementsForDuplication() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.checkElementsForDuplication();
    }

    @Test(priority = 3, description = "Check if link to the image contains a theme's name")
    public void linkContent() {
        HomePage homePage = new HomePage(driver);
        homePage.checkLinkContent();
    }

}
