package Runner.plugin.API;

import DataModel.CONST.*;
import Utilities.SSH;
import Utilities.cloud.fw.HTMLReportGenerator;
import Utilities.cloud.fw.ProfileProperties;
import Utilities.cloud.rest.RestCallCache;
import cucumber.api.PickleStepTestStep;
import cucumber.api.Result;
import cucumber.api.event.*;
import cucumber.api.formatter.Formatter;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CustomFormatter implements Formatter {

    public CustomFormatter() {}

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepStarted.class, stepStartedHandler);
        publisher.registerHandlerFor(TestRunStarted.class, testRunStartedHandler);
        publisher.registerHandlerFor(TestCaseStarted.class, testCaseStartedEventHandler);
        publisher.registerHandlerFor(TestStepFinished.class, stepFinishedEventHandler);
        publisher.registerHandlerFor(TestCaseFinished.class, caseFinishedEventHandler);
        publisher.registerHandlerFor(TestRunFinished.class, testRunFinishedEventHandler);

    }

    private EventHandler<TestRunStarted> testRunStartedHandler = new EventHandler<TestRunStarted>() {
        @Override
        public void receive(TestRunStarted event) {
            handleTestRunStarted(event);
        }
    };
    private EventHandler<TestCaseStarted> testCaseStartedEventHandler = new EventHandler<TestCaseStarted>() {
        @Override
        public void receive(TestCaseStarted caseStarted) {
            handleTestCaseStarted(caseStarted);
        }
    };

    private EventHandler<TestStepStarted> stepStartedHandler = new EventHandler<TestStepStarted>() {
        @Override
        public void receive(TestStepStarted event) {
            handleTestStepStarted(event);
        }
    };

    private EventHandler<TestStepFinished> stepFinishedEventHandler = new EventHandler<TestStepFinished>(){
        @Override
        public void receive(TestStepFinished testStepFinished) {
            handleTestStepFinished(testStepFinished);
        }
    };

    private EventHandler<TestCaseFinished> caseFinishedEventHandler = new EventHandler<TestCaseFinished>() {
        @Override
        public void receive(TestCaseFinished testCaseFinished) {
            handleTestCaseFinished(testCaseFinished);
        }
    };

    private EventHandler<TestRunFinished> testRunFinishedEventHandler = new EventHandler<TestRunFinished>() {
        @Override
        public void receive(TestRunFinished testRunFinished) {
            try {
                handleTestRunFinished(testRunFinished);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    private void handleTestRunStarted(TestRunStarted event){
        //System.out.println("Test Run Started.");
        if(RestCallCache.getInstance().getTimeStamp().equals("")){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MMM_dd_HH_mm_ss");
            String timeStamp = sdf.format(new Date());
            RestCallCache.getInstance().setTimeStamp(timeStamp);

            HTMLReportGenerator.getInstance().setTitle("Test Report "+timeStamp + ". Build Number: "
                    + ProfileProperties.getInstance().getBuildNumber());

            HTMLReportGenerator.getInstance().setStyle();
        }

        // Open tunnel switch
        if(ProfileProperties.getInstance().getOpenTunnel() !=null){
            if(ProfileProperties.getInstance().getOpenTunnel().toLowerCase().equals("on")){
                SSH.getInstance().setValues(ProfileProperties.getInstance().getJumpPort()
                        , ProfileProperties.getInstance().getJumperAddress()
                        , ProfileProperties.getInstance().getRemoteAddress()
                        , ProfileProperties.getInstance().getRemotePort());

                SSH.getInstance().connect(ProfileProperties.getInstance().getJumperUser()
                        , ProfileProperties.getInstance().getJumperPsw());

            }
        }

    }

    private void handleTestCaseStarted(TestCaseStarted event){
        //System.out.println("Test Case Started.");
        RestCallCache.getInstance().clearAll();

        String testCaseName = event.testCase.getName();
        if(RestCallCache.getInstance().getCurrentScenarioName().equals(testCaseName)){
            RestCallCache.getInstance().setCurrentDataLine(RestCallCache.getInstance().getCurrentDataLine()+1);
        }else{
            RestCallCache.getInstance().setCurrentDataLine(1);
            RestCallCache.getInstance().setCurrentScenarioName(testCaseName);
        }

        RestCallCache.getInstance().setCurrentCaseNumber(RestCallCache.getInstance().getCurrentCaseNumber()+1);

        RestCallCache.getInstance()
                .setLogOutputPath(ProfileProperties.getInstance().getLogOutputPathAuth(
                        RestCallCache.getInstance().getTimeStamp()
                        , testCaseName
                        , RestCallCache.getInstance().getCurrentDataLine()));

    }

    private void handleTestStepStarted(TestStepStarted event) {
        //System.out.println("Test Step Started.");
        if(event.testStep instanceof PickleStepTestStep) {
            RestCallCache.getInstance().clearCurrentStepCallLog();
            RestCallCache.getInstance().clearCurrentStepCallNumber();
            RestCallCache.getInstance().clearTestStepOutputData();
            //ThreadLocalPickleStep.set((PickleStepTestStep)event.testStep);
            String stepName = ((PickleStepTestStep)event.testStep).getStepText();
            String fName = stepName
                    .replaceAll("\\s+", " ")
                    .replaceAll("[^a-zA-Z0-9- ]", "");
            RestCallCache.getInstance().setCurrentStepName(fName);
        }
    }

    private void handleTestStepFinished(TestStepFinished event){
        //System.out.println("Test Step " + event.result.getStatus().firstLetterCapitalizedName());
        if(event.testStep instanceof PickleStepTestStep){
            // get step status,
            TestStep testStep= new TestStep();
            testStep.setTestStepName(RestCallCache.getInstance().getCurrentStepName());
            testStep.setTestStepResult(event.result.getStatus());
            testStep.setTestStepDuration(event.result.getDuration());
            testStep.setTestStepCallList(new ArrayList<>(RestCallCache.getInstance().getCurrentStepCallLogs()));
            testStep.setOutputs(new HashMap<>(RestCallCache.getInstance().getTestStepOutPutData()));

            if(event.result.getStatus() != Result.Type.PASSED){
                testStep.setTestStepMessages(event.result.getErrorMessage());
                testStep.setTestStepResult(Result.Type.FAILED);
                RestCallCache.getInstance().addToCurrentCaseSteps(testStep);

                TestCaseExecution testCaseExecution = new TestCaseExecution();
                    testCaseExecution.generateCaseExecution(Result.Type.FAILED);
                RestCallCache.getInstance().setCurrentTestCaseExecution(testCaseExecution);

                HTMLReportGenerator.getInstance().addTestCase();
            }
            RestCallCache.getInstance().addToCurrentCaseSteps(testStep);
        }
    }

    private void handleTestCaseFinished(TestCaseFinished event){
        //System.out.println("Test Case Finished");
        if(RestCallCache.getInstance().getCurrentTestCaseExecution() == null){
            TestCaseExecution testCaseExecution = new TestCaseExecution();
            /*testCaseExecution.setTestName(RestCallCache.getInstance().getCurrentScenarioName());
            testCaseExecution.setTestDuration(event.result.getDuration().toString());
            testCaseExecution.setIteration(RestCallCache.getInstance().getCurrentDataLine());
            testCaseExecution.setTestSteps(RestCallCache.getInstance().getCurrentCaseSteps());
            testCaseExecution.setTestResult(event.result.getStatus());*/

            testCaseExecution.generateCaseExecution(event.result.getDuration().toString(),event.result.getStatus());
            RestCallCache.getInstance().setCurrentTestCaseExecution(testCaseExecution);

            HTMLReportGenerator.getInstance().addTestCase();
        }
    }

    private void handleTestRunFinished(TestRunFinished event) throws IOException{
        //System.out.println("Test Run Finished");
        // create html file
        String reportPath = ProfileProperties.getInstance()
                .getReportPath(ProfileProperties.getInstance().getBuildNumber()
                        + File.separator
                        + RestCallCache.getInstance().getTimeStamp());
        String cssFilePath = ProfileProperties.getInstance().getCssPath();

        // Create the output directory if necessary
        File baseDir = new File(reportPath);
        if (!baseDir.exists()) {
            if (!baseDir.mkdirs()) {
                throw new IOException("Failed to create directory for RestCall logs: " + reportPath);
            }
        } else if (!baseDir.isDirectory()) {
            throw new IOException("Non-directory file already exists for RestCall logs directory: " + reportPath);
        }

        File htmlFile = new File(reportPath + File.separator + "Report.html");
        HTMLReportGenerator.getInstance().addHead();
        HTMLReportGenerator.getInstance().addTestCasesDiv();
        HTMLReportGenerator.getInstance().addBody();

        FileUtils.writeStringToFile(htmlFile, HTMLReportGenerator.getInstance().write(),"UTF-8",false);

        // copy css file to html file folder
        File sourceCssFile = new File(cssFilePath);
        File desCssFile = new File(ProfileProperties.getInstance().getCssPath(reportPath));

        FileUtils.copyFile(sourceCssFile, desCssFile);

        SSH.getInstance().disConnect();
    }

}
