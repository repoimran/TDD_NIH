package common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import report.Log;

public class CommonAction {

	public static void click(WebElement element) {
		element.click();
		Log.log(element + "clicked...");
	}

	public static void insert(WebElement element, String text) {
		element.sendKeys(text);
		Log.log(element + " inserted ..." + text);
	}

	public static void insert(WebElement element, char text) {
		element.sendKeys(text + "");
		Log.log("inserted...." + text + "@ element " + element);
	}

}
