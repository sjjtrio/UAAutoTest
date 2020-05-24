package Utilities.cloud.rest;

import java.util.HashMap;

public class AuthenticationTokensCache {

    private static volatile AuthenticationTokensCache instance = null;

    private String currentUser;
    /**
     * Class accessor for the singleton instance.
     *
     * @return AuthenticationTokensCache
     */
    public static AuthenticationTokensCache getInstance() {
        if (instance == null) {
            synchronized (AuthenticationTokensCache.class) {
                if (instance == null) {
                    instance = new AuthenticationTokensCache();
                }
            }
        }
        return instance;
    }

    /**
     * The collection of generated authentication tokens.
     * Each user key / SSO user key combo has its own set of tokens.
     * This allows scenarios to use multiple different users / tokens.
     * The singleton instance acts as a holder for these pairs.
     */
    private HashMap<String, AuthenticationTokens> tokens;

    /**
     * Default constructor: create an empty set of tokens.
     */
    private AuthenticationTokensCache() {
        tokens = new HashMap<String, AuthenticationTokens>();
    }

    public void addToken(String keyName, AuthenticationTokens token){
        tokens.put(keyName, token);
        currentUser = keyName;
    }

    /**
     * get Token for current user
     * @return Token String
     */
    public AuthenticationTokens getToken(){
        return tokens.get(currentUser);
    }

    /**
     * Get token by user name
     * @param userName the user name
     * @return Token String
     */
    public AuthenticationTokens getToken(String userName){
        return tokens.get(userName);
    }

}
