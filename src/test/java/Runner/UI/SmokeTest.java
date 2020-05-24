package Runner.UI;
import cucumber.api.CucumberOptions;

@CucumberOptions(plugin = {"pretty", "html:target/cucumber", "Runner.plugin.UI.CustomFormatter"},
        features = "src/test/resources/Features/UI",
        glue = {"BDD.stepdefs.UI"},
        tags = {"@Smoke"})

public class SmokeTest extends RunnerBase{
}
