package pages;

import events.Clear;
import events.Click;
import events.Enter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CareerLabCorpPage {
    @FindBy(css = ".search-keyword")
    public  WebElement keyWordSearchTextBox;

    @FindBy(css = ".search-form__submit")
    public WebElement submitButton;

    @FindBy(xpath = "//input[@class = 'search-location']")
    public WebElement cityStateZipRTextBox;

    public String resultSearch = "//h2[text() = '%s']";

    public void searchForCareer(String textToSearch) {
        Enter.text(keyWordSearchTextBox,textToSearch);
        Clear.textBoxContent(cityStateZipRTextBox);
        Click.on(submitButton);

    }
}
