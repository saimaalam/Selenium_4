import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class RelativeLocators {
    WebDriver driver;
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test
    public void testRelativeLocators() {
        WebElement loginPanel = driver.findElement(By.xpath("//div[@class='orangehrm-login-error']//div"));
        WebElement username= driver.findElement(with(By.tagName("p")).near(loginPanel));
        WebElement password= driver.findElement(with(By.tagName("p")).near(username));
        System.out.println(username.getText() + " || " + password.getText());
    }
    @Test
    public void testListOfElements(){
        List<WebElement> allSocialMedia = driver.findElements(with(By.tagName("a")).near(driver.findElement(By.className("orangehrm-login-footer-sm"))));
        for (WebElement socialMedia : allSocialMedia) {
            System.out.println(socialMedia.getAttribute("href"));
        }
    }

}
