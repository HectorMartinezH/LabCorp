package base;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver webDriver;
    private String url = "https://www.labcorp.com";
    private String browser = "chrome";

    @Before
    public void setUp() throws Exception {
        switch (browser){
            case "chrome":
                System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
                webDriver = new ChromeDriver();
                break;
//            case "firefox":
//                System.setProperty("webdriver.gecko.driver","resource/geckodriver.exe");
//                webDriver = new FirefoxDriver();
//                break;
            default:
                throw new Exception(browser + " no soportado");
        }
        webDriver.get(url);
        webDriver.manage().window().maximize();
    }

    @After
    public void tearDown(){
        if(webDriver != null)
            webDriver.quit();
    }
}
