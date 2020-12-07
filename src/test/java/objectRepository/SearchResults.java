package objectRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResults {

    public SearchResults(WebDriver driver, WebDriverWait wait)
    {
        this.driver = driver;
        this.wait = wait;
    }

    WebDriver driver;
    WebDriverWait wait;
    By URL = By.xpath("//a[@href='http://www.tvmaze.com/shows/481/the-batman']");
    By Unlimited = By.xpath("//span[contains(text(),'Batman Unlimited')]//parent::div");
    By goBack = By.xpath("//a[@class='btn btn-primary']");

    public WebElement clickURL(){
        return wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(URL)));
    }

    public WebElement batmanUnlimited(){
       return driver.findElement(Unlimited);
    }

    public WebElement backButton(){
        return wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(goBack)));
    }

    public String changeBGColor(){
        return "arguments[0].setAttribute('style', 'background-color: #4a148c')";
    }
    public String scrollTo(){
        return "arguments[0].scrollIntoView(true)";
    }
}
