package base;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObject.HomePage;
import util.Configuration;
import util.Key;
import static util.Key.*;
import static util.Browser.*;

public class TestBase {

	protected WebDriver driver;
	protected HomePage homePage;
	Configuration config = new Configuration();
	
	@BeforeMethod
	public void beforeEachTest() {
		String browserName = config.readProp(getValue(browser));
		initiatDriver(browserName);
		initObject();
		driver.manage().window().maximize();
		int pageLoadTime = config.readPropNum(getValue(pageLoad));
		int implicitLoadTime = config.readPropNum(getValue(implicitLoad));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTime));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitLoadTime));
		String urlName = config.readProp(getValue(url));
		driver.get(urlName);
	}
	
	public void initiatDriver(String browser) {
		switch (browser) {
		case CHROME:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		case EDGE:
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		}
	}
	
	protected void initObject() {
		homePage = new HomePage(driver);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	
	private String getValue(Key key) {
		return key.name();
	}
	
}
