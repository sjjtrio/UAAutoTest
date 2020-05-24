package Runner.API;

import Utilities.cloud.fw.ProfileProperties;
import Utilities.cloud.rest.RequestBuilder;
import Utilities.cloud.rest.RestCall;
import Utilities.cloud.rest.RestCallCache;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

public class RunnerBase extends AbstractTestNGCucumberTests {

    @BeforeSuite
    public void prepareAPITest(ITestContext test) throws IOException, ConfigurationException {

        ProfileProperties.getInstance().load();
        //RequestBuilder.ServiceHost = getProperty(ProfileProperties.KEY_PROP_APISERVICEHOST);
        //RequestBuilder.ServicePort = getProperty(ProfileProperties.KEY_PROP_APISERVICEHOSTPORT);
        //RequestBuilder.BasePath = getProperty(ProfileProperties.KEY_PROP_APISERVICEBASEPATH);

        
    }

    public String getProperty(String key){
        return ProfileProperties.getInstance().getProperties().getProperty(key);
    }

    public void runScenario(PickleEventWrapper pickleWrapper, CucumberFeatureWrapper featureWrapper) throws Throwable {
        // Set Steps for current scenario
        //RestCallCache.getInstance()
        //        .setScenarioSteps(pickleWrapper.getPickleEvent().pickle.getSteps().stream().map(x->x.getText()).collect(Collectors.toList()));

        // Set Current Scenario Name, If current scenario name is different, setup log folder.
        //if(!RestCallCache.getInstance().getCurrentScenarioName().equals(pickleWrapper.getPickleEvent().pickle.getName())){
        //    RestCallCache.getInstance().setCurrentDataLine(1);
            //RestCallCache.getInstance().setCurrentScenarioName(pickleWrapper.getPickleEvent().pickle.getName());
        //}else{
            // if Scenario name is same, keep using the same log folder.
        //RestCallCache.getInstance().setCurrentDataLine(RestCallCache.getInstance().getCurrentDataLine()+1);
        //}
        //RestCallCache.getInstance()
        //        .setLogOutputPath(ProfileProperties.getInstance().getLogOutputPathAuth(
        //                RestCallCache.getInstance().getTimeStamp()
        //                , pickleWrapper.getPickleEvent().pickle.getName()
        //                , RestCallCache.getInstance().getCurrentDataLine()));

        super.runScenario(pickleWrapper, featureWrapper);
    }
}
