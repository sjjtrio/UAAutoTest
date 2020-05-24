package Utilities.cloud.rest;


/**
 * UserKeys holds three user keys: general user, SSO user, and region.
 * The region defaults to the default blank value.
 */
public class UserKeys {

    /**
     * General user key.
     */
    private String generalUser;

    /**
     * SSO user key.
     */
    private String ssoUser;

    /**
     * Region enumeration value.
     */
    private ENV region;

    /**
     * Constructor for a blank region.
     *
     * @param generalUser String
     * @param ssoUser     String
     */
    public UserKeys(String generalUser, String ssoUser) {
        this(generalUser, ssoUser, ENV.FT);
    }

    /**
     * Full constructor with string region.
     *
     * @param generalUser String
     * @param ssoUser     String
     * @param region      String
     */
    public UserKeys(String generalUser, String ssoUser, String region) {
        this.generalUser = generalUser;
        this.ssoUser = ssoUser;
        this.region = ENV.valueOf(region);
    }

    /**
     * Full constructor.
     *
     * @param generalUser String
     * @param ssoUser     String
     * @param region      Products
     */
    public UserKeys(String generalUser, String ssoUser, ENV region) {
        this.generalUser = generalUser;
        this.ssoUser = ssoUser;
        this.region = region;
    }

    /**
     * Accessor for the general user.
     *
     * @return String
     */
    public String getGeneralUser() {
        return generalUser;
    }

    /**
     * Accessor for the SSO user.
     *
     * @return String
     */
    public String getSsoUser() {
        return ssoUser;
    }

    /**
     * Accessor for the region enumeration value.
     *
     * @return Products
     */
    public ENV getENV() {
        return region;
    }

    /**
     * Accessor for the feature perm ID (based on the region).
     *
     * @return String
     */
    public String getENVName() {
        return getENV().getENVName();
    }

}