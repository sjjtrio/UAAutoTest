package Utilities.cloud.rest;

public enum ENV {

    FT("FT"),
    ITG("ITG"),
    PROD("PROD");

    private String Environment;

    ENV(String e){
        Environment =e;
    }

    public String getENVName(){
        return Environment;
    }
}
