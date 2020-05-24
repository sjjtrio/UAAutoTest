package BDD.stepdefs.UI;

import Utilities.DataKeys;
import Utilities.JsonOP;
import Utilities.ScenarioContext;
import Utilities.cloud.fw.ProfileProperties;
import Utilities.cloud.rest.*;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.PageFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class StepCommon {

    /**
     * Properties will be used in almost every test*
     */
    private static volatile WebDriver driver;

    private static volatile StepCommon instance = null;

    private  String CurrentCaseName = "";

    private  String CurrentStepName = "";

    private  String ScreenShotDir = "";

    public String getScreenShotDir() {
        return ScreenShotDir;
    }

    public  void setScreenShotDir(String screenShotDir) {
        ScreenShotDir = screenShotDir;
    }

    public  String getCurrentCaseName() {
        return CurrentCaseName;
    }

    public  void setCurrentCaseName(String currentCaseName) {
        CurrentCaseName = currentCaseName;
    }

    public  String getCurrentStepName() {
        return CurrentStepName;
    }

    public  void setCurrentStepName(String currentStepName) {
        CurrentStepName = currentStepName;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        StepCommon.driver = driver;
    }

    public String getProperty(String property){
        //System.out.println("Try to get property value: " + property);
        return ProfileProperties.getInstance().getProperties().getProperty(property);
    }

    public Boolean elementExists(By by){
        return getDriver().findElements(by).size() > 0;
    }

    /**
     * This will be invoked when debug run with JUnit
     */
    /*@Before("@UI")
    public void prepareScenario(Scenario scenario) throws IOException{
        System.out.println("This is Before function " + scenario.getName());
        getInstance().initTest();

    }*/

   /* public void writeFeatureFileFromJira() throws IOException, RestCallLoggingException, ConfigurationException {
        if(System.getProperty(ProfileProperties.KEY_PROP_JIRA_API_ISSUE_KEY) != null){
            loadJiraTestFeature(System.getProperty(ProfileProperties.KEY_PROP_JIRA_API_ISSUE_KEY));
        }

        if(System.getProperty(ProfileProperties.KEY_PROP_JIRA_API_JQL) != null){
            loadJiraSearchFeature(System.getProperty(ProfileProperties.KEY_PROP_JIRA_API_JQL));
        }
    }

    private void initJiraProperties() throws IOException, ConfigurationException {
        ProfileProperties.getInstance().load(ProfileProperties.GENERAL_PROFILE_DEFAULT);
        RequestBuilder.JiraHost = getProperty(ProfileProperties.KEY_PROP_JIRA_API_HOST);
        RequestBuilder.JiraBasePath = getProperty(ProfileProperties.KEY_PROP_JIRA_API_END_POINT);
        RequestBuilder.APIProxy = getProperty(ProfileProperties.KEY_PROP_JIRA_PROXY);
        RequestBuilder.APIProxyPort = getProperty(ProfileProperties.KEY_PROP_JIRA_PROXY_PORT);
    }

    private RestCall getJiraCall(){
        RestCall jiraCall = RestCallCache.getInstance().get(DataKeys.API_Jira.API_JIRA_REQUEST);
            jiraCall.setRequest(RequestBuilder
                .defaultRequestJiraAPI(getProperty(ProfileProperties.KEY_PROP_JIRACOOKIEHEADER)
                , getProperty(ProfileProperties.KEY_PROP_JIRAPROXY_SWITCH).toLowerCase().equals("yes")));
        return jiraCall;
    }

    private void loadJiraSearchFeature(String jql) throws IOException, RestCallLoggingException, ConfigurationException {
        initJiraProperties();
        JiraSearchRequest request = new JiraSearchRequest(jql);
            request.setFields(new String[]{"id","key","summary","description","issuetype","labels"});
        String requestBody = JsonOP.getInstance().serialiseJson(request);
        RestCall jiraCall = getJiraCall();
            jiraCall.getRequest().body(requestBody);
            jiraCall.makeRequest(ResourcePaths.POST.getResourcePath()
                    , ResourcePaths.JIRA_ISSUE_SEARCH.getResourcePath()
                    , "JiraSearchTests"
                    ,true);
            //jiraCall.makeRequest(ResourcePaths.GET.getResourcePath()
            //    , ResourcePaths.JIRA_ISSUE_SEARCH_JQL.getResourcePath(jql)
            //    , "JiraSearchTests"
            //    , true);
        JiraSearchResults searchResults = JsonOP.getInstance().deserialiseJson(jiraCall.getResponse().body().asString(), JiraSearchResults.class);
        if(searchResults.getTotal() > searchResults.getMaxResults()){
            // if total is more than max results, change start at value and send request again
        }else{
            JiraIssue[] automatedIssues = Arrays.stream(searchResults.getIssues())
                    .filter(x-> Arrays.stream(x.getFields().getLabels())
                            .anyMatch(y->y.equals("Automated"))).toArray(JiraIssue[]::new);
            writeFeatureFile(automatedIssues, jql);
        }
    }

    /*private void loadJiraTestFeature(String issueID) throws IOException, RestCallLoggingException, ConfigurationException {
        initJiraProperties();
        RestCall jiraCall = getJiraCall();
               jiraCall.makeRequest(ResourcePaths.GET.getResourcePath()
                , ResourcePaths.JIRA_ISSUE_DETAILS.getResourcePath(issueID)
                , "JiraSingleTest"
                ,true);
        JiraIssue jiraIssue = JsonOP.getInstance().deserialiseJson(jiraCall.getResponse().body().asString(), JiraIssue.class);
        writeFeatureFile(new JiraIssue[]{jiraIssue}, null);
    }

    private void writeFeatureFile(JiraIssue[] issues, String jql) throws IOException {
        // Write feature file
        File file = new File(ProfileProperties.KEY_PROP_JIRA_FEATURE_FILE_PATH);
        FileWriter fw = new FileWriter(file, false);
        PrintWriter pw = new PrintWriter(fw, true);
        // Start Writing..

        pw.println();
        pw.write("Feature: Test Scenarios Loaded From Jira.  " + (jql!=null ? "(JQL = " + jql + ")" : ""));
        pw.println();

        Arrays.stream(issues).forEach( x->{
            if(x.getFields().getDescription() != null && x.getFields().getDescription().contains("Scenario")){
                pw.write("@" + x.getKey());
                Arrays.stream(x.getFields().getLabels()).forEach(y -> pw.write(" @" + y));
                pw.write(" @Jira");
                pw.println();
                pw.write("#" + x.getFields().getSummary());
                pw.println();
                pw.write(x.getFields().getDescription().replace("\r\n\r\n", "\r\n"));
            }else{
                System.out.println("No Valid Scenario for test: "+x.getFields().getSummary() );
                pw.println();
                pw.write("# No Valid Scenario for test: "+ x.getKey()+ ":" + x.getFields().getSummary());
                pw.println();
            }
                pw.println();
            }
        );

        // close file instances
        pw.flush();
        pw.close();
        fw.close();
    }*/

    public void initTest() throws IOException, ConfigurationException {
            System.out.println("Initiating....");
            ProfileProperties.getInstance().load(ProfileProperties.GENERAL_PROFILE_DEFAULT);

            if(driver == null){
                if(ProfileProperties.getInstance().isProxyOn()){
                    System.out.println("Proxy is on.");
                    WebDriverManager.globalConfig().setProxy(getProperty(ProfileProperties.KEY_PROP_PROXY_ADDRESS));
                }

                ProfileProperties.getInstance().setDriverPath();

                String browser = ProfileProperties.getInstance().getBrowser().toLowerCase().trim();  //getProperty(ProfileProperties.KEY_PROP_BROWSER).toLowerCase().trim();
                switch (browser){
                    case "chrome":
                        if(ProfileProperties.getInstance().getBrowserVersion() == null){
                            WebDriverManager.chromedriver().setup();//version("76").setup();
                        }else{
                            WebDriverManager.chromedriver()
                                    .version(ProfileProperties.getInstance().getBrowserVersion())
                                    .setup();
                        }

                        ChromeOptions chromeOptions = new ChromeOptions();
                        if(ProfileProperties.getInstance().isHeadless()){
                            System.out.println("headless mode");
                            chromeOptions.addArguments("--headless");
                            chromeOptions.addArguments("--no-sandbox");
                            chromeOptions.addArguments("--disable-dev-shm-usage");
                            chromeOptions.addArguments("window-size=1920,1080");
                            if(ProfileProperties.getInstance().isProxyOn()){
                                Proxy proxy = new Proxy();
                                proxy.setHttpProxy(getProperty(ProfileProperties.KEY_PROP_PROXY_ADDRESS));
                                chromeOptions.setProxy(proxy);
                            }
                        }
                        chromeOptions.setAcceptInsecureCerts(true);
                        setDriver(new ChromeDriver(chromeOptions));
                        break;
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        setDriver(new FirefoxDriver(new FirefoxOptions().setAcceptInsecureCerts(true)));
                        break;
                    case "ie":
                        WebDriverManager.iedriver().setup();
                        setDriver(new InternetExplorerDriver(new InternetExplorerOptions()));
                        break;
                    case "edge":
                        WebDriverManager.edgedriver().setup();
                        setDriver(new EdgeDriver(new EdgeOptions()));
                        break;
                }
                if(getDriver()==null){
                    System.out.println("WebDriver is still null.");
                }else{
                    System.out.println("WebDriver is setup.");
                }
                getDriver().manage().window().maximize();
                getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(getProperty(ProfileProperties.KEY_PROP_PAGE_TIMEOUT)), TimeUnit.SECONDS );
                getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(getProperty(ProfileProperties.KEY_PROP_ELEMENT_TIMEOUT)), TimeUnit.SECONDS);
            }
            System.out.println("Finished Web Driver Initiation....");
            ProfileProperties.getInstance().load();

    }

    /**
     * This function will be invoked when debug run using JUnit
     *
     */
    /*@After("@UI")
    public void quitDriver(Scenario scenario){
        System.out.println("This is after hook of JUnit");
        if(driver != null){
            driver.quit();
        }
    }*/

    public <T> T getPage(Class<T> type){
        return PageFactory.initElements(driver, type);
    }

    public static StepCommon getInstance() {
        if (instance == null) {
            synchronized (StepCommon.class) {
                if (instance == null) {
                    instance = new StepCommon();
                }
            }
        }

        return instance;
    }

    @Before
    public void SetScenarioContext(Scenario scenario){
        System.out.println("Cucumber hook -- Before");
        // Scenario instance
        // Set Scenario Name
        ScenarioContext.getInstance().setScenarioContext(scenario.getName());
        /*ScenarioContext.getInstance().set("String", "Test String");
        ScenarioContext.getInstance().set("Array", new String[]{",","."});
        String t= ScenarioContext.getInstance().get("String");
        String[] c = ScenarioContext.getInstance().get("Array");*/
    }

    public void takeScreenShotDirByStepName(String stepName) throws IOException {
        TakesScreenshot takeScreenshots = (TakesScreenshot) getDriver();
        File scrFile = takeScreenshots.getScreenshotAs(OutputType.FILE);
        File DestFile=new File(getScreenShotDir()
                + File.separator
                + stepName
                //+ ProfileProperties.getInstance().getTimeStamp()
                + ProfileProperties.DEFAULT_SCREENSHOT_EXT
        );
        FileUtils.copyFile(scrFile, DestFile);
    }

    public void takeScreenShotDirByStepName(String caseName, String res) throws IOException {
        TakesScreenshot takeScreenshots = (TakesScreenshot) getDriver();
        File scrFile = takeScreenshots.getScreenshotAs(OutputType.FILE);
        File DestFile=new File(getScreenShotDir()
                + File.separator
                + res
                + File.separator
                + caseName
                //+ ProfileProperties.getInstance().getTimeStamp()
                + ProfileProperties.DEFAULT_SCREENSHOT_EXT
        );
        FileUtils.copyFile(scrFile, DestFile);
    }

    public Calendar parseAPIDateString(String date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        //simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        calendar.setTime(simpleDateFormat.parse(date.replaceAll("Z$", "+0000")));
        //calendar.setTime(simpleDateFormat.parse(date));
        return calendar;
    }

}
