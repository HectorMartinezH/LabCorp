package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FatFooterMenu {

    @FindBy(xpath = "//div[@class='footer-links']//following-sibling::a[contains(text(),'Careers')]")
    public WebElement careersButton;

}
