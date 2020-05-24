package Runner.plugin.UI;

import BDD.stepdefs.UI.StepCommon;
import Utilities.cloud.fw.ProfileProperties;
import cucumber.api.PickleStepTestStep;
import cucumber.api.Result;
import cucumber.api.event.*;
import cucumber.api.formatter.Formatter;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.io.IOException;

public class CustomFormatter implements Formatter {

    private static boolean takeScreenShot = false;
    private static int passed = 0;
    private static int failed = 0;

    private EventHandler<TestRunStarted> testRunStartedEventHandler = new EventHandler<TestRunStarted>() {
        @Override
        public void receive(TestRunStarted testRunStarted) {
            System.out.println("Run Start event");
            passed = 0;
            failed = 0;
        }
    };

    /***
     * Event handler when test case started.
     */
    private EventHandler<TestCaseStarted> caseStartedEventHandler = new EventHandler<TestCaseStarted>() {
        @Override
        public void receive(TestCaseStarted testCaseStarted) {
            takeScreenShot = true;
            try {
                System.out.println("Case Start event");
                StepCommon.getInstance().initTest();
                createScreenShotFolder(testCaseStarted);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ConfigurationException e) {
                e.printStackTrace();
            }
        }
    };

    /**
     *  Event handler for each step finished.
     */
    private EventHandler<TestStepFinished> stepFinishedEventHandler = new EventHandler<TestStepFinished>() {
        @Override
        public void receive(TestStepFinished testStepFinished) {
            //System.out.println(testStepFinished.result.getStatus().firstLetterCapitalizedName());
            if(testStepFinished.result.is(Result.Type.PASSED) || testStepFinished.result.is(Result.Type.FAILED))
            {
                try {
                    if (testStepFinished.testStep instanceof PickleStepTestStep) {
                        takeScreenShotByStepName((((PickleStepTestStep)testStepFinished.testStep).getStepLine()
                                < 10 ? "0" : "" )+ ((PickleStepTestStep)testStepFinished.testStep).getStepLine()
                                + "_" + "Finished_"
                                + ((PickleStepTestStep)testStepFinished.testStep).getStepText());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                takeScreenShot = false;
            }
        }
    };

    /**
     * Event handler for each step started
     */
    private EventHandler<TestStepStarted> stepStartedEventHandler = new EventHandler<TestStepStarted>() {
        @Override
        public void receive(TestStepStarted testStepStarted) {
            try {
                if(testStepStarted.testStep instanceof PickleStepTestStep){
                    if(takeScreenShot){
                        takeScreenShotByStepName((((PickleStepTestStep)testStepStarted.testStep).getStepLine()
                                < 10 ? "0" : "") + ((PickleStepTestStep)testStepStarted.testStep).getStepLine()
                                + "_" + "Begin_"
                                + ((PickleStepTestStep)testStepStarted.testStep).getStepText());
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    private EventHandler<TestCaseFinished> testCaseFinishedEventHandler = new EventHandler<TestCaseFinished>() {
        @Override
        public void receive(TestCaseFinished testCaseFinished) {
            if(testCaseFinished.result.is(Result.Type.PASSED)){
               passed += 1;
            }else{
                failed += 1;
                renameScreenFolderWithTestName(testCaseFinished);
            }
            System.out.println("Quit web driver.....");
            if(StepCommon.getInstance().getDriver() != null){
                StepCommon.getInstance().getDriver().quit();
                StepCommon.getInstance().setDriver(null);
            }
        }
    };

    private EventHandler<TestRunFinished> testRunFinishedEventHandler = new EventHandler<TestRunFinished>() {
        @Override
        public void receive(TestRunFinished testRunFinished) {
            System.setProperty("passed",Integer.toString(passed));
            System.setProperty("failed",Integer.toString(failed));
        }
    };

    /**
     * Register all handlers.
     * @param eventPublisher
     */
    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestRunStarted.class, testRunStartedEventHandler);
        eventPublisher.registerHandlerFor(TestCaseStarted.class, caseStartedEventHandler);
        eventPublisher.registerHandlerFor(TestStepFinished.class, stepFinishedEventHandler);
        eventPublisher.registerHandlerFor(TestStepStarted.class, stepStartedEventHandler);
        eventPublisher.registerHandlerFor(TestCaseFinished.class, testCaseFinishedEventHandler);
        eventPublisher.registerHandlerFor(TestRunFinished.class, testRunFinishedEventHandler);
    }

    /**
     * Create folder for screen shots, named with scenario name and time stamp
     * @param event
     * @throws IOException
     */
    private void createScreenShotFolder(TestCaseStarted event) throws IOException {
        String caseName = event.testCase.getName();

        String dirPath = ProfileProperties.getInstance().getDefaultUiScreenOutputDir();
        String buildNumber = ProfileProperties.getInstance().getBuildNumber();
        File baseDir = new File(dirPath + File.separator + buildNumber + File.separator
                + caseName + File.separator + ProfileProperties.getInstance().getTimeStamp());
                //+ File.separator + "Passed");

        if (!baseDir.exists()) {
            if (!baseDir.mkdirs()) {
                throw new IOException("Failed to create directory for Screenshots: " + dirPath);
            }
        } else if (!baseDir.isDirectory()) {
            throw new IOException("Non-directory file already exists for Screenshots: " + dirPath);
        }

        StepCommon.getInstance().setCurrentCaseName(caseName);
        StepCommon.getInstance().setScreenShotDir(baseDir.getAbsolutePath());
    }

    public void takeScreenShotByStepName(String stepName) throws IOException {
        StepCommon.getInstance().takeScreenShotDirByStepName(stepName);
    }

    public void takeScreenShotByStepName(String stepName, String res) throws IOException {
        StepCommon.getInstance().takeScreenShotDirByStepName(stepName, res);
    }

    public void renameScreenFolderWithTestName(TestCaseFinished testCaseFinished){
        File timeStampFolder = new File(StepCommon.getInstance().getScreenShotDir());
        File renamedFolder = new File(timeStampFolder.getParent()
                + File.separator + "["
                + testCaseFinished.result.getStatus().lowerCaseName() + "]"
                + ProfileProperties.getInstance().getTimeStamp());
        timeStampFolder.renameTo(renamedFolder);
    }
}
