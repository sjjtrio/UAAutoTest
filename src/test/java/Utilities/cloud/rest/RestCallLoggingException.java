package Utilities.cloud.rest;

/**
 * RestCallLoggingException is an Exception to be thrown for RestCall logging problems.
 */
public class RestCallLoggingException extends Exception {

    /**
     * Constant for missing requests.
     */
    public static final String MISSING_REQUEST = "request";

    /**
     * Constant for missing responses.
     */
    public static final String MISSING_RESPONSE = "response";

    /**
     * The REST call object.
     */
    private RestCall call;

    /**
     * The missing part - either "request" or "response".
     */
    private String missingPart;

    /**
     * Constructor.
     *
     * @param call RestCall
     * @param missingPart String
     */
    public RestCallLoggingException(RestCall call, String missingPart) {
        super("RestCall '" + call.getName() + "' cannot log a missing " + missingPart);
        this.call = call;
        this.missingPart = missingPart;
    }

    /**
     * Accessor for the REST call object.
     *
     * @return RestCall
     */
    public RestCall getCall() {
        return call;
    }

    /**
     * Accessor for the missing part.
     *
     * @return String
     */
    public String getMissingPart() {
        return missingPart;
    }

}