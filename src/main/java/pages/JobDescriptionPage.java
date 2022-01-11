package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class JobDescriptionPage {

    public String jobDescriptionHeading = "//h1[text() = '%s']";
    private String jobLocation = "//span[contains(@class,'job-location') and contains(text(),'%s') ]";
    private String jobId = "//span[contains(@class,'job-id') and contains(text(),'%s')]";
    private String jobResponsibilities = ".ats-description > ul > li";
    private String applyNowButton = "//a[contains(@class,'job-apply')]";

    public WebElement getJobLocation(WebDriver webDriver, String jobLocation) {
        return webDriver.findElement(By.xpath(String.format(this.jobLocation,jobLocation)));
    }

    public WebElement getJobId(WebDriver webDriver, String jobId) {
        return webDriver.findElement(By.xpath(String.format(this.jobId,jobId)));
    }

    public List<WebElement> getJobResponsibilities(WebDriver webDriver) {
        return webDriver.findElements(By.cssSelector(jobResponsibilities));
    }

    public void clickOnApplyButton(WebDriver webDriver) {
        webDriver.findElements(By.xpath(applyNowButton)).get(0).click();
    }


}
