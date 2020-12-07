package test;

import objectRepository.DriverConfig;
import objectRepository.SearchResults;
import objectRepository.ShowsPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;

public class seleniumTest {

    @Test
    public void search() throws InterruptedException {

        DriverConfig dc = new DriverConfig();
        WebDriver driver = null;
        ChromeOptions options = new ChromeOptions();
        dc.configDriver();
        driver = new ChromeDriver(options);

        //System.setProperty(dc.myDriver(), dc.driverLocation());
        WebDriverWait wait=new WebDriverWait(driver, 10);
        ShowsPage sp = new ShowsPage(driver, wait);
        SearchResults sr = new SearchResults(driver, wait);

        driver.manage().window().maximize();
        driver.get(sp.showsURL());

        //Shows search Page
        sp.searchTBox().sendKeys(sp.searchText());
        sp.submitButton().click();

        //Search Results
        sr.clickURL().click();
        //Go back to Search Results
        driver.navigate().back();
       JavascriptExecutor js = (JavascriptExecutor) driver;
       js.executeScript(sr.scrollTo(), sr.batmanUnlimited());
       js.executeScript(sr.changeBGColor(), sr.batmanUnlimited());

       //This is only to see the changed background
       Thread.sleep(2000);

       sr.backButton().click();

       //Search page again
       sp.searchTBox().clear();
        if (!sp.searchTBox().getAttribute(sp.attr()).equals(sp.desiredText())) {
            fail();
            }
        else
        {
            System.out.println(sp.emptyBox());
        }
        driver.quit();
    }



}
