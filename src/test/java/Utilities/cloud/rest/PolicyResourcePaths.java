package Utilities.cloud.rest;


public enum PolicyResourcePaths {

    /* Methods
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    PATCH("PATCH"),
    DELETE("DELETE"),
    HEAD("HEAD"), */

    // Path resource
    list_endpoints("GET:/endpoints"),
    create_endpoint("POST:/endpoints"),
    get_endpoint("GET:/endpoints/{endpoint_id}"),
    update_endpoint("PATCH:/endpoints/{endpoint_id}"),
    delete_endpoint("DELETE:/endpoints/{endpoint_id}"),

    list_services("GET:/services"),
    create_service("POST:/services"),
    get_service("GET:/services/{service_id}"),
    update_service("PATCH:/services/{service_id}"),
    delete_service("DELETE:/services/{service_id}"),

    list_projects("GET:/projects"),
    create_project("POST:/projects"),
    get_project("GET:/projects/{project_id}"),
    list_user_projects("GET:/users/{user_id_1}/projects"),
    update_project("PATCH:/projects/{project_id}"),
    delete_project("DELETE:/projects/{project_id}"),

    create_domain("POST:/domains"),
    list_domains("GET:/domains"),
    get_domain("GET:/domains/{domain_id}"),
    update_domain("PATCH:/domains/{domain_id}"),
    delete_domain("DELETE:/domains/{domain_id}"),

    create_group("POST:/groups"),
    list_groups("GET:/groups"),
    get_group("GET:/groups/{group_id}"),
    list_groups_for_user("GET:/groups/{group_id}/users"),
    update_group("PATCH:/groups/{group_id}"),
    add_user_to_group("PUT:/groups/{group_id}/users/{user_id_1}"),
    list_users_in_group("GET:/groups/{group_id}/users"),
    check_user_in_group("HEAD:/groups/{group_id}/users/{user_id_1}"),
    remove_user_from_group("DELETE:/groups/{group_id}/users/{user_id_1}"),
    delete_group("DELETE:/groups/{group_id}"),

    create_role("POST:/roles"),
    get_role("GET:/roles/{role_id}"),
    list_roles("GET:/roles"),
    update_role("PATCH:/roles/{role_id}"),
    delete_role("DELETE:/roles/{role_id}"),

    create_implied_role("PUT:/roles/{prior_role_id}/implies/{implied_role_id}"),
    check_implied_role("HEAD:/roles/{prior_role_id}/implies/{implied_role_id}"),
    get_implied_role("GET:/roles/{prior_role_id}/implies/{implied_role_id}:"),
    list_implied_roles("GET:/roles/{prior_role_id}/implies"),
    list_role_inference_rules("GET:/role_inferences"),
    delete_implied_role("DELETE:/roles/{prior_role_id}/implies/{implied_role_id}"),

    create_grant("PUT:/projects/{project_id}/users/{user_id_1}/roles/{role_id}"),
    check_grant("HEAD:/projects/{project_id}/users/{user_id_1}/roles/{role_id}"),
    list_grants("GET:/projects/{project_id}/users/{user_id_1}/roles"),
    revoke_grant("DELETE:/projects/{project_id}/users/{user_id_1}/roles/{role_id}"),

    list_role_assignments("GET:/role_assignments"),
    list_role_assignments_for_tree("GET:/role_assignments?scope.project.id={project_id}&include_subtree=true"),

    create_user("POST:/users"),
    list_users("GET:/users"),
    get_user("GET:/users/{user_id}"),
    change_password("POST:/users/{user_id_1}/password"),
    update_user("PATCH:/users/{user_id}"),
    delete_user("DELETE:/users/{user_id}");



    private String resourcePath;

    PolicyResourcePaths(String e) {
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