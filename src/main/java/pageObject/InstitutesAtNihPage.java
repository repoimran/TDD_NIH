package pageObject;

import static org.openqa.selenium.support.PageFactory.initElements;

import org.openqa.selenium.WebDriver;

public class InstitutesAtNihPage {
	WebDriver driver;
	
	public InstitutesAtNihPage(WebDriver driver) {
		this.driver = driver;
		initElements(driver, this);
	}
}
