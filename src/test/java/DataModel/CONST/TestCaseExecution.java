package DataModel.CONST;

import Utilities.cloud.rest.RestCallCache;
import cucumber.api.Result.Type;

import java.util.HashMap;
import java.util.List;

public class TestCaseExecution {
    private String testName;
    private Type testResult;
    private List<TestStep> testSteps;
    private String testDuration;
    private int iteration;

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Type getTestResult() {
        return testResult;
    }

    public void setTestResult(Type testResult) {
        this.testResult = testResult;
    }

    public List<TestStep> getTestSteps() {
        return testSteps;
    }

    public void setTestSteps(List<TestStep> testSteps) {
        this.testSteps = testSteps;
    }

    public String getTestDuration() {
        return testDuration;
    }

    public void setTestDuration(String testDuration) {
        this.testDuration = testDuration;
    }

    public TestCaseExecution generateCaseExecution(String testDuration, Type testResult){
        setTestName(RestCallCache.getInstance().getCurrentScenarioName());
        setTestDuration(testDuration);
        setIteration(RestCallCache.getInstance().getCurrentDataLine());
        setTestSteps(RestCallCache.getInstance().getCurrentCaseSteps());
        setTestResult(testResult);

        return this;
    }

    public TestCaseExecution generateCaseExecution(Type testResult){
        setTestName(RestCallCache.getInstance().getCurrentScenarioName());
        setIteration(RestCallCache.getInstance().getCurrentDataLine());
        setTestSteps(RestCallCache.getInstance().getCurrentCaseSteps());
        setTestResult(testResult);

        return this;
    }

}
