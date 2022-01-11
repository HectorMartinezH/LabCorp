package events;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUntilElement {

    public static boolean isVisible(WebDriver webDriver, By locator){
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, 30);
            wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(locator)));
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isClickable(WebDriver webDriver, WebElement element){
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, 30);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
