package pageObject;

import static common.CommonAction.click;
import static common.CommonAction.saveOcrImage;
import static common.CommonAction.screenshotPage;
import static common.CommonAction.textToFile;
import static org.openqa.selenium.support.PageFactory.initElements;
import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.Configuration;

public class GrantsAndFundingPage {
	Configuration config = new Configuration();
	WebDriver driver;
	File sShot;

	public GrantsAndFundingPage(WebDriver driver) {
		this.driver = driver;
		initElements(driver, this);
	}

	@FindBy(xpath = "//h4/a[text()='How to Apply']")
	WebElement howToApplyLinkLocator;

	@FindBy(xpath = "//td[contains(text(),'Guidance for research only')]//following::a")
	WebElement pdfClickLocator;

	public void openPdfAndTakeScreenshot() throws AWTException, InterruptedException, IOException {
		click(howToApplyLinkLocator);
		click(pdfClickLocator);
		sShot = screenshotPage(driver, "pdfFile");
	}

	public void saveOcrImageToText() throws Exception {
		String s1 = saveOcrImage(sShot);
		textToFile(s1, "ocr");
	}
}
