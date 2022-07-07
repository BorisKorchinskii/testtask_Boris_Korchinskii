package pageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import resources.base;

import java.util.*;

public class HomePage extends base {

    public WebDriver driver;

    public String whiteColor = "#ffffff";
    public String orangeColor = "#ff800e";


    @FindBy(css = "a[class='top-btn'] span")
    private WebElement check;

    @FindBy(xpath = "//a[@title='Home']")
    private WebElement home;

    @FindBy(xpath = "//a[@title='Games']")
    private WebElement products;

    @FindBy(xpath = "//a[@target='_blank'][normalize-space()='Client Hub']")
    private WebElement clienthub;

    @FindBy(xpath = "//a[normalize-space()='Company']")
    private WebElement company;

    @FindBy(xpath = "//a[normalize-space()='News']")
    private WebElement news;

    @FindBy(xpath = "//a[@href='https://pragmaticplay.com/en/contact#form']")
    private WebElement contact;

    @FindBy(xpath = "//a[normalize-space()='Bingo']")
    private WebElement bingo;

    @FindBy(xpath = "//div[@class='col-md-12']")
    //@FindBy(xpath = "//h2[@class='title']")
    private WebElement variants;

    @FindBy(xpath = "//div[@class='slick-slide slick-cloned']")
    private WebElement slider;

    @FindBy(xpath = "//div[@class='col-md-4 col-xs-12']/p")
    public List<WebElement> titles;
    @FindBy(xpath = "//div[contains(@class, 'slick-active')]")
    public List<WebElement> visiblethemes;
    @FindBy(xpath = "//div[@class='slick-slide']")
    public List<WebElement> notvisiblethemes;
    @FindBy(xpath = "//img")
    public List<WebElement> allimages;
    @FindBy(xpath = "//div[@class='right-arrow slick-arrow']")
    private WebElement rightarrow;
    @FindBy(xpath = "//div[@class='slick-slide slick-current slick-active']/div/div")
    private WebElement currentactivetheme;

    @FindBy(xpath = "//img[contains(@src,'Snowball-Blast')]")
    private WebElement activelink;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkHomeColor() {
        check.click();
        Actions actions = new Actions(driver);
        String homeColor = home.getCssValue("color");
        actions.moveToElement(home).perform();
        String actualHomeColor = Color.fromString(homeColor).asHex();
        Assert.assertEquals(actualHomeColor, whiteColor);
    }

    public void checkProductsColor() {
        Actions actions = new Actions(driver);
        actions.moveToElement(products).perform();
        String productsColor = products.getCssValue("color");
        String actualProductColor = Color.fromString(productsColor).asHex();
        Assert.assertEquals(actualProductColor, whiteColor);
    }

    public void checkClientHubColor() {
        Actions actions = new Actions(driver);
        actions.moveToElement(clienthub).perform();
        String clienthubColor = clienthub.getCssValue("color");
        String actualClientHubColor = Color.fromString(clienthubColor).asHex();
        Assert.assertEquals(actualClientHubColor, orangeColor);
    }

    public void checkCompanyColor() {
        Actions actions = new Actions(driver);
        actions.moveToElement(company).perform();
        String companyColor = company.getCssValue("color");
        String actualCompanyColor = Color.fromString(companyColor).asHex();
        Assert.assertEquals(actualCompanyColor, whiteColor);
    }

    public void checkNewsColor() {
        Actions actions = new Actions(driver);
        actions.moveToElement(news).perform();
        String newsColor = news.getCssValue("color");
        String actualNewsColor = Color.fromString(newsColor).asHex();
        Assert.assertEquals(actualNewsColor, orangeColor);
    }

    public void checkContactColor() {
        Actions actions = new Actions(driver);
        actions.moveToElement(contact).perform();
        String contactColor = contact.getCssValue("color");
        String actualContractColor = Color.fromString(contactColor).asHex();
        Assert.assertEquals(actualContractColor, orangeColor);
    }

    public void checkBingoColor() {
        Actions actions = new Actions(driver);
        actions.moveToElement(products).moveToElement(bingo).perform();
        String bingoColor = bingo.getCssValue("color");
        String actualBingoColor = Color.fromString(bingoColor).asHex();
        Assert.assertEquals(actualBingoColor, orangeColor);
    }

    public void checkNineElements() {
        check.click();
        Actions actions = new Actions(driver);
        actions.moveToElement(products).moveToElement(bingo).click().perform();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", variants);

        // Verify that there are 9 Bingo themes.
        int notvisiblesize = notvisiblethemes.size();
        int visiblethemessize = visiblethemes.size();
        int totalthemes = notvisiblesize + visiblethemessize;
        Assert.assertEquals(totalthemes, 9);
    }

    public void checkElementsForDuplication() throws InterruptedException {
        // Verify that the elements aren't duplicated.
        // Put elements names into original List and compare with expected results in visible list
        Actions actions = new Actions(driver);
        actions.moveToElement(products).moveToElement(bingo).click().perform();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", variants);

        List<String> originalnames = Arrays.asList("Bingo Blast",
                "Reels Room",
                "Country Roads",
                "Zoom Room",
                "Boombox",
                "Sweet Bonanza Bingo",
                "Snowball Blast",
                "Rock N Swing",
                "The Jackpot Room");

        List<String> visiblenames = new ArrayList<String>();
        for (int i = 0; i < 9; i++) {
            rightarrow.click();
            Thread.sleep(1000);
            String name = currentactivetheme.getText();
            visiblenames.add(name);
        }
        Assert.assertEquals(visiblenames, originalnames);
    }

    // Verify that the link to the image contains a theme's name (i.e. Rock'n'Swing, Bingo Blast, Boombox, etc.).
    public void checkLinkContent() {
        // The idea here is to add all correct links to collection, filter by link partial names,
        // and then to compare with a collection containing correct theme's name using Assert equals,
        // but I stuck here
        Actions actions = new Actions(driver);
        actions.moveToElement(products).moveToElement(bingo).click().perform();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", variants);

        List<String> specificlinks = new ArrayList<String>();
        for (WebElement link : allimages) {
            String weblink = link.getText() + " - " + link.getAttribute("src");
            if (weblink.contains("Snowball-Blast.png")) {
                specificlinks.add(weblink);
            }
            if (weblink.contains("The-Jackpot-Room.png")) {
                specificlinks.add(weblink);
            }
            if (weblink.contains("Bingo-Blast.png")) {
                specificlinks.add(weblink);
            }
            if (weblink.contains("Release-The-Kraken.png")) {
                specificlinks.add(weblink);
            }
            if (weblink.contains("Country-Roads.png")) {
                specificlinks.add(weblink);
            }
            if (weblink.contains("Zoom-Room.png")) {
                specificlinks.add(weblink);
            }
            if (weblink.contains("Thumbnail-Boombox.png")) {
                specificlinks.add(weblink);
            }
            if (weblink.contains("Thumbnail-Sweet-Bonanza-Bingo.png")) {
                specificlinks.add(weblink);
            }
        }
        System.out.println(specificlinks);
    }
}