package auto;

import org.testng.annotations.Test;
import base.TestBase;

public class AutoNewsAndEventsTest extends TestBase {

	@Test(groups = "regression")
	public void testNewsAndEventsPage() {

		np.clickNewsAndEventsLinkAndCheckTitle();
		try {
			np.controlClickSocialMediaLinkWithScreenRoder();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(groups = "smoke")
	public void testNewsAndEventsPageSmoke() {
		np.clickNewsAndEventsLinkAndCheckTitle();
	}

}
