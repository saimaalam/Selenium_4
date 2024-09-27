import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ElementPosition {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
       WebDriverManager.chromedriver().setup();
       driver = new ChromeDriver();
       driver.manage().window().maximize();
       driver.get("https://testautomationu.applitools.com/learningpaths.html");
    }
    @Test
    public void getPositionDimension(){
        WebElement logoTAU = driver.findElement(By.xpath("//div[@id='app']//header/a/img"));
        Rectangle rectTAUlogo= logoTAU.getRect();
        System.out.println("x: " +rectTAUlogo.getX());
        System.out.println("y: " +rectTAUlogo.getY());
        System.out.println("Width: " +rectTAUlogo.getWidth());
        System.out.println("Hight: " +rectTAUlogo.getHeight());
    }
}
