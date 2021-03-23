package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public class Basetest {

    //keeping the driver class as protected because it can be accessed in the child classes
        protected  WebDriver driver;


    @BeforeTest
    public void setupDriver(ITestContext ctx) throws MalformedURLException {
        setProxymethod();
        //Chrome
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\nshibaha\\Docker\\chromedriver_win32\\chromedriver.exe");
        this.driver =new ChromeDriver();


        System.setProperty("webdriver.gecko.driver","C:\\Users\\nshibaha\\Docker\\Selenium grid test\\geckodriver-v0.28.0-win64\\geckodriver.exe");
        this.driver = new FirefoxDriver();
/*
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--verbose");
        chromeOptions.addArguments("--whitelisted-ips=''");
        chromeOptions.addArguments("--proxy-server=localhost:4444");*/

        //Browser input required
        //host url input required


        //below two we are declaring incase user is not providing any value
        String host ="localhost";
        DesiredCapabilities dc ;

       String BROWSER ="firefox";


       if(System.getProperty("BROWSER") != null && System.getProperty("BROWSER").equalsIgnoreCase("firefox")){
           // if((BROWSER) != null && (BROWSER).equalsIgnoreCase("firefox")){
            dc = DesiredCapabilities.firefox();

        }

        else{
            dc = DesiredCapabilities.chrome();
        }
        if(System.getProperty("HUB_HOST") != null){
            host = System.getProperty("HUB_HOST");
        }
            String testname =ctx.getCurrentXmlTest().getName();

        String completeUrl = "http://" + host + ":4444/wd/hub";
        dc.setCapability("name",testname);

        this.driver = new RemoteWebDriver(new URL(completeUrl),dc);
        driver.manage().window().maximize();
    }



    @AfterTest
    public void quitBrowser()
    {
        this.driver.quit();
    }


    public void setProxymethod() {
        System.setProperty("https.proxyHost", "proxy-chain.intel.com");
        System.setProperty("https.proxyPort", "912");
        System.setProperty("http.proxyPort", "911");

    }


}