package DataModel.CONST;

import cucumber.api.Result.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestStep {
    private String testStepName;
    private Type testStepResult;
    private String testStepDetails;
    private String testStepMessages;
    private List<String> testStepExtraMessageList;
    private List<APICallLog> testStepCallList;
    private long testStepDuration;
    private HashMap<String, String> outputs;

    public HashMap<String, String> getOutputs() {
        return outputs;
    }

    public void setOutputs(HashMap<String, String> outputs) {
        this.outputs = outputs;
    }

    public List<APICallLog> getTestStepCallList() {
        return testStepCallList;
    }

    public void setTestStepCallList(List<APICallLog> testStepCallList) {
        this.testStepCallList = testStepCallList;
    }

    public List<String> getTestStepExtraMessageList() {
        return testStepExtraMessageList;
    }

    public void setTestStepExtraMessageList(List<String> testStepExtraMessageList) {
        this.testStepExtraMessageList = testStepExtraMessageList;
    }

    public void addTestStepExtraMessage(String log){
        if(getTestStepExtraMessageList() == null){
            setTestStepExtraMessageList(new ArrayList<>());
        }

        getTestStepExtraMessageList().add(log);
    }

    public long getTestStepDuration() {
        return testStepDuration;
    }

    public void setTestStepDuration(long testStepDuration) {
        this.testStepDuration = testStepDuration;
    }

    public String getTestStepName() {
        return testStepName;
    }

    public void setTestStepName(String testStepName) {
        this.testStepName = testStepName;
    }

    public Type getTestStepResult() {
        return testStepResult;
    }

    public void setTestStepResult(Type testStepResult) {
        this.testStepResult = testStepResult;
    }

    public String getTestStepDetails() {
        return testStepDetails;
    }

    public void setTestStepDetails(String testStepDetails) {
        this.testStepDetails = testStepDetails;
    }

    public String getTestStepMessages() {
        return testStepMessages;
    }

    public void setTestStepMessages(String testStepMessages) {
        this.testStepMessages = testStepMessages;
    }
}
