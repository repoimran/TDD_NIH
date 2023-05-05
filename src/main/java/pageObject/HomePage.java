package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import com.github.dockerjava.api.model.Driver;

import static org.testng.Assert.*;
import static org.openqa.selenium.support.PageFactory.*;
import static common.CommonAction.*;

public class HomePage {
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[normalize-space()='Health Information']") WebElement healthInformationLink;
	
	
	
	@Test
	public void clickHealthInformationLink() {
		
		click(healthInformationLink);
	}
	
	@Test
	public void getPageTitle() {
		
		String title = driver.getTitle();
		System.out.println(title);
	}
	
}
