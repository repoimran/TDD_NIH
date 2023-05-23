package auto;

import org.testng.annotations.Test;

import base.TestBase;

public class AutoGrantsAndFundingPage extends TestBase {

	@Test
	public void testGrantsAndFundingPage() throws Throwable {
		gp.openPdfAndTakeScreenshot();
		gp.saveOcrImageToText();
	}

}
