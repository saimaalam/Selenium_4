import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.security.PublicKey;

public class Screenshot {
    WebDriver driver;
    @BeforeMethod
    public void setup(){
        WebDriverManager.firefoxdriver().setup();
        driver= new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://applitools.com/");
    }
    @Test
    public void takeWebelementScreenshot() throws IOException {
        WebElement header= driver.findElement(By.cssSelector("#h-ai-powered-end-to-end-testing"));
        File source = header.getScreenshotAs(OutputType.FILE);
        File destination = new File("screenshots/header.png");
        FileHandler.copy(source, destination);
    }
    @Test
    public void takeWebElementPageSectionScreenshot() throws IOException {
        WebElement applitoolsPageSection = driver.findElement
                (By.xpath("//*[@id='post-8']/div[1]/div/div"));
        File source = applitoolsPageSection.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("screenshots/Applitools Page Section.png"));

    }
    @Test
    public void takeFullPageScreenshot() throws IOException {
        File source = ((FirefoxDriver)driver).getFullPageScreenshotAs(OutputType.FILE);
        File destination = new File("screenshots/Full Page Screenshot.png");
        FileHandler.copy(source,destination);
    }


}
