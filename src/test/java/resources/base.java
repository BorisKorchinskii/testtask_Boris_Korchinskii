package resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class base {
    public static WebDriver driver;
    public static Properties prop;

    public static WebDriver initializeDriver() throws IOException {

        prop = new Properties();
        FileInputStream fis = new FileInputStream("src\\test\\java\\resources\\data.properties");

        prop.load(fis);
        String browserName = prop.getProperty("browser");
        System.out.println(browserName);

        if ("chrome".equals(browserName)) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_win32\\chromedriver.exe");

            driver = new ChromeDriver();
            driver.manage().window().maximize();
        } else if ("firefox".equals(browserName)) {
            System.setProperty("webdriver.gecko.driver", "geckodriver-v0.31.0-win64\\geckodriver.exe");
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }
}
