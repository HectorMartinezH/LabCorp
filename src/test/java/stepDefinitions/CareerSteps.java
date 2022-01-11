package stepDefinitions;

import base.BaseTest;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import events.Click;
import events.GoTo;
import events.ScrollWebPage;
import events.WaitUntilElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CareerSteps {

    protected WebDriver webDriver;
    private String url = "https://www.labcorp.com";
    private String browser = "chrome";


    private MainLabCorpPage mainLabCorpPage = PageFactory.initElements(this.webDriver, MainLabCorpPage.class);
    private FatFooterMenu fatFooterMenu = PageFactory.initElements(this.webDriver, FatFooterMenu.class);
    private CareerLabCorpPage careerLabCorpPage = PageFactory.initElements(this.webDriver, CareerLabCorpPage.class);
    private JobDescriptionPage jobDescriptionPage = new JobDescriptionPage();
    private ApplyNowPage applyNowPage = PageFactory.initElements(this.webDriver,ApplyNowPage.class);

    @Before
    public void setUp() throws Exception {
        switch (browser){
            case "chrome":
                System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
                webDriver = new ChromeDriver();
                break;
            default:
                throw new Exception(browser + " no soportado");
        }
        webDriver.get(url);
        webDriver.manage().window().maximize();

        mainLabCorpPage = PageFactory.initElements(this.webDriver, MainLabCorpPage.class);
        fatFooterMenu = PageFactory.initElements(this.webDriver, FatFooterMenu.class);
        careerLabCorpPage = PageFactory.initElements(this.webDriver, CareerLabCorpPage.class);
        jobDescriptionPage = new JobDescriptionPage();
        applyNowPage = PageFactory.initElements(this.webDriver,ApplyNowPage.class);
    }


    @When("The user logs in to careers option from LabCorp portal")
    public void the_user_logs_in_to_careers_option_from_lab_corp_portal() {
        Click.on(mainLabCorpPage.acceptCookiesButton);
        ScrollWebPage.toElement(this.webDriver, fatFooterMenu.careersButton);
        WaitUntilElement.isClickable(this.webDriver,fatFooterMenu.careersButton);
        Click.on(fatFooterMenu.careersButton);
        GoTo goTo = new GoTo(this.webDriver);
        goTo.tab(1);
    }

    @And("^searches for \"([^\"]*)\"$")
    public void searches_for(String textToSearch) {
        WaitUntilElement.isClickable(this.webDriver,careerLabCorpPage.keyWordSearchTextBox);
        careerLabCorpPage.searchForCareer(textToSearch);
        this.webDriver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);

    }

    @Then("^check that in the results is displayed \"([^\"]*)\"$")
    public void check_results(String textToCheck) {
        WaitUntilElement.isVisible(this.webDriver, By.xpath(String.format(careerLabCorpPage.resultSearch,textToCheck)));
        WebElement element = this.webDriver.findElement(By.xpath(String.format(careerLabCorpPage.resultSearch,textToCheck)));
        ScrollWebPage.toElement(this.webDriver, element);
        Assert.assertTrue(this.webDriver.findElement(By.xpath(String.format(careerLabCorpPage.resultSearch,textToCheck))).isDisplayed());
    }

    @When("^View the details for \"([^\"]*)\"$")
    public void view_details_job(String textToCheck) {
        Click.on(this.webDriver, By.xpath(String.format(careerLabCorpPage.resultSearch,textToCheck)));
    }

    @Then("^verify the job title \"([^\"]*)\" is displayed$")
    public void check_job_description(String textToCheck) {
        WebElement element = this.webDriver.findElement(By.xpath(String.format(jobDescriptionPage.jobDescriptionHeading,textToCheck)));
        ScrollWebPage.toElement(this.webDriver, element);
        WaitUntilElement.isVisible(this.webDriver,By.xpath(String.format(jobDescriptionPage.jobDescriptionHeading,textToCheck)));
        Assert.assertTrue(this.webDriver.findElement(By.xpath(String.format(jobDescriptionPage.jobDescriptionHeading,textToCheck))).isDisplayed());
    }

    @Then("^verify the job location \"([^\"]*)\" is displayed$")
    public void check_job_location(String textToCheck) {
        Assert.assertTrue(jobDescriptionPage.getJobLocation(this.webDriver,textToCheck).isDisplayed());
    }

    @Then("^verify the job id \"([^\"]*)\" is displayed$")
    public void check_job_id(String textToCheck) {
        Assert.assertTrue(jobDescriptionPage.getJobId(this.webDriver,textToCheck).isDisplayed());
    }

    @Then("^verify the job descriptions are correct$")
    public void check_job_descriptions() {
        String fistPositionDescription = "Identifying, planning and executing testing activities within development sprints to ensure high quality software and compliance according to regulatory statutes, policies and procedures.";
        String secondPositionDescription = "Test web and desktop application software, including PowerBuilder, Java, C#, and ASP.Net, along with web services.";
        String thirdPositionDescription = "Create and maintain test plans, cases and summaries.";
        String desiredProgramingLanguage = "Java";

        List<WebElement> jobsDescriptionsList = jobDescriptionPage.getJobResponsibilities(this.webDriver);
        Assert.assertEquals(fistPositionDescription, jobsDescriptionsList.get(0).getText());
        Assert.assertEquals(secondPositionDescription, jobsDescriptionsList.get(1).getText());
        Assert.assertEquals(thirdPositionDescription, jobsDescriptionsList.get(2).getText());
        Assert.assertTrue(jobsDescriptionsList.get(1).getText().contains(desiredProgramingLanguage));
    }

    @When("^applying for the job$")
    public void apply_for_the_job() {
        jobDescriptionPage.clickOnApplyButton(this.webDriver);
        applyNowPage.waitForCloseSignInModal(this.webDriver);
        applyNowPage.clickOnCloseSignInModal();
    }

    @Then("^verify the information of the aplyied job is the correct one$")
    public void check_the_info_of_the_aplyied_job(){
        String textToSearch = "Senior Software QA Analyst";
        String jobLocationInApplyJobPage = "Burlington, NC";
        String jobIdInApplyJobPage = "#21-85987";
        String desiredProgramingLanguage = "Java";

        Assert.assertEquals(textToSearch, applyNowPage.getJobTitle());
        Assert.assertEquals(jobLocationInApplyJobPage, applyNowPage.getJobLocation());
        Assert.assertEquals(jobIdInApplyJobPage, applyNowPage.getJobId());
        Assert.assertTrue(applyNowPage.getJobDescriptionInPosition(2).contains(desiredProgramingLanguage));
    }

    @Then("^return to job search page$")
    public void return_to_job_search_page(){
        applyNowPage.clickOnReturnToJobSearch();
        Assert.assertTrue(this.webDriver.getTitle().equals("Career Site - Self Service"));
    }

    @After
    public void tearDown(){
        if(webDriver != null)
            webDriver.quit();
    }
}
