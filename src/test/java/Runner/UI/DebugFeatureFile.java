package Runner.UI;
import cucumber.api.CucumberOptions;

@CucumberOptions(plugin = {"pretty", "html:target/cucumber", "Runner.plugin.UI.CustomFormatter"},
        features = "src/test/resources/Features/UI/DXCUser/DXCUser.feature",
        glue = {"BDD.stepdefs.UI"})

public class DebugFeatureFile extends RunnerBase{
}
