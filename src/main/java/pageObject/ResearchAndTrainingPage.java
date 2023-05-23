package pageObject;

import static org.openqa.selenium.support.PageFactory.initElements;

import org.openqa.selenium.WebDriver;

public class ResearchAndTrainingPage {
	WebDriver driver;
	
	public ResearchAndTrainingPage(WebDriver driver) {
		this.driver = driver;
		initElements(driver, this);
	}
}
