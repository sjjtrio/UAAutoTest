package Runner.UI;

import cucumber.api.CucumberOptions;

@CucumberOptions(plugin = {"pretty", "html:target/cucumber", "Runner.plugin.UI.CustomFormatter"},
        features = "src/test/resources/Features",
        glue = {"BDD.stepdefs.UI"},
        tags = {"@UI","not @Example","not @Jira","not @Ignore"})

public class TestNGAllTest extends RunnerBase {

}
