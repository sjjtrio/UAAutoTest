package BDD.stepdefs.API;

import BDD.stepdefs.UI.StepCommon;
import Utilities.DataKeys;
import Utilities.JsonOP;
import Utilities.ScenarioContext;
import Utilities.cloud.rest.AuthenticationTokensCache;
import Utilities.cloud.rest.RestCallCache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APIStepBase {
    JsonOP jsonOP(){ return JsonOP.getInstance();}
    RestCallCache restCallCache(){return RestCallCache.getInstance();}
    AuthenticationTokensCache authenticationTokensCache(){return AuthenticationTokensCache.getInstance();}
    protected String getProperty(String key){return StepCommon.getInstance().getProperty(key);}
    void sSet(String key, Object val){
        ScenarioContext.getInstance().set(key,val);
        if(val != null){
            RestCallCache.getInstance().addTestStepOutputData(key,val.toString());
        }
    }
    void setBodyStringForNextStepToUse(String bodyString){
        sSet(DataKeys.API.APIRequestBodyJson, bodyString);
    }
    String getBodyStringFromPreviousStep(){
        return sGet(DataKeys.API.APIRequestBodyJson);
    }
    <T> T sGet(String key){
        return ScenarioContext.getInstance().get(key);
    }
    boolean sNotExist(String key){return sGet(key)==null;}
    <T> T getRandomItemFromList(List<T> list){
        return list.get((int)(Math.random() * ( list.size())));
    }
    String getLastCallBody(){return restCallCache().getLastCall().getResponse().body().asString();}
    String getLastCallStatusCode(){return Integer.toString(restCallCache().getLastCall().getResponse().getStatusCode());}
    void log(String s){System.out.println(s);}
    <T> String serilizeJson(T t){ return jsonOP().serialiseJson(t);}
    <T> T deserilizeJson(String json, Class<T> t){return jsonOP().deserialiseJson(json, t);}
    Map<String, Object> queryParams(String... args){
        Map<String, Object> map = new HashMap<>();
        int i = 1;
        String k = "";
        String v = "";
        for (String arg : args) {
            if(i % 2 ==0){
                v = arg;
                map.put(k,v);
            }else{
                k = arg;
            }
            i++;
        }
        return map;
    }
}
