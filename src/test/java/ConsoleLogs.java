import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.log.Log;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ConsoleLogs {
    //ChromeDriver driver;
    EdgeDriver driver;
    @BeforeClass
    public void setup(){
        //WebDriverManager.chromedriver().setup();
        //driver= new ChromeDriver();
        WebDriverManager.chromiumdriver().setup();
        driver= new EdgeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void viewBrowserConsoleLogs(){
        //Get the Devtools & create a session
        DevTools devTools=  driver.getDevTools();
        devTools.createSession();

        //Enable the console Logs
        devTools.send(Log.enable());

        //Add a listener for console logs
        devTools.addListener(Log.entryAdded(),logEntry -> {System.out.println("----------");
            System.out.println("Level: " + logEntry.getLevel());
            System.out.println("Text: " + logEntry.getText());
            System.out.println("Broken Url: "+logEntry.getUrl());
        });

        //Load AUT
        driver.get("https://the-internet.herokuapp.com/broken_images");
    }

}
