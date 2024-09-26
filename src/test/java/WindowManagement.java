import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class WindowManagement {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().minimize();
        driver.get("https://demo.opencart.com/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        System.out.println("Title: " + driver.getTitle());
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void testNewWindowTab() {
        WebDriver newWindow = driver.switchTo().newWindow(WindowType.WINDOW);
        newWindow.get("https://demo.opencart.com/en-gb/product/macbook");
        System.out.println("Title: " + driver.getTitle());
    }

    @Test
    public void testWorkingInBothWindowTabs() {
        //automatically open and switch to the new window or tab
        driver.switchTo().newWindow(WindowType.TAB).get("https://demo.opencart.com/en-gb?route=account/login");
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        System.out.println("Title: " + driver.getTitle());

        //work in the new window or tab
        driver.findElement(By.id("input-email")).sendKeys("test@example.com");
        driver.findElement(By.id("input-password")).sendKeys("123456");
        driver.findElement(with(By.xpath("//button[text()='Login']"))).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        //get the window ID handles
        Set<String>allWindowsTabs= driver.getWindowHandles();
        Iterator<String> iterate = allWindowsTabs.iterator();
        String mainFirstWindow = iterate.next();

        //switch and work in the main window or tab
        driver.switchTo().window(mainFirstWindow);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@name='search']")).sendKeys("iPhone");
        driver.findElement(By.xpath("//input[@name='search']/following-sibling::button")).click();
        System.out.println("Title: " + driver.getTitle());
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }
}
