package pageObject;

import static org.openqa.selenium.support.PageFactory.initElements;

import org.openqa.selenium.WebDriver;

public class AboutPage {
	WebDriver driver;
	
	public AboutPage(WebDriver driver) {
		this.driver = driver;
		initElements(driver, this);
	}

}
