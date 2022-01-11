package events;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class GoTo {
    private WebDriver webDriver;

    public GoTo(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void tab(int tabNumber) {
        ArrayList<String> tabs2 = new ArrayList<String> (this.webDriver.getWindowHandles());
        this.webDriver.switchTo().window(tabs2.get(1));
    }
}
