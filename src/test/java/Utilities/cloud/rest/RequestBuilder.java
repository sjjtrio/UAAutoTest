package Utilities.cloud.rest;

import Utilities.cloud.fw.ProfileProperties;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static io.restassured.config.HeaderConfig.headerConfig;

/**
 * RequestBuilder is a helper class for creating request objects.
 * Its static methods add common things like headers to request objects.
 */
public class RequestBuilder {

    // ------------------------------
    // INSTANCE VARIABLES
    // ------------------------------

    public static AuthenticationTokens tokens;

    /**
     * Header key constant.
     */
    public static final String KEY_ACCEPT = "Accept";
    public static final String KEY_ACCEPT_ENCODING = "Accept-Encoding";
    public static final String KEY_CONNECTION = "Connection";
    public static final String KEY_CONTENT_TYPE = "Content-Type";
    public static final String KEY_XLN_APPLICATION = "X-LN-Application";
    public static final String KEY_XLN_REQUEST = "X-LN-Request";
    public static final String KEY_XLN_SESSION = "X-LN-Session";

    public static final String AUTH_TOKEN_HEADER_NAME_REQUEST = "X-Auth-Token";
    public static final String AUTH_TOKEN_HEADER_NAME_REQUEST_VPC = "X-Auth-Token";
    public static final String AUTH_TOKEN_HEADER_NAME_REQUEST_NGP = "X-NGP-Token";
    public static final String AUTH_TOKEN_HEADER_NAME= "X-Subject-Token";
    public static final String AUTH_TOKEN_DC_HEADER_NAME= "X-Auth-Data-Center";
    public static final String AUTH_TOKEN_TYPE_HEADER_NAME = "X-Auth-Token-Type";
    public static final String JIRA_COOKIE_HEADER = "Cookie";

    /**
     * XML body tag constant: contextual permanent ID.
     */
    public static final String TAG_CONTEXTUAL_FEATURE_PERM_ID = "contextualFeaturePermID";

    /**
     * XML body tag constant: feature permanent ID.
     */
    public static final String TAG_FEATURE_PERM_ID = "featurePermID";

    /**
     * XML body tag constant: SSO password.
     */
    public static final String TAG_PASSWORD = "password";

    /**
     * XML body tag constant: SSO cookie value.
     */
    public static final String TAG_SSO_COOKIE_VALUE = "ssoCookieValue";

    /**
     * XML body tag constant: feature permanent ID.
     */
    public static final String TAG_TRANSACTION_ID = "transactionID";

    /**
     * XML body tag constant: SSO username.
     */
    public static final String TAG_USERID = "userId";

    /**
     * Header value.
     */
    public static final String VALUE_APP_JSON = "application/json";
    public static final String VALUE_TOKEN_TYPE = "Keystone";

    /**
     * Set host, port and base path properties.
     */
    public static String ServiceHost = "";
    public static String ServicePort = "";
    public static String BasePath = "";


    public static Headers defaultHeaders(){
        return Headers.headers(new Header(KEY_ACCEPT,VALUE_APP_JSON), new Header(KEY_CONTENT_TYPE,"application/json"));
    }

    public static Headers defaultV2Headers(String authToken , String dataCenter){
        return Headers.headers(new Header(KEY_CONTENT_TYPE, VALUE_APP_JSON),
                new Header(AUTH_TOKEN_TYPE_HEADER_NAME, VALUE_TOKEN_TYPE),
                new Header(AUTH_TOKEN_DC_HEADER_NAME, dataCenter),
                new Header(AUTH_TOKEN_HEADER_NAME_REQUEST, authToken));
    }

    public static Headers defaultV2Headers(String userProfile){
        return Headers.headers(new Header(KEY_CONTENT_TYPE, VALUE_APP_JSON),
                new Header(AUTH_TOKEN_TYPE_HEADER_NAME, VALUE_TOKEN_TYPE),
                new Header(AUTH_TOKEN_DC_HEADER_NAME
                        , ProfileProperties.getInstance().getProperties().getProperty(ProfileProperties.concatDCNameAPI(userProfile))),
                new Header(AUTH_TOKEN_HEADER_NAME_REQUEST
                        , AuthenticationTokensCache.getInstance().getToken(userProfile).getToken()));
    }

    public static RequestSpecification defaultRequestSpec(){
        RequestSpecification specification = given().baseUri(ServiceHost);
        if (Integer.parseInt(ServicePort) > 0) {
            specification = specification.port(Integer.parseInt(ServicePort));
        }
        if(!BasePath.equals("")){
            specification = specification.basePath(BasePath);
        }

        return specification.headers(defaultHeaders()).relaxedHTTPSValidation();
    }


    public static RequestSpecification defaultRequestSpecWithAuthToken(AuthenticationTokens token){

        return defaultRequestSpec()
                .config(config()
                        .headerConfig(headerConfig().overwriteHeadersWithName(AUTH_TOKEN_HEADER_NAME_REQUEST)))
                .header(AUTH_TOKEN_HEADER_NAME_REQUEST,token.getToken());
    }

    public static RequestSpecification defaultRequestSpecWithAuthToken(AuthenticationTokens authToken, AuthenticationTokens validateToken)
    {
        return defaultRequestSpec()
                .config(config()
                        .headerConfig(headerConfig().overwriteHeadersWithName(AUTH_TOKEN_HEADER_NAME_REQUEST, AUTH_TOKEN_HEADER_NAME)))
                .header(AUTH_TOKEN_HEADER_NAME_REQUEST,authToken.getToken())
                .header(AUTH_TOKEN_HEADER_NAME, validateToken.getToken());
    }

    public static RequestSpecification defaultRequestSpecWithAuthToken(String key){
        return defaultRequestSpec()
                .config(config().headerConfig(headerConfig()
                        .overwriteHeadersWithName(AUTH_TOKEN_HEADER_NAME_REQUEST)))
                .header(AUTH_TOKEN_HEADER_NAME_REQUEST
                        , AuthenticationTokensCache.getInstance().getToken(key).getToken());
    }

    public static void loadRequestSpecs(String service_name){
        ServiceHost = ProfileProperties.getInstance().getProperties().getProperty(service_name+ ".ServiceHost");
        ServicePort = ProfileProperties.getInstance().getProperties().getProperty(service_name+ ".ServicePort");
        BasePath = ProfileProperties.getInstance().getProperties().getProperty(service_name+ ".BasePath");
    }

}