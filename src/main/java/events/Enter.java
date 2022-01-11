package events;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Enter {
    public static void text(WebDriver webDriver, By locator, String text){
        webDriver.findElement(locator).sendKeys(text);
    }

    public static void text(WebElement webElement, String text){
        webElement.sendKeys(text);
    }
}

