package PageObjects.Bing;

import PageObjects.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageBase {

    @FindBy(css = "#sb_form_q")
    public WebElement Search_Box;

    @FindBy(css = ".search.icon.tooltip")
    public WebElement Search_Button;

    public void search(String text){
        Search_Box.sendKeys(text);
        Search_Button.click();
    }

}
