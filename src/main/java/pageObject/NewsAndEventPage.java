package pageObject;

import static org.openqa.selenium.support.PageFactory.initElements;

import org.openqa.selenium.WebDriver;

public class NewsAndEventPage {
	WebDriver driver;
	
	public NewsAndEventPage(WebDriver driver) {
		this.driver = driver;
		initElements(driver, this);
	}
}
