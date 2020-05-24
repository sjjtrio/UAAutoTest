package Utilities.cloud.rest;

import DataModel.CONST.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RestCallCache is a data structure for storing and accessing RestCall objects.
 * It allows RestCall objects to be easily shared between step definition classes.
 */
public class RestCallCache {

    /**
     * Singleton instance
     */
    private static volatile RestCallCache instance = null;
    private static volatile String lastCall = "";
    private static volatile String timeStamp = "";
    private static volatile String currentStepName = "";
    private static volatile String currentScenarioName = "";
    private static volatile int currentDataLine = 1;
    private static volatile TestCaseExecution currentTestCaseExecution = null;
    private static volatile List<TestStep> currentCaseSteps = new ArrayList<>();
    private static volatile List<APICallLog> currentStepCallLogs = new ArrayList<>();
    private static volatile List<TestCaseExecution> testCaseExecutions = new ArrayList<>();
    private static volatile HashMap<String, String> testStepOutPutData = new HashMap<>();
    private static volatile int currentCaseNumber = 0;
    private static volatile int currentStepCallNumber = 0;


    public HashMap<String, String> getTestStepOutPutData() {
        return testStepOutPutData;
    }

    public void addTestStepOutputData(String k, String v ){
        testStepOutPutData.put(k,v);
    }

    public void clearTestStepOutputData(){
        testStepOutPutData.clear();
    }

    public int getCurrentStepCallNumber() {
        return currentStepCallNumber;
    }

    public void setCurrentStepCallNumber(int number) {
        currentStepCallNumber = number;
    }

    public void addCurrentStepCallNumber(){
        setCurrentStepCallNumber(getCurrentStepCallNumber() + 1);
    }

    public void clearCurrentStepCallNumber(){
        setCurrentStepCallNumber(0);
    }

    public List<APICallLog> getCurrentStepCallLogs(){
        return currentStepCallLogs;
    }

    public void addCurrentStepCallLog(APICallLog log){
        currentStepCallLogs.add(log);
    }

    public void clearCurrentStepCallLog(){
        currentStepCallLogs.clear();
    }

    public void addToCurrentCaseSteps(TestStep testStep){
        getCurrentCaseSteps().add(testStep);
    }

    public void clearCurrentCaseSteps(){
        getCurrentCaseSteps().clear();
    }

    public void clearAll(){
        clearCurrentCaseSteps();
        clearCurrentStepCallLog();
        clearCurrentStepCallNumber();
        setCurrentTestCaseExecution(null);
    }

    public List<TestStep> getCurrentCaseSteps() {
        return currentCaseSteps;
    }

    public void setCurrentCaseSteps(List<TestStep> currentCaseSteps) {
        RestCallCache.currentCaseSteps = currentCaseSteps;
    }

    public int getCurrentCaseNumber() {
        return currentCaseNumber;
    }

    public void setCurrentCaseNumber(int currentCaseNumber) {
        RestCallCache.currentCaseNumber = currentCaseNumber;
    }

    public TestCaseExecution getCurrentTestCaseExecution() {
        return currentTestCaseExecution;
    }

    public void setCurrentTestCaseExecution(TestCaseExecution currentTestCaseExecution) {
        RestCallCache.currentTestCaseExecution = currentTestCaseExecution;
    }

    public List<TestCaseExecution> getTestCaseExecutions() {
        return testCaseExecutions;
    }

    public void setTestCaseExecutions(List<TestCaseExecution> testCaseExecutions) {
        RestCallCache.testCaseExecutions = testCaseExecutions;
    }

    public int getCurrentDataLine() {
        return currentDataLine;
    }

    public void setCurrentDataLine(int currentDataLine) {
        RestCallCache.currentDataLine = currentDataLine;
    }

    public String getCurrentScenarioName() {
        return currentScenarioName;
    }

    public void setCurrentScenarioName(String currentScenarioName) {
        RestCallCache.currentScenarioName = currentScenarioName;
    }

    public String getCurrentStepName() {
        return currentStepName;
    }

    public void setCurrentStepName(String currentStepName) {
        RestCallCache.currentStepName = currentStepName;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        RestCallCache.timeStamp = timeStamp;
    }

    /**
     * Class accessor for the singleton instance.
     *
     * @return RestCallCache
     */
    public static RestCallCache getInstance() {
        if (instance == null) {
            synchronized (RestCallCache.class) {
                if (instance == null) {
                    instance = new RestCallCache();
                }
            }
        }

        return instance;
    }

    /**
     * Data structure for storing and accessing RestCall objects.
     */
    private Map<String, RestCall> calls;

    /**
     * Log output path for all RestCall objects created by this cache.
     */
    private String logOutputPath;

    /**
     * Default constructor - required for picocontainer dependency injection.
     */
    public RestCallCache() {
        this.calls = new HashMap<>();
        this.logOutputPath = "";
    }

    /**
     * Calls get method using RestCall.DEFAULT_NAME.
     *
     * @return RestCall
     */
    public RestCall get() {
        return get(RestCall.DEFAULT_NAME);
    }

    /**
     * Returns a reference to the stored RestCall using the provided name.
     * If no RestCall object is stored, construct and store a new instance.
     *
     * @param name String
     * @return RestCall
     */
    public RestCall get(String name) {
        if (!has(name)) {
            calls.put(name, new RestCall(getLogOutputPath(), name));
        }
        lastCall = name;
        calls.get(name).setLogOutputPath(this.logOutputPath);
        return calls.get(name);
    }

    public void set(String name, RestCall call){
        calls.remove(name);
        calls.put(name, call);
    }

    /**
     * Accessor for the log output path.
     *
     * @return String
     */
    public String getLogOutputPath() {
        return logOutputPath;
    }

    /**
     * Calls has method using RestCall.DEFAULT_NAME.
     *
     * @return boolean
     */
    public boolean has() {
        return has(RestCall.DEFAULT_NAME);
    }

    /**
     * Checks if a RestCall object is stored using the provided name.
     *
     * @param name String
     * @return boolean
     */
    public boolean has(String name) {
        return calls.containsKey(name);
    }

    /**
     * Modifier for the log output path.
     *
     * @param logOutputPath String
     */
    public void setLogOutputPath(String logOutputPath) {
        this.logOutputPath = logOutputPath;
    }

    public RestCall getLastCall(){
        return get(lastCall);
    }
}