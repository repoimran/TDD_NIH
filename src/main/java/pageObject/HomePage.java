package pageObject;

import static org.openqa.selenium.support.PageFactory.initElements;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static common.CommonAction.*;
import util.Configuration;
import util.Key;

public class HomePage {
	WebDriver driver;
	Configuration config = new Configuration();

	public HomePage(WebDriver driver) {
		this.driver = driver;
		initElements(driver, this);
	}

	@FindBy(xpath = "//span[normalize-space()='Health Information']")
	WebElement healthInformationLink;
	@FindBy(xpath = "//input[@id='query']")
	WebElement searchBox;;
	@FindBy(xpath = "//a[normalize-space()='Subscribe']")
	WebElement SubscribeTab;;

	public void getPageTitle() {
		String title = driver.getTitle();
		System.out.println(title);
	}

	public void insertIntoSearchBox() {
		insert(searchBox, "Senior Health");
		try {
			screenshotElement(driver, "SearchBox", searchBox);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void mouseHoverHealthInformationLinkSubMenuCheck() throws InterruptedException, IOException {

		mouseHover(driver, healthInformationLink);
		screenshotPage(driver, "SubMenuLinks");
		TimeUnit.MILLISECONDS.sleep(config.readPropNum(Key.pause.name()));
	}

	public void enterEmailForSubscribe() throws Exception {// did not work cause of captcha
		String rootWindow = driver.getWindowHandle();
		click(SubscribeTab);
		Set<String> allWindows = driver.getWindowHandles();
		switchWindow(rootWindow, allWindows, driver);
		WebElement e1 = driver.findElement(By.id("email"));
		insert(e1, config.readProp(Key.email.name()));
		WebElement e2 = driver.findElement(By.xpath("//input[@name='commit']"));
		click(e2, driver); // jsClick overriding
		WebElement e3 = driver.findElement(By.xpath("//input[@id='subscriber_email_confirm']"));
		insert(e3, config.readProp(Key.email.name()));
		WebElement e4 = driver.findElement(By.xpath("//input[@id='subscriber_password']"));
		insert(e4, config.readProp(Key.pass.name()));
		WebElement e5 = driver.findElement(By.xpath("//input[@id='subscriber_password_confirm']"));
		insert(e5, config.readProp(Key.pass.name()));
		screenshotElement(driver, "TestImageOfEmail", e3);// testing element screenshot

	}

}
