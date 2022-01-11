package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainLabCorpPage {

    private final String ACCEPT_COOKIES = "onetrust-accept-btn-handler";
    @FindBy(id = ACCEPT_COOKIES)
    public WebElement acceptCookiesButton;

}
