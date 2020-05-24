package Utilities.cloud.rest;


import java.util.Calendar;

/**
 * AuthenticationTokens holds the set of authentication tokens for REST API testing.
 */
public class AuthenticationTokens {

    /**
     * Application token.
     */
    private String application;

    /**
     * The timestamp when this token was fetched.
     */
    private Calendar fetchedTime;

    /**
     * Session token.
     */
    private String session;

    /**
     * SSO token.
     */
    private String sso;

    /**
     * UserPermId key.
     */
    private String userPermId;

    /**
     * Auth token
     */
    private String token;
    /**
     * Constructor.
     *
     * @param application String
     * @param sso         String
     * @param session     String
     * @param fetchedTime Calendar
     */
    public AuthenticationTokens(String application, String sso, String session, String userPermId, Calendar fetchedTime) {
        this.application = application;
        this.sso = sso;
        this.session = session;
        this.userPermId = userPermId;
        this.fetchedTime = fetchedTime;
    }

    public AuthenticationTokens(String token){
        this.token = token;
    }

    /**
     * Accessor for the Auth Token
     */
    public String getToken(){
        return token;
    }

    /**
     * Accessor for the application token.
     *
     * @return String
     */
    public String getApplication() {
        return application;
    }

    /**
     * Accessor for the fetched time.
     *
     * @return Calendar
     */
    public Calendar getFetchedTime() {
        return fetchedTime;
    }

    /**
     * Accessor for the session token.
     *
     * @return String
     */
    public String getSession() {
        return session;
    }

    /**
     * Accessor for the SSO token.
     *
     * @return String
     */
    public String getSso() {
        return sso;
    }

    /**
     * Accessor for the UserPermId.
     *
     * @return String
     */
    public String getUserPermId() {
        return userPermId;
    }

    /**
     * Calculates the amount of time since this token was fetched.
     * This value is used to determine if a token must be regenerated.
     *
     * @return long time in milliseconds
     */
    public long getTimeSinceFetched() {
        long end = Calendar.getInstance().getTimeInMillis();
        long start = getFetchedTime().getTimeInMillis();
        return (end - start);
    }

}