package Utilities.cloud.rest;

import DataModel.CONST.*;
import Utilities.cloud.fw.ProfileProperties;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.output.WriterOutputStream;
import org.apache.commons.text.StringEscapeUtils;
import java.io.*;
import java.nio.charset.Charset;

/**
 * RestCall is a class for holding objects for making REST calls:
 * - String call name
 * - RequestSpecification object
 * - Response object
 * - PrintStream for log file output
 * The request and response objects are null until explicitly set by the caller.
 * The file output stream is null until the log file config is specified by the caller.
 */
public class RestCall {

    /**
     * The default name for REST calls.
     */
    public static final String DEFAULT_NAME = "main";

    /**
     * Log output path.
     */
    private String logOutputPath;

    /**
     * REST call name - used for logging and cache lookup.
     */
    private String name;

    /**
     * Rest Call Method
     */
    private String method;

    /**
     * Rest Call Endpoint
     */
    private String resource;

    /**
     * REST Assured request object.
     */
    private RequestSpecification request;

    /**
     * REST Assured response object.
     */
    private Response response;

    /**
     * Default constructor: uses DEFAULT_NAME as the name.
     */
    public RestCall(String logOutputPath) {
        this(logOutputPath, DEFAULT_NAME);
    }

    private StringWriter writer ;

    /**
     * Parametrized constructor.
     *
     * @param name String
     */
    public RestCall(String logOutputPath, String name) {
        this.logOutputPath = logOutputPath;
        this.name = name;
        this.request = null;
        this.response = null;
    }

    /**
     * Flushes and closes the log file output stream.
     * This method should be called when logging is complete.
     */
    private void closeLogFile(PrintStream stream) {
        stream.flush();
        stream.close();
    }

    /**
     * Logs the response object.
     * This should be called before making the request call.
     * It is assumed that the file output stream for logging is already in place (from setRequest).
     *
     * @throws RestCallLoggingException if the request object has not been set
     */
    private void logRequest(PrintStream stream) throws RestCallLoggingException {
        if (!hasRequest()) {
            throw new RestCallLoggingException(this, RestCallLoggingException.MISSING_REQUEST);
        }

        stream.println();
        stream.println("=== REQUEST ===");
        getRequest().log().all();
    }

    /**
     * Log the response object.
     * This should be called after making the request call.
     * It is assumed that the file output stream for logging is already in place (from setRequest).
     *
     * @throws RestCallLoggingException if the response object has not been set
     */
    private void logResponse(PrintStream stream) throws RestCallLoggingException {
        if (!hasResponse()) {
            throw new RestCallLoggingException(this, RestCallLoggingException.MISSING_RESPONSE);
        }

        stream.println();
        stream.println("=== RESPONSE ===");

        // The line below throws "[Fatal Error] :1:1: Premature end of file" when the response contains an empty body.
        // holder.response.then().log().all();

        // As a workaraound, print each response part separately.
        if (response.statusLine() != null) {
            response.then().log().status();
        }
        if (response.headers() != null) {
            response.then().log().headers();
        }
        if (response.cookies() != null) {
            response.then().log().cookies();
        }
        if (response.body() != null && !(response.body().asString().equals(""))) {
            response.then().log().body();
        }
        String logString = writer.toString();
        String htmlLog = StringEscapeUtils.escapeHtml4(logString);
        htmlLog = htmlLog.replace("\n","<br>");

        RestCallCache.getInstance().addCurrentStepCallLog(new APICallLog(this.method, htmlLog, this.resource ,getResponse().getStatusCode() ));
        RestCallCache.getInstance().addCurrentStepCallNumber();

    }

    /**
     * Prepare the file output stream for printing the log file.
     *
     * @return PrintStream
     * @throws IOException for any file problems
     */
    private PrintStream prepLogFile() throws IOException {
        // Create the output directory if necessary
        /*String dirPath = getLogOutputPath();
        File baseDir = new File(dirPath);
        if (!baseDir.exists()) {
            if (!baseDir.mkdirs()) {
                throw new IOException("Failed to create directory for RestCall logs: " + dirPath);
            }
        } else if (!baseDir.isDirectory()) {
            throw new IOException("Non-directory file already exists for RestCall logs directory: " + dirPath);
        }*/

        //String stepName = RestCallCache.getInstance().getCurrentStepName();

        //String path = dirPath + File.separator + "[" + this.method.toUpperCase() + "]"+ stepName + ".txt";

        //PrintStream stream = new PrintStream(path);
        writer = new StringWriter();
        PrintStream stream = new PrintStream(new WriterOutputStream(writer, Charset.defaultCharset()), true);

        getRequest().config(RestAssuredConfig.config().logConfig(LogConfig.logConfig().defaultStream(stream)));

        // Return the PrintStream reference
        return stream;
    }

    private PrintStream prepLogFile(String stepName) throws IOException {
        // Create the output directory if necessary
        String dirPath = getLogOutputPath();
        File baseDir = new File(dirPath);
        if (!baseDir.exists()) {
            if (!baseDir.mkdirs()) {
                throw new IOException("Failed to create directory for RestCall logs: " + dirPath);
            }
        } else if (!baseDir.isDirectory()) {
            throw new IOException("Non-directory file already exists for RestCall logs directory: " + dirPath);
        }

        String timestamp = ProfileProperties.getInstance().getTimeStamp();

        String fname = stepName.replaceAll("\\s+", " ").replaceAll("[^a-zA-Z0-9- ]", "");

        String path = dirPath + File.separator + fname + " " + timestamp + ".txt";

        PrintStream stream = new PrintStream(path);

        getRequest().config(RestAssuredConfig.config().logConfig(LogConfig.logConfig().defaultStream(stream)));

        // Return the PrintStream reference
        return stream;
    }

    /**
     * Accessor for the log output path.
     *
     * @return String
     */
    public String getLogOutputPath() {
        return logOutputPath;
    }

    public void setLogOutputPath(String logOutputPath) {
        this.logOutputPath = logOutputPath;
    }
    /**
     * Accessor for the name.
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor for the request.
     *
     * @return RequestSpecification
     */
    public RequestSpecification getRequest() {
        return request;
    }

    /**
     * Accessor for the response.
     *
     * @return Response
     */
    public Response getResponse() {
        return response;
    }

    /**
     * Checks if the call has a request object.
     *
     * @return boolean (true if not null, false otherwise)
     */
    public boolean hasRequest() {
        return request != null;
    }

    /**
     * Checks if the call has a response object.
     *
     * @return boolean (true if not null, false otherwise)
     */
    public boolean hasResponse() {
        return response != null;
    }

    /**
     * Makes a request call with logging.
     *
     * @param method            String
     * @param resource          String
     * @return Response
     * @throws IOException
     * @throws RestCallLoggingException for logging errors
     */
    public Response makeRequest(String method, String resource) throws IOException, RestCallLoggingException {
        return makeRequest(method, resource, false);
    }

    /**
     * Makes a request call with logging.
     *
     * @param method            String
     * @param resource          String
     * @param relaxedValidation if true then it'll trust all hosts regardless if the SSL certificate is invalid
     * @return Response
     * @throws IOException
     * @throws RestCallLoggingException for logging errors
     */
    public Response makeRequest(String method, String resource, boolean relaxedValidation)
            throws IOException, RestCallLoggingException {
        this.method = method;
        this.resource = resource;
        PrintStream stream = prepLogFile();
        logRequest(stream);

        if (relaxedValidation) {
            setResponse(getRequest().relaxedHTTPSValidation().request(method, resource));
        } else {
            setResponse(getRequest().request(method, resource));
        }

        logResponse(stream);
        closeLogFile(stream);

        return getResponse();
    }

    public Response makeRequest(String method, String resource,String stepName, boolean relaxedValidation)
            throws IOException, RestCallLoggingException {
        this.method = method;
        this.resource = resource;
        PrintStream stream = prepLogFile(stepName);
        logRequest(stream);

        if (relaxedValidation) {
            setResponse(getRequest().relaxedHTTPSValidation().request(method, resource));
        } else {
            setResponse(getRequest().request(method, resource));
        }
        logResponse(stream);
        closeLogFile(stream);

        return getResponse();
    }
 /*   *//**
     * Makes a request call with logging.
     * Store the request object as part of the call.
     *
     * @param request    RequestSpecification
     * @param method     String
     * @param resource   String
     * @return Response
     * @throws IOException              for logging errors
     * @throws RestCallLoggingException for logging errors
     *//*
    public Response makeRequest(String request, ResourcePaths method, boolean resource)
            throws IOException, RestCallLoggingException {
        setRequest(request);
        return makeRequest(method, resource);
    }*/

    /**
     * Modifier for the request.
     *
     * @param request RequestSpecification
     */
    public void setRequest(RequestSpecification request) {
        this.request = request;
    }

    /**
     * Modifier for the response.
     *
     * @param response Response
     */
    public void setResponse(Response response) {
        this.response = response;
    }


}