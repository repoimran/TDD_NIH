package pageObject;

import static org.openqa.selenium.support.PageFactory.initElements;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static common.CommonAction.click;
import static common.CommonAction.switchWindow;
import static util.ScreenRecorderUtil.*;

public class NewsAndEventPage {
	WebDriver driver;

	public NewsAndEventPage(WebDriver driver) {
		this.driver = driver;
		initElements(driver, this);
	}

	@FindBy(xpath = "//span[contains(normalize-space(),'News & Events')] ")
	WebElement newsAndEventsLink;
	@FindBy(xpath = "//h4/a[text()='Social Media']")
	WebElement socialMediaLink;

	public void clickNewsAndEventsLinkAndCheckTitle() {
		click(newsAndEventsLink);
		System.out.println(driver.getTitle());
	}

	public void controlClickSocialMediaLinkWithScreenRoder() throws Exception {
		// Starts the screen recording
		startRecord("np_clickSocialMediaLinkWithScreenRoder");
		String currentUrl = driver.getWindowHandle();
		click(socialMediaLink, driver, " ");// control click overriding
		Set<String> allWinds = driver.getWindowHandles();
		switchWindow(currentUrl, allWinds, driver);
		Thread.sleep(5000); // only for visual testing
		// Ends the screen recording
		stopRecord();

	}

}
