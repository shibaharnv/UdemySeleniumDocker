package searchmodule.tests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import searchmodules.pages.SearchPage;
import tests.Basetest;

public class SearchTest extends Basetest {



/*
    @BeforeTest
    public void setupDriver()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\nshibaha\\Docker\\chromedriver_win32\\chromedriver.exe");
        this.driver =new ChromeDriver();
    }
*/


    @Test
    @Parameters({"keyword"})
    public void search(String keyword){

        SearchPage searchPage = new SearchPage(driver);
        searchPage.goTo();
        searchPage.doSearch(keyword);
        searchPage.goToVideos();
        int size = searchPage.getResult();

        Assert.assertTrue(size > 0);
    }

    @AfterTest
    public void quitDriver()
    {
        this.driver.quit();
    }
}
