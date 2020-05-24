package Runner.UI;
import cucumber.api.CucumberOptions;

@CucumberOptions(plugin = {"pretty", "html:target/cucumber", "Runner.plugin.UI.CustomFormatter"},
        features = "src/test/resources/Features/UI",
        glue = {"BDD.stepdefs.UI"},
        tags = {"@WorkingCase"})

public class DebugTags extends RunnerBase{
}
