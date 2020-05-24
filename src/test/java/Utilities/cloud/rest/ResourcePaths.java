package Utilities.cloud.rest;


public enum ResourcePaths {

    // Methods
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    PATCH("PATCH"),
    DELETE("DELETE"),
    HEAD("HEAD"),

    // Path resource
    AUTH_TOKEN("/auth/tokens"),
    GET_DOMAIN_LIST("/domains"),
    CHANGE_USER_PASSWORD("/users/{user_id}/password"),
    USER_WITH_ID("/users/{user_id}"),
    PROJECT_DETAIL("/projects/{project_id}"),
    PROJECTS("/projects"),
    PROJECTS_UPDATE("/projects/{project_id}"),
    PROJECT_TAGS("/projects/{project_id}/tags"),
    GET_USERS_LIST("/users"),
    GET_DOMAINS_LIST("/domains"),
    UPDATE_DOMAIN_DETAILS("/domains/{domain_id}"),
    SERVICE_ACCOUNT("/users"),
    LIST_SERVICES("/services"),
    SERVICES_ID("/services/{service_id}"),
    SERVICES("/services"),
    LIST_ENDPOINTS("/endpoints"),
    ENDPOINTS("/endpoints"),
    ENDPOINTS_ID("/endpoints/{endpoint_id}"),
    LIST_GROUPS("/groups"),
    GROUPS_ID("/groups/{group_id}"),
    ROLES("/roles"),
    ROLES_UPDATE("/roles/{role_id}"),
    DOMAIN_ROLES_ASSIGNMENT("/domains/{domain_id}/users/{user_id}/roles/{role_id}"),
    DOMAIN_ROLES_LIST("/domains/{domain_id}/users/{user_id}/roles"),
    PROJECT_ROLES_ASSIGNMENT("/projects/{project_id}/users/{user_id}/roles/{role_id}"),
    PROJECT_ROLES_LIST("/projects/{project_id}/users/{user_id}/roles"),
    ALL_ROLES_ASSIGNMENTS("/role_assignments"),
    V2_NETWORKS("/{tenant_id}/networks"),
    V2_DATACENTERS("/{tenant_id}/datacenters"),
    V2_VOLUMNS("/{tenant_id}/volumes"),
    V2_IMAGES("/{tenant_id}/images"),
    V2_FLAVORS("/{tenant_id}/flavors"),
    V2_SERVERS("/{tenant_id}/servers"),
    JIRA_ISSUE_DETAILS("/issue/{Issue_Id}"),
    JIRA_ISSUE_SEARCH("/search"),
    JIRA_ISSUE_SEARCH_JQL("/search?jql={jql}"),

    //Body json file path
    JSON_BODY_AUTHTOKENS("request/body/AuthTokens.json"),

    //Json content path
    JSON_CONTENT_AUTH_NODE_PATH("auth.identity.password.user"),
    JSON_CONTENT_AUTHTOKENS_DOMAIN("auth.identity.password.user.domain"),
    JSON_CONTENT_AUTH_USER("name"),
    JSON_CONTENT_AUTH_PSW("password"),
    JSON_CONTENT_AUTH_DOMAIN_ID("id"),
    JSON_CONTENT_AUTH_DEFAULT_DOMAIN("default"),
    JSON_CONTENT_AUTH_DEFAULT_USER("admin"),

    // Json properties
    JSON_CONTENT_AUTH_IDENTITY_PASSWORD("password");

    private String resourcePath;

    ResourcePaths(String e) {
        resourcePath = e;
    }

    /**
     * getResourcePath
     *
     * @return resourcePath
     */
    public String getResourcePath() {
        return resourcePath;
    }

    public String getResourcePath(String e){
        if(e.equals("")){
            return getResourcePath();
        }
        String r = resourcePath;
        r = r.replaceFirst("\\{.*?\\}",e);
        return r;
    }

    public String getResourcePath(String[] e){
        if(e.equals("")){
            return getResourcePath();
        }
        String r = resourcePath;
        for (int i =0; i < e.length ; i++){
            r = r.replaceFirst("\\{.*?\\}",e[i]);
        }

        //r = r.replaceFirst("\\{.*?\\}",e);
        return r;
    }

}