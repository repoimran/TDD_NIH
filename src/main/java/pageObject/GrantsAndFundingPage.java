package pageObject;

import static org.openqa.selenium.support.PageFactory.initElements;
import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static common.CommonAction.*;
import util.Configuration;

public class GrantsAndFundingPage {
	WebDriver driver;
	Configuration config = new Configuration();

	public GrantsAndFundingPage(WebDriver driver) {
		this.driver = driver;
		initElements(driver, this);
	}

	@FindBy(xpath = "//td[contains(text(),'Guidance for research only')]//following::a")
	WebElement pdfClick;

	@FindBy(xpath = "//h4/a[text()='How to Apply']")
	WebElement howToApplyLink;

	public void openPdfAndTakeScreenshot() throws AWTException, InterruptedException, IOException {

		click(howToApplyLink);
		click(pdfClick);
		screenshotPage(driver, "pdfFile");
	}

	public void saveOcrImageToText() throws Exception {
		String s1 = saveOcrImage();
		textToFile(s1);
	}
}
