package auto;

import java.io.IOException;
import org.testng.annotations.Test;

import base.TestBase;

public class AutoHomePageTest extends TestBase {

	@Test(groups = "regression")
	public void testHomePage() throws InterruptedException, IOException {
		hp.getPageTitle();
		hp.insertIntoSearchBox();
		hp.mouseHoverHealthInformationLinkSubMenuCheck();
	}

	@Test(groups = "smoke")
	public void testHomePageSmoke() {
		hp.getPageTitle();
	}

}
