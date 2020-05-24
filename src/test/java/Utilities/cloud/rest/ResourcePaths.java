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
    Weather_Service_Coordinates("/points/{latitude},{longitude}");

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

    public String get(String... e){
        String r = resourcePath;
        for(String s : e){
            r = r.replaceFirst("\\{.*?\\}", s);
        }
        return r;
    }

}