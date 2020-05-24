package Runner.API;

import cucumber.api.CucumberOptions;

@CucumberOptions(plugin = {"pretty", "html:target/cucumber","Runner.plugin.API.CustomFormatter"},
        features = "src/test/resources/Features/API",
        glue = {"BDD.stepdefs.API"},
        name = {"API test example"}
)
public class DebugSingleAPI extends RunnerBase{
}
