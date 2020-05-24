package BDD.stepdefs.UI;

import PageObjects.PageBase;
import Utilities.DataKeys;
import Utilities.ScenarioContext;
import Utilities.TestContext;
import Utilities.cloud.fw.ProfileProperties;
import com.tyro.oss.randomdata.RandomInteger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class StepBase {

    private StepCommon I(){
        return StepCommon.getInstance();
    }

    protected WebDriver getDriver(){
        return I().getDriver();
    }

    protected <T extends PageBase> T getPage(Class<T> type) {
        return I().getPage(type);
    }

    protected void refreshPage(){
        getDriver().navigate().refresh();
    }

    protected void log(String s){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("[Test Log "+ dtf.format(now)+" ]: "+s);
        //System.out.println(s);
    }

    protected <T> T getRandomItemFromList(List<T> list){
        return list.get((int)(Math.random() * ( list.size())));
    }

    protected String getProperty(String property){
        return StepCommon.getInstance().getProperty(property);
    }

    // Get object from Scenario context
    protected <T> T sGet(String k){return ScenarioContext.getInstance().get(k);}

    // Check if key is existing in scenario context
    protected boolean sGetExist(String k){
        return sGet(k) != null;
    }

    // Set object to scenario context
    protected void sSet(String k, Object o){
        ScenarioContext.getInstance().set(k,o);
    }

    // Set object to test context
    protected void tSet(String k, Object o) { TestContext.getInstance().set(k,o); }

    // Get object from test context
    protected <T> T tGet(String k){ return TestContext.getInstance().get(k); }


    protected Boolean elementExists(By by){
        getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        Boolean exist = I().elementExists(by);
        getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(getProperty(ProfileProperties.KEY_PROP_ELEMENT_TIMEOUT)), TimeUnit.SECONDS);
        return exist;
    }

    protected void waitWhenEnabled(WebElement element){
        WebDriverWait wait = new WebDriverWait(getDriver(), Integer.parseInt(getProperty(ProfileProperties.KEY_PROP_ELEMENT_TIMEOUT)));
        wait.until((ExpectedCondition<Boolean>) driver -> element.isEnabled());
    }

    protected WebElement waitWhenVisible(WebElement element){
        WebDriverWait wait = new WebDriverWait(getDriver(), Integer.parseInt(getProperty(ProfileProperties.KEY_PROP_ELEMENT_TIMEOUT)));
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    protected void waitPageLoading(){
        WebDriverWait wait = new WebDriverWait(getDriver(), Integer.parseInt(getProperty(ProfileProperties.KEY_PROP_WAIT_TIMEOUT)));
        setElementTimeOut(1);
        wait.until((ExpectedCondition<Boolean>) driver -> driver.findElements(By.cssSelector("#loading-bar-spinner")).size() == 0
                    && driver.findElements(By.cssSelector("#loading-bar")).size() == 0);

        setDefaultElementTimeOut();
    }

    protected void waitModalClose(){
        WebDriverWait wait = new WebDriverWait(getDriver(), Integer.parseInt(getProperty(ProfileProperties.KEY_PROP_ELEMENT_TIMEOUT)));
        setElementTimeOut(1);
        wait.until((ExpectedCondition<Boolean>) driver
                -> driver.findElements(By.cssSelector(".reveal-modal-bg.fade.in")).size() == 0);

        setDefaultElementTimeOut();
    }

    protected void scrollToElement( WebElement element){
        JavascriptExecutor je = (JavascriptExecutor) getDriver();
        je.executeScript("arguments[0].scrollIntoView(true);",element);
        long last_height = (long)je.executeScript("return document.body.scrollHeight");
        long new_heght = last_height;
        do {
            last_height = new_heght;
            je.executeScript("arguments[0].scrollIntoView(true);",element);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new_heght = (long)je.executeScript("return document.body.scrollHeight");
        }while(new_heght != last_height);
    }

    protected void scrollToBottom(){
        scrollToElement(getDriver().findElement(By.xpath("(//propel-footer)[2]")));
    }
    protected void setElementTimeOut(int sec){
        getDriver().manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
    }

    protected void setDefaultElementTimeOut(){
        getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(getProperty(ProfileProperties.KEY_PROP_ELEMENT_TIMEOUT)), TimeUnit.SECONDS);
    }

    protected <T> T pickOne(List<T> list){
        if(list.size() > 0 ){
            return list.get(RandomInteger.randomIntegerBetween(0,list.size()-1));
        }else{
            return null;
        }
    }

    protected void blurElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].focus(); arguments[0].blur(); return true", element);
    }

    protected <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = RandomInteger.randomIntegerBetween(0,clazz.getEnumConstants().length-1);
        return clazz.getEnumConstants()[x];
    }
}
