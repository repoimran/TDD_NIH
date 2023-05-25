package auto;

import org.testng.annotations.Test;

import base.TestBase;

public class AutoGrantsAndFundingPageTest extends TestBase {

	@Test(groups = "regression")
	public void testGrantsAndFundingPage() throws Throwable {
		gp.clickGrantsAndFundingLink();
		gp.openPdfAndTakeScreenshot();
		gp.saveOcrImageToText();
	}

	@Test(groups = "smoke")
	public void testGrantsAndFundingPageSmoke() {
		gp.clickGrantsAndFundingLink();
	}

}
