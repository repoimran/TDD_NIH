package auto;

import org.testng.annotations.Test;
import base.TestBase;

public class AutoTest extends TestBase{

	@Test
	public void sanity() throws InterruptedException {
		homePage.clickHealthInformationLink();
		homePage.getPageTitle();
		Thread.sleep(5000);
	}
}
