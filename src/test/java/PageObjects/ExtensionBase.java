package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ExtensionBase extends PageBase {
    protected WebElement I;

    public ExtensionBase(WebElement element){
        I = element;
    }

    protected WebElement xpath(String xpath){
        return I.findElement(By.xpath(xpath));
    }

    protected List<WebElement> xpaths(String xpath){
        return I.findElements(By.xpath(xpath));
    }

    protected WebElement css(String css){
        return I.findElement(By.cssSelector(css));
    }

    protected  List<String> getStringsFromElements (List<WebElement> list){
        List<String> names = new ArrayList<>();
        if(list.size()>0){
            list.stream().forEach(x-> {
                names.add(x.getText());
            });
        }
        return names;
    }

    protected boolean exists(String xpath){
        return xpaths(xpath).size() > 0;
    }
}
