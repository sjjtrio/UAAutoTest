package Runner.UI;

import BDD.stepdefs.UI.StepCommon;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.*;

import java.io.IOException;

public class RunnerBase extends AbstractTestNGCucumberTests {

    @BeforeSuite
    public void prepareTest() throws IOException
    {
        System.out.println("Before Suite...." );
        //StepCommon.getInstance().initTest();
    }


    @AfterSuite
    public void quitDriver(){
        System.out.println("Quit web driver.....");
        if(StepCommon.getInstance().getDriver() != null){
            StepCommon.getInstance().getDriver().quit();
        }
    }
}
