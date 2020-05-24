package BDD.stepdefs.UI;

import PageObjects.Bing.HomePage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class ExampleSteps  extends  StepBase{
    @Given("The pre-condition of test is ready")
    public void thePreConditionOfTestIsReady() {
        log("Initiation of testing......");
    }

    @Given("I open home page of (.*)")
    public void iOpenHomePageOfBing(String site_name) {
        String url = getProperty(site_name);
        getDriver().get(url);
        waitPageLoading();
    }

    @When("I search (.*)")
    public void iSearchText(String text) {
        getPage(HomePage.class).search(text);
        waitPageLoading();
    }

    @Then("The results should be correct")
    public void theResultsShouldBeCorrect() {
        log("Assertion......");
        //Assert.assertTrue("...");
    }
}
