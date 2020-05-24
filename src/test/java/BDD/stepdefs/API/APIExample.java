package BDD.stepdefs.API;

import BDD.stepdefs.UI.StepBase;
import DataModel.API.JsonModel.WeatherModel;
import Utilities.cloud.rest.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.testng.Assert;

import java.io.IOException;

public class APIExample extends APIStepBase {

    private void send_get_request(String service_name,  String endpoint) throws IOException, RestCallLoggingException {
        RestCallCache.getInstance().get(service_name).makeRequest(ResourcePaths.GET.getResourcePath(), endpoint, true);
    }

    @Given("API test data for (.*) is ready")
    public void apiTestDataIsReady(String service_name){
        System.out.println("API test is ready");
        // prepare data, login, get auth token etc.....

        RequestBuilder.loadRequestSpecs(service_name);

        RestCall restCall = RestCallCache.getInstance().get(service_name);

        restCall.setRequest(RequestBuilder.defaultRequestSpec());

    }

    @Given("I send request to weather service by coordinates")
    public void iSendRequestToWeatherServiceByCoordinates() throws IOException, RestCallLoggingException {
        String latitude = getProperty("latitude");
        String longitude = getProperty("longitude");

        send_get_request("weather", ResourcePaths.Weather_Service_Coordinates.get(latitude, longitude) );
    }

    @Then("The response code should be (.*)")
    public void theResponseCodeShouldBe(String arg0) {
        Assert.assertEquals(getLastCallStatusCode(), arg0, "Status code of last response should be " + arg0);
    }

    @And("The response body should contain the coordinates")
    public void theResponseBodyShouldContainTheCoordinates() {
        WeatherModel weatherModel = jsonOP().deserialiseJson(getLastCallBody(), WeatherModel.class);
        Assert.assertTrue(weatherModel.geometry.coordinates.length > 0);

        // More assertions....

    }
}
