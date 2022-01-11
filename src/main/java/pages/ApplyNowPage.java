package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ApplyNowPage {

    @FindBy(xpath = "//button[contains(@class,'closebutton')]")
    private WebElement closeSignInModal;

    @FindBy(xpath = "//span[contains(@class,'jobTitle')]")
    private WebElement jobTitle;

    @FindBy(xpath = "//span[@class='resultfootervalue']")
    private WebElement jobLocation;

    @FindBy(xpath = "//span[@class='jobnum']")
    private WebElement jobId;

    @FindBy(xpath = "//ul/li")
    private List<WebElement> descriptions;

    @FindBy(xpath = "//span[text()='Return to Job Search']//parent::button")
    private WebElement returnToJobSearchButton;

    public void waitForCloseSignInModal(WebDriver webDriver) {
        WebDriverWait wait = new WebDriverWait(webDriver,30);
        wait.until(ExpectedConditions.elementToBeClickable(closeSignInModal));
    }

    public void clickOnCloseSignInModal() {
        closeSignInModal.click();
    }

    public String getJobTitle() {
        return jobTitle.getText();
    }

    public String getJobLocation() {
        return jobLocation.getText();
    }

    public String getJobId() {
        return jobId.getText();
    }

    public String getJobDescriptionInPosition(int position) {
        return descriptions.get(position).getText();
    }

    public void clickOnReturnToJobSearch() {
        returnToJobSearchButton.click();
    }


}
