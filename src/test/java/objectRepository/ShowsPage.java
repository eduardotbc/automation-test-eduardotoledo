package objectRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShowsPage {


    public ShowsPage(WebDriver driver, WebDriverWait wait)
    {
        this.driver = driver;
        this.wait = wait;
    }

    public String showsURL() {
        return "http://localhost:3000/shows";
    }

    public String searchText() {
        return "batman";
    }
    public String emptyBox(){
        return "Text box is empty.";
    }
    public String attr(){
        return "value";
    }
    public String desiredText(){
        return "";
    }


    WebDriver driver;
    WebDriverWait wait;
    By input = By.xpath("//input[@placeholder='Search']");
    By submit = By.xpath("//button[@type='submit']");


    public WebElement searchTBox(){
        return wait.until(ExpectedConditions.visibilityOf(driver.findElement(input)));
    }
    public WebElement submitButton(){
        return wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(submit)));
    }
}
