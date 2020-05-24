package Utilities.cloud.fw;


import cucumber.api.Scenario;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

/**
 * ProfileProperties is a singleton class that reads profile properties files.
 * Every test suite launch will run against one profile (or "environment").
 * Properties files are located in the resources directory under the "profiles" subdirectory.
 * The properties file must be explicitly loaded using "load".
 * This is done outside of the constructor so that singleton calls won't throw IOException.
 */
public class ProfileProperties {

    /**
     * Default property value.
     * The amount of time in milliseconds before a token expires.
     * The value equates to 23 hours.
     */
    private static final String DEFAULT_AUTH_EXPIRATION = "82800000";

    /**
     * Default property value.
     * The maximum number of times to attempt token generation.
     */
    private static final String DEFAULT_AUTH_RETRY_MAX = "5";

    /**
     * Default property value.
     * The amount of time in milliseconds to wait between token generation attempts.
     */
    private static final String DEFAULT_AUTH_RETRY_WAIT = "5000";

    public static final String KEY_PROP_RANDOM_PREFIX = "TS_AT_";

    /**
     * Default property value.
     */
    private static final String DEFAULT_BROWSER_NAME = "Chrome";
    private static final String DEFAULT_LOG_OUTPUT_DIR = "target/rest-logs";
    private static final String DEFAULT_REPORT_OUTPUT_DIR = "target/reports";
    private static final String DEFAULT_CSS_FILE_PATH = "target/test-classes/CSS";
    private static final String DEFAULT_CSS_FILE_NAME ="style.css";
    private static final String DEFAULT_UI_SCREEN_OUTPUT_DIR = "target/ui-screens";
    public static final String DEFAULT_SCREENSHOT_EXT = ".png";
    private static final String DEFAULT_HEADLESS_SWITCH = "Off";
    private static final String DEFAULT_PROXY_SWITCH = "Off";
    private static final String DEFAULT_DRIVER_PATH = "target/drivers";
    private static final String DEFAULT_BUILD_NUMBER = "default";
    public static final String DEFAULT_POLICY_FILE_PATH = "target/test-classes/JsonFile/policy_ali.json";
    public static final String DEFAULT_POLICY_MAPPING_FILE_PATH = "target/test-classes/JsonFile/mapping.json";

    /**
     * Profile properties key constant: authorization expiration in milliseconds.
     */
    public static final String KEY_PROP_AUTH_EXPIRATION = "auth.expiration";

    /**
     * Profile properties key constant: max number of authentication retries.
     */
    public static final String KEY_PROP_AUTH_RETRY_MAX = "auth.retry.max";

    /**
     * Profile properties key constant: milliseconds to wait between authentication attempts.
     */
    public static final String KEY_PROP_AUTH_RETRY_WAIT = "auth.retry.wait";

    /**
     * Profile properties key constant: output directory for response text files.
     */
    public static final String KEY_PROP_LOG_OUTPUT_DIR = "log.output.dir";

    public static final String KEY_PROP_REPORT_OUTPUT_DIR = "report.output.dir";

    /**
     * Profile properties key constant: service host.
     */
    public static final String KEY_PROP_SERVICEHOST = "serviceHost";

    /**
     * Profile properties key constant: password.
     */
    public static final String KEY_PROP_PASSWORD = "Password";

    /**
     * Profile properties key constant: Email.
     */
    public static final String KEY_PROP_EMAIL = "Email";
    /**
     * Profile properties key constant: username.
     */
    public static final String KEY_PROP_USERNAME = "Username";

    public static final String KEY_PROP_NON_PROVIDER_ORG = "CustomerOrg";

    /**
     * Profile properties key constant: name of role created by automation
     */
    public static final String KEY_PROP_AT_ROLE = "AutomationRole";
    /**
     * Profile properties key constant: service host, port and basepath
     */
    public static final String KEY_PROP_APISERVICEHOST = "APIServiceHost";
    public static final String KEY_PROP_APISERVICEHOSTPORT = "APIServiceHostPort";
    public static final String KEY_PROP_APISERVICEBASEPATH = "APIServiceHostBasePath";
    public static final String V2KEY_PROP_APISERVICEHOST = "V2APIServiceHost";
    public static final String V2KEY_PROP_APISERVICEHOSTPORT = "V2APIServiceHostPort";
    public static final String V2KEY_PROP_APISERVICEBASEPATH = "V2APIServiceHostBasePath";
    public static final String KEY_PROP_JIRACOOKIEHEADER = "JiraCookieHeader";
    public static final String KEY_PROP_JIRAPROXY_SWITCH = "JiraProxySwitch";
    public static final String KEY_PROP_JIRA_API_HOST = "JiraAPIHost";
    public static final String KEY_PROP_JIRA_API_END_POINT = "JiraAPIEndPoint";
    public static final String KEY_PROP_JIRA_PROXY = "JiraProxy";
    public static final String KEY_PROP_JIRA_PROXY_PORT = "JiraProxyPort";
    public static final String KEY_PROP_JIRA_API_ISSUE_KEY = "JiraIssueKey";
    public static final String KEY_PROP_JIRA_API_JQL = "JiraSearchJQL";
    public static final String KEY_PROP_JIRA_FEATURE_FILE_PATH = "src//test//resources//Features//Jira.feature";

    /**
     * Profile properties key constant: API username and API password
     */
    public static final String KEY_PROP_API_USERNAME = "APIAuthUserName";
    public static final String KEY_PROP_API_PASSWORD = "APIAuthPSW";
    public static final String KEY_PROP_API_DOMAIN = "APIDomain";
    public static final String KEY_PROP_API_PROJECT = "APIProject";
    public static final String KEY_PROP_API_DEFAULT_ROLE = "admin";
    public static final String KEY_PROP_API_CUSTOMER_ROLE = "customer";
    public static final String KEY_PROP_API_CUSTOMERID = "CustomerID";
    public static final String KEY_PROP_API_USERID = "UserID";
    public static final String KEY_PROP_API_APP_SERVER_NUMBER = "App.Server.Number";
    public static final String KEY_PROP_API_REGION_NAME = "Endpoint.Region.Name";
    public static final String KEY_PROP_API_LOCATION_ID ="ServiceInfo_Test_Location_Id";
    public static final String KEY_PROP_API_LOCATION_NAME ="ServiceInfo_Test_Location";
    public static final String KEY_PROP_API_OFFERING_ID ="ServiceInfo_Test_Offering_Id";
    public static final String KEY_PROP_API_OFFERING_NAME ="ServiceInfo_Test_Offering";
    public static final String KEY_PROP_API_User_ID ="ServiceInfo_Test_User_Id";
    public static final String KEY_PROP_API_User_NAME ="ServiceInfo_Test_User";
    public static final String KEY_PROP_API_Customer_ID ="ServiceInfo_Test_Customer_Id";
    public static final String KEY_PROP_API_Customer_NAME ="ServiceInfo_Test_Customer";
    public static final String KEY_PROP_API_Department_ID ="ServiceInfo_Test_Department_Id";
    public static final String KEY_PROP_API_Department_NAME ="ServiceInfo_Test_Department";
    public static final String KEY_PROP_API_Resource_ID ="ServiceInfo_Test_Resource_Id";
    public static final String KEY_PROP_API_Resource_NAME ="ServiceInfo_Test_Resource";
    public static final String KEY_PROP_API_VDC_ID ="ServiceInfo_Test_VDC_Id";
    public static final String KEY_PROP_API_VDC_NAME ="ServiceInfo_Test_VDC";
    public static final String KEY_PROP_API_Vendor_ID ="ServiceInfo_Test_Vendor_Id";
    public static final String KEY_PROP_API_Vendor_NAME ="ServiceInfo_Test_Vendor";
    public static final String KEY_PROP_API_QRS_ID ="ServiceInfo_Test_QRS_Id";

    /**
     * Browser & proxy settings
     */
    public static final String KEY_PROP_BROWSER = "Browser";

    public static final String KEY_PROP_PROXY_SWITCH = "ProxySwitch";

    public static final String KEY_PROP_PROXY_ADDRESS= "ProxyAddress";

    public static final String KEY_PROP_PAGE_TIMEOUT = "PageTimeOut";

    public static final String KEY_PROP_ELEMENT_TIMEOUT = "ElementTimeOut";

    public static final String KEY_PROP_WAIT_TIMEOUT = "WaitTimeOut";
    /**
     * System properties key constant: profile name to use for testing.
     * System properties key constant: browser name to use for testing.
     */
    private static final String KEY_SYSPROP_PROFILE = "profile";
    private static final String KEY_SYSPROP_BROWSER = "browser";
    private static final String KEY_SYSPROP_HEADLESS = "isHeadLess";
    private static final String KEY_SYSPROP_PROXY = "proxy";
    private static final String KEY_SYSPROP_DRIVERPATH = "driverPath";
    private static final String KEY_SYSPROP_BUILDNUMBER = "buildNumber";
    private static final String KEY_SYSPROP_BROWSERVERSION = "browserVersion";
    private static final String KEY_SYSPROP_OPENTUNNEL = "openTunnel";
    private static final String KEY_SYSPROP_JUMPADDR = "jumpAddress";
    private static final String KEY_SYSPROP_JUMPPORT = "jumpPort";
    private static final String KEY_SYSPROP_REMOTEADDR = "remoteAddress";
    private static final String KEY_SYSPROP_REMOTEPORT = "remotePort";
    private static final String KEY_SYSPROP_JUMPUSER= "jumpUser";
    private static final String KEY_SYSPROP_JUMPPSW = "jumpPSW";
    /**
     * Profile name: default.
     * The default profile is always read first.
     * The selected profile will overwrite default settings.
     */
    private static final String PROFILE_DEFAULT = "FT";

    /**
     * General settings profile name
     */
    public static final String GENERAL_PROFILE_DEFAULT = "General";

    /**
     * Profile name: local.
     * The local default is used if no profile is provided as a system property.
     */
    private static final String PROFILE_LOCAL = "FT";

    /**
     * Singleton instance.
     */
    private static volatile ProfileProperties instance = null;

    /**
     * Concatenate the password key for a user.
     *
     * @param orgName String
     * @return String
     */
    public static String concatPasswordKey(String orgName) {
        return orgName + "." + KEY_PROP_PASSWORD;
    }

    public static String concatPasswordKey(String orgName, String userName){
        return orgName + "." + userName + "." + KEY_PROP_PASSWORD;
    }
    public static String concatEmailKey(String orgName, String userName){
        return orgName + "." + userName + "." + KEY_PROP_EMAIL;
    }
    public static String concatPasswordKeyAPI(String userRole){
        return userRole + "." + KEY_PROP_API_PASSWORD;
    }
    public static String concatAPICustomerID(String userRole) {return userRole + "." + KEY_PROP_API_CUSTOMERID;}
    public static String concatAPICustomerID() {return "vpc_customer" + "." + KEY_PROP_API_CUSTOMERID;}
    public static String concatAPIUserID(String userRole) {return userRole + "." + KEY_PROP_API_USERID;}
    public static String concatAPIUserID() {return "vpc_customer" + "." + KEY_PROP_API_USERID;}



    /**
     * Concatenate the properties file path for a profile.
     *
     * @param profile String
     * @return String
     */
    private static String concatPropertiesFilePath(String profile) {
        String path = System.getProperty("user.dir") + File.separator + "src"+ File.separator+"test"+ File.separator+"resources"+ File.separator+"profiles" +  File.separator + profile + ".properties";
        System.out.println("path: " + path);
        return path;
    }

    /**
     * Concatenate the properties file path for test profile.
     *
     * @param profile
     * @param service
     * @return
     */
    private String concatServicePropertiesFilePath(String profile, String service) {
        return "testprofiles/" + service + "/" + profile + ".properties";
    }

    private String concatServicePropertiesFilePath(String profile) {
        return System.getProperty("user.dir") + "\\src\\test\\resources\\profiles\\" + profile + ".properties";
    }

    private String concatWebPropertiesFilePath() {
        return "webprofiles/TestConfig.properties";
    }

    /**
     * Concatenate the username key for a user.
     *
     * @param orgName String
     * @return String
     */
    public static String concatUsernameKey(String orgName) {
        return orgName + "." + KEY_PROP_USERNAME;
    }

    public static String concatUsernameKey(String orgName, String userRole){
        return orgName + "." +userRole + "." + KEY_PROP_USERNAME;
    }

    public static String concatUserNameKeyAPI(String userRole){
        return userRole + "." + KEY_PROP_API_USERNAME;
    }

    public static String concatProjectNameKeyAPI(String userRole){
        return userRole + "." + KEY_PROP_API_PROJECT;
    }

    public static String concatDomainIDKeyAPI(String userName){
        return userName + "." + KEY_PROP_API_DOMAIN;
    }

    public static String concatDCNameAPI(String userRole){
        return userRole+".DCName";
    }

    public static String concatTenantIDAPI(String userProfile){
        return userProfile + ".TenantID";
    }
    public static String concatOrgIDKey(String orgProfile) { return orgProfile + ".Org.Key";}
    public static String concatOrgNameKey(String orgProfile) { return orgProfile + ".Org.Display";}
    public static String concatTokenKey(String extra){ return "Token_" + extra +"_Extra_ID";}
    public static String concatTokenExpire(String extra){ return "Token_" + extra +"_Extra_Expire";}


    /**
     * Class accessor for the singleton instance.
     *
     * @return ProfileProperties
     */
    public static ProfileProperties getInstance() {
        if (instance == null) {
            synchronized (ProfileProperties.class) {
                if (instance == null) {
                    instance = new ProfileProperties();
                }
            }
        }

        return instance;
    }

    /**
     * Chosen profile.
     */
    private String profile;

    /**
     * Chosen browser.
     */
    private String browser;
    public String getBrowser(){
        return browser;
    }
    /**
     *  Chosen headless option
     * @return
     */
    private String headless;
    public String getHeadless(){
        return headless;
    }
    public boolean isHeadless(){
        return headless.toLowerCase().equals("on");
    }

    /**
     *  Chosen Proxy switch
     */
    private String proxySwitch;
    public String getProxySwitch(){
        return proxySwitch;
    }
    public boolean isProxyOn(){
        return proxySwitch.toLowerCase().equals("on");
    }

    /**
     *  Chosen webdriver path
     */
    private String driverPath;
    public String getDriverPath(){return driverPath;}
    public void setDriverPath(){
        System.setProperty("wdm.targetPath", getDriverPath());
    }

    private String browserVersion;
    public String getBrowserVersion(){return browserVersion;}

    private String buildNumber;
    public String getBuildNumber(){
        return buildNumber;
    }

    private String openTunnel;
    public String getOpenTunnel(){
        return openTunnel;
    }

    private String jumperAddress;
    public String getJumperAddress(){
        return jumperAddress;
    }

    private int jumpPort;
    public int getJumpPort(){
        return jumpPort;
    }

    private String remoteAddress;
    public String getRemoteAddress(){
        return remoteAddress;
    }

    private int remotePort;
    public int getRemotePort(){
        return remotePort;
    }

    private String jumperUser;
    public String getJumperUser(){
        return jumperUser;
    }

    private String jumperPsw;
    public String getJumperPsw(){
        return jumperPsw;
    }

    /**
     * Loaded properties.
     */
    private Properties properties;
    private FileBasedConfigurationBuilder<FileBasedConfiguration> builder;

    /**
     * Private default constructor.
     * The properties are NOT loaded by this constructor.
     */
    private ProfileProperties() {
        // Read the system property for profile (using "local" as default)
        profile = System.getProperty(KEY_SYSPROP_PROFILE, PROFILE_LOCAL);
        browser = System.getProperty(KEY_SYSPROP_BROWSER, DEFAULT_BROWSER_NAME);
        headless = System.getProperty(KEY_SYSPROP_HEADLESS, DEFAULT_HEADLESS_SWITCH);
        proxySwitch = System.getProperty(KEY_SYSPROP_PROXY, DEFAULT_PROXY_SWITCH);
        driverPath = System.getProperty(KEY_SYSPROP_DRIVERPATH, DEFAULT_DRIVER_PATH);
        buildNumber = System.getProperty(KEY_SYSPROP_BUILDNUMBER, DEFAULT_BUILD_NUMBER) == null
                ? "default"
                : System.getProperty(KEY_SYSPROP_BUILDNUMBER, DEFAULT_BUILD_NUMBER);

        browserVersion = System.getProperty(KEY_SYSPROP_BROWSERVERSION);

        openTunnel = System.getProperty(KEY_SYSPROP_OPENTUNNEL);
        jumperAddress = System.getProperty(KEY_SYSPROP_JUMPADDR);
        jumpPort = Integer.parseInt(System.getProperty(KEY_SYSPROP_JUMPPORT,"35357"));
        remoteAddress = System.getProperty(KEY_SYSPROP_REMOTEADDR);
        remotePort = Integer.parseInt(System.getProperty(KEY_SYSPROP_REMOTEPORT,"35357"));
        jumperUser = System.getProperty(KEY_SYSPROP_JUMPUSER);
        jumperPsw = System.getProperty(KEY_SYSPROP_JUMPPSW);

        System.out.println("Profile name: " + profile + ", Browser: " + browser + ", " + "Headless is " + headless + ", Proxy is " + proxySwitch +", Drivers save to " + getDriverPath());
        // Construct empty properties
        properties = new Properties();

    }

    /**
     * Load a profile's properties file into the properties object.
     *
     * @param properties Properties
     * @param profile    String
     * @throws IOException if the properties files are not found
     */
    private void loadProfile(Properties properties, String profile) throws IOException, ConfigurationException {
        String path = concatPropertiesFilePath(profile);
        System.out.println("Try to load Profile Path "+ path);
        properties.load(ResourceReader.getInputStream(path));
        Parameters params = new Parameters();
        builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(params.properties()
                        .setFileName(path));
    }

    /**
     * Load Test profiles
     *
     * @param properties
     * @param profile
     * @throws IOException
     */
    private void loadTestProfile(Properties properties, String profile) throws IOException, ConfigurationException {
        loadProfile(properties, profile);
    }

    /**
     * Load webTest profiles
     *
     * @param properties
     * @throws IOException
     */
    private void loadWebTestProfile(Properties properties) throws IOException {
        String path = concatWebPropertiesFilePath();
        properties.load(ResourceReader.getInputStream(path));
    }

    /**
     * Property accessor.
     *
     * @return String
     */
    public long getAuthExpiration() {
        String value = getProperties().getProperty(KEY_PROP_AUTH_EXPIRATION, DEFAULT_AUTH_EXPIRATION);
        return Long.parseLong(value);
    }

    /**
     * Property accessor.
     *
     * @return String
     */
    public int getAuthRetryMax() {
        String value = getProperties().getProperty(KEY_PROP_AUTH_RETRY_MAX, DEFAULT_AUTH_RETRY_MAX);
        return Integer.parseInt(value);
    }

    /**
     * Property accessor.
     *
     * @return String
     */
    public long getAuthRetryWait() {
        String value = getProperties().getProperty(KEY_PROP_AUTH_RETRY_WAIT, DEFAULT_AUTH_RETRY_WAIT);
        return Long.parseLong(value);
    }

    /**
     * Gets the password for a user key.
     *
     * @param userKey String
     * @return String
     */
    public String getPassword(String userKey) {
        String key = concatPasswordKey(userKey);
        return getProperties().getProperty(key);
    }

    /**
     * Accessor for the chosen profile.
     *
     * @return String
     */
    public String getProfile() {
        return profile;
    }

    /**
     * Accessor for the properties object.
     *
     * @return Properties
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * Property accessor.
     *
     * @return String
     */
    public String getLogOutputDir() {
        return getProperties().getProperty(KEY_PROP_LOG_OUTPUT_DIR, DEFAULT_LOG_OUTPUT_DIR);
    }

    public String getDefaultReportOutputDir(){
        return getProperties().getProperty(KEY_PROP_REPORT_OUTPUT_DIR, DEFAULT_REPORT_OUTPUT_DIR);
    }


    public String getLogOutputDir(String timeStamp){
        return getProperties().getProperty(KEY_PROP_LOG_OUTPUT_DIR, DEFAULT_LOG_OUTPUT_DIR)
                + File.separator + timeStamp;
    }

    public String getDefaultUiScreenOutputDir(){
        return getProperties().getProperty("", DEFAULT_UI_SCREEN_OUTPUT_DIR);
    }

    /**
     * Gets the log output directory path for a given scenario name.
     *
     * @param scenario Scenario
     * @return String
     */
    public String getLogOutputPath(Scenario scenario) {
        String[] parts = scenario.getId().split(".feature:");
        String feature = parts[0].trim().toLowerCase().
                replaceAll("[^0-9a-z]+", "-").
                replaceFirst("src-test-resources-features-", "");
        String name = scenario.getName().trim().toLowerCase().
                replaceAll("\\s+", "-");
        return getLogOutputDir() + File.separator + feature + File.separator + name + "-" + parts[1];
    }

    public String getLogOutputPathAuth(String token1, String token2, int iter) {
        return getLogOutputPathAuth(token1,token2) + File.separator +"Iteration " + iter;
    }

    public String getReportPath(String timeStamp){
        return getDefaultReportOutputDir() + File.separator + timeStamp;
    }

    public String getCssPath(){
        return DEFAULT_CSS_FILE_PATH+ File.separator + DEFAULT_CSS_FILE_NAME;
    }

    public String getCssPath(String folderPath){
        return folderPath+ File.separator + DEFAULT_CSS_FILE_NAME;
    }

    public String getLogOutputPathAuth(String timeStamp, String token1, String token2, int iter) {

        return getLogOutputPathAuth(timeStamp, token1,token2) + File.separator +"Iteration " + iter;
    }

    public String getLogOutputPathAuth(String token1, String token2){
        return getLogOutputDir() + File.separator + token1 + File.separator + token2;
    }

    public String getLogOutputPathAuth(String timeStamp, String token1, String token2){
        return getLogOutputDir(timeStamp) + File.separator + token1 + File.separator + token2;
    }

    public String getLogOutputPathArr(String[] res){
        String path = String.join(File.separator, res);
        return getLogOutputDir() + File.separator + path;
    }

    public String getTimeStamp(){
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSSSSS").format(new Date());
        return timestamp;
    }
    /**
     * Property accessor.
     *
     * @return String
     */
    public String getServiceHost() {
        return getProperties().getProperty(KEY_PROP_SERVICEHOST);
    }

    /**
     * Gets the username for a user key.
     *
     * @param userKey String
     * @return String
     */
    public String getUsername(String userKey) {
        String key = concatUsernameKey(userKey);
        return getProperties().getProperty(key);
    }

    /**
     * Load all properties.
     *
     * @throws IOException if the properties files are not found
     */
    public void load() throws IOException, ConfigurationException {
        // TODO: handle IOException for when files are missing
        loadProfile(properties, PROFILE_DEFAULT);
        loadProfile(properties, getProfile());
    }

    /**
     * Load all test profile properties
     *
     * @throws IOException
     */
    public void load(String profileFile) throws IOException, ConfigurationException {
        loadTestProfile(properties, profileFile);
    }

    /**
     * Load all test config properties
     *
     * @throws IOException
     */
    public void loadWeb() throws IOException {
        loadWebTestProfile(properties);
    }

    public void setPropValue(String key, String value) throws ConfigurationException {
        System.out.println("Set " + key + "from " +builder.getConfiguration().getString(key)+ " to value: " + value);
        builder.getConfiguration().setProperty(key,value);
        builder.save();
    }


}