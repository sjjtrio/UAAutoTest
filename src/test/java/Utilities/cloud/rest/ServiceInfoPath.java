package Utilities.cloud.rest;

public enum ServiceInfoPath {

    vpc_auth("/auth"),
    ngp_auth("/auth"),

    get_customers("/customers"),
    get_specific_customer("/customers/{customerId}"),
    get_customer_by_token("/customer"),

    get_departments("/customers/{customerId}/departments"),
    get_specific_department("/customers/{customerId}/departments/{departmentId}"),
    get_department_by_token("/department"),

    get_virtualDataCenters("/customers/{customerId}/departments/{departmentId}/virtualdatacenters"),
    get_specific_virtualDataCenter("/customers/{customerId}/departments/{departmentId}/virtualdatacenters/{virutaldatacenterId}"),
    validate_virtualDataCenterPair("/validatedvirtualdatacenters"),

    get_locations("/locations"),
    get_specific_location("/locations/{locationId}"),

    get_users("/customers/{customerId}/departments/{departmentId}/users"),
    get_specific_user("/customers/{customerId}/departments/{departmentId}/users/{userId}"),
    get_user_by_token("/user"),

    get_resource_permission("/permissions"),

    get_static_resources("/staticResources"),

    get_vendors("/vendors"),
    get_specific_vendor("/vendors/{vendorId}");


    private String path;
    ServiceInfoPath(String e) { path = e;}

    public String getServiceInfoPath(){
        return path;
    }

    public String getServiceInfoPath(String e){
        if(e.equals("")){
            return getServiceInfoPath();
        }
        String r = path;
        r = r.replaceFirst("\\{.*?\\}",e);
        return r;
    }

    public String getServiceInfoPath(String[] e){
        if(e.equals("")){
            return getServiceInfoPath();
        }
        String r = path;
        for (int i =0; i < e.length ; i++){
            r = r.replaceFirst("\\{.*?\\}",e[i]);
        }

        //r = r.replaceFirst("\\{.*?\\}",e);
        return r;
    }

    public String get(String... e){

        String r = path;
        for (int i =0; i < e.length ; i++){
            r = r.replaceFirst("\\{.*?\\}",e[i]);
        }

        //r = r.replaceFirst("\\{.*?\\}",e);
        return r;
    }
}
