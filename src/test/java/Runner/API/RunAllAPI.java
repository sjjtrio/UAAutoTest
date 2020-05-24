package Runner.API;

import cucumber.api.CucumberOptions;

@CucumberOptions(plugin = {"pretty", "html:target/cucumber","Runner.plugin.API.CustomFormatter"},
        features = "src/test/resources/Features",
        glue = {"BDD.stepdefs.API"},
        tags = {"@API","not @Example","not @Ali"})
public class RunAllAPI extends RunnerBase {
}
