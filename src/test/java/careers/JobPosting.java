package careers;

import base.BaseTest;
import events.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.util.List;

public class JobPosting extends BaseTest {

    @Test
    public void confirmInformationOnTheJob() {
        String textToSearch = "Senior Software QA Analyst";
        String jobLocation = "Burlington, North Carolina";
        String jobLocationInApplyJobPage = "Burlington, NC";
        String jobId = "21-85987";
        String jobIdInApplyJobPage = "#21-85987";

        String fistPositionDescription = "Identifying, planning and executing testing activities within development sprints to ensure high quality software and compliance according to regulatory statutes, policies and procedures.";
        String secondPositionDescription = "Test web and desktop application software, including PowerBuilder, Java, C#, and ASP.Net, along with web services.";
        String thirdPositionDescription = "Create and maintain test plans, cases and summaries.";
        String desiredProgramingLanguage = "Java";

        MainLabCorpPage mainLabCorpPage = PageFactory.initElements(this.webDriver, MainLabCorpPage.class);
        Click.on(mainLabCorpPage.acceptCookiesButton);

        FatFooterMenu fatFooterMenu = PageFactory.initElements(this.webDriver, FatFooterMenu.class);
        ScrollWebPage.toElement(this.webDriver, fatFooterMenu.careersButton);
        WaitUntilElement.isClickable(this.webDriver,fatFooterMenu.careersButton);

        Click.on(fatFooterMenu.careersButton);

        GoTo goTo = new GoTo(this.webDriver);
        goTo.tab(1);

        CareerLabCorpPage careerLabCorpPage = PageFactory.initElements(this.webDriver, CareerLabCorpPage.class);
        WaitUntilElement.isClickable(this.webDriver,careerLabCorpPage.keyWordSearchTextBox);
        careerLabCorpPage.searchForCareer(textToSearch);

        WaitUntilElement.isVisible(this.webDriver,By.xpath(String.format(careerLabCorpPage.resultSearch,textToSearch)));
        Assert.assertTrue(this.webDriver.findElement(By.xpath(String.format(careerLabCorpPage.resultSearch,textToSearch))).isDisplayed());

        Click.on(this.webDriver, By.xpath(String.format(careerLabCorpPage.resultSearch,textToSearch)));

        JobDescriptionPage jobDescriptionPage = new JobDescriptionPage();
        WebElement element = this.webDriver.findElement(By.xpath(String.format(jobDescriptionPage.jobDescriptionHeading,textToSearch)));
        ScrollWebPage.toElement(this.webDriver, element);
        WaitUntilElement.isVisible(this.webDriver,By.xpath(String.format(jobDescriptionPage.jobDescriptionHeading,textToSearch)));
        Assert.assertTrue(this.webDriver.findElement(By.xpath(String.format(jobDescriptionPage.jobDescriptionHeading,textToSearch))).isDisplayed());
        Assert.assertTrue(jobDescriptionPage.getJobLocation(this.webDriver,jobLocation).isDisplayed());
        Assert.assertTrue(jobDescriptionPage.getJobId(this.webDriver,jobId).isDisplayed());

        List<WebElement> jobsDescriptionsList = jobDescriptionPage.getJobResponsibilities(this.webDriver);
        Assert.assertEquals(fistPositionDescription, jobsDescriptionsList.get(0).getText());
        Assert.assertEquals(secondPositionDescription, jobsDescriptionsList.get(1).getText());
        Assert.assertEquals(thirdPositionDescription, jobsDescriptionsList.get(2).getText());
        Assert.assertTrue(jobsDescriptionsList.get(1).getText().contains(desiredProgramingLanguage));
        jobDescriptionPage.clickOnApplyButton(this.webDriver);

        ApplyNowPage applyNowPage = PageFactory.initElements(this.webDriver,ApplyNowPage.class);
        applyNowPage.waitForCloseSignInModal(this.webDriver);
        applyNowPage.clickOnCloseSignInModal();

        Assert.assertEquals(textToSearch, applyNowPage.getJobTitle());
        Assert.assertEquals(jobLocationInApplyJobPage, applyNowPage.getJobLocation());
        Assert.assertEquals(jobIdInApplyJobPage, applyNowPage.getJobId());
        Assert.assertTrue(applyNowPage.getJobDescriptionInPosition(2).contains(desiredProgramingLanguage));
        applyNowPage.clickOnReturnToJobSearch();
    }
}
