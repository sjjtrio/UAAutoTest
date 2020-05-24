package Runner.UI;

import cucumber.api.CucumberOptions;

@CucumberOptions(plugin = {"pretty", "html:target/cucumber", "Runner.plugin.UI.CustomFormatter"},
        features = "src/test/resources/Features/UI",
        glue = {"BDD.stepdefs.UI"},
        name = {"Search engine website test"}
        )

public class DebugSingleTest extends RunnerBase {
    // This class is empty because the options above fulfill all needs.
}