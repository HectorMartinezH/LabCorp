package events;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Click {
    public static void on(WebDriver webDriver, By locator){
        webDriver.findElement(locator).click();
    }
    public static void on(WebElement webElement){
        webElement.click();
    }
}
