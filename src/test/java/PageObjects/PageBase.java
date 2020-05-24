package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class PageBase {

    protected void scrollToElement(WebDriver driver, WebElement element){
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("arguments[0].scrollIntoView(true);",element);
        long last_height = (long)je.executeScript("return document.body.scrollHeight");
        long new_height = last_height;
        do {
            last_height = new_height;
            je.executeScript("arguments[0].scrollIntoView(true);",element);
            //je.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new_height = (long)je.executeScript("return document.body.scrollHeight");
        }while(new_height != last_height);
    }

    protected void scrollToPageBottom(WebDriver driver, WebElement container, String containerItemXpath){
        int orgCount = 0;
        int lastOrgCount = 0;
        do{
            lastOrgCount = orgCount;
            List<WebElement> orgs = container.findElements(By.xpath(containerItemXpath));
            orgCount = orgs.size();
            scrollToElement(driver, orgs.get(orgCount-1));
        }while(orgCount > lastOrgCount);
    }

    protected boolean hasClass(WebElement element, String val){
        return element.getAttribute("class").contains(val);
    }

    protected <T> T getExtension(Class<T> t, WebElement element){
        Constructor<T> constructor = null;
        try {
            constructor = t.getConstructor(WebElement.class);
            return constructor.newInstance(element);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String translate(String att){
        return "translate("+att+",'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')";
    }

    protected String translate(String att, String val){
        return "translate("+att+",'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='" + val+"'";
    }

}
