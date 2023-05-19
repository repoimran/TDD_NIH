package common;

import static common.CommonWait.waitUntilClickable;
import static common.CommonWait.waitUntilVisible;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import report.Log;
import util.Misc;

public class CommonAction {

	static String screenshotPath = "C:\\Users\\imsdh\\eclipse-workspace\\TDD_NIH\\test-output\\screenshot\\";
	static String tessarectTextPath = "C:\\Users\\imsdh\\eclipse-workspace\\TDD_NIH\\test-output\\OCR Files\\";
	static File target;
	static String ocrString;

	public static String getInnerHTML(WebElement element) {
		waitUntilVisible(element);
		String innerText = element.getAttribute("innerHTML");
		Log.log(Misc.getClassName(Thread.currentThread()), element + " <<< Inner Text : " + innerText);
		return innerText;
	}

	public static String getText(WebElement element) {
		waitUntilVisible(element);
		String innerText = element.getText();
		Log.log(Misc.getClassName(Thread.currentThread()), element + " <<< Inner Text : " + innerText);
		return innerText;
	}

	public static void click(WebElement element) {
		waitUntilClickable(element);
		element.click();
		Log.log(Misc.getClassName(Thread.currentThread()), element + " Has been clicked");
	}

	public static void jsClick(WebElement element, WebDriver driver) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Log.log(Misc.getClassName(Thread.currentThread()), element + " Has been clicked with JavascriptExec");
	}

	public static void insert(WebElement element, String text) {
		element.sendKeys(text);
		Log.log(Misc.getClassName(Thread.currentThread()), element + " <<< value inserted : " + text);
	}

	public static void insert(WebElement element, char text) {
		String temp = String.valueOf(text);
		element.sendKeys(temp);
		Log.log(Misc.getClassName(Thread.currentThread()), element + " <<< value inserted : " + text);
	}

	public static void dropdown(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByVisibleText(value);
		Log.log(Misc.getClassName(Thread.currentThread()), element + " <<< selected : " + value);
	}

	public static void switchWindow(String root, Set<String> allWindows, WebDriver driver) {
		for (String handle : allWindows) {
			if (!handle.equals(root)) {
				driver.switchTo().window(handle);
			}
		}
	}

	public static void screenshotElement(WebDriver driver, String name, WebElement element) throws Exception {
		waitUntilVisible(element);
		File src = element.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(src, new File(screenshotPath + name + ".png"));

	}

	public static void screenshotPage(WebDriver driver, String name) throws IOException {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		target = new File(screenshotPath + name + ".png");
		FileUtils.copyFile(source, target);
	}

	public static void mouseHover(WebDriver driver, WebElement element) {
		Actions actions = new Actions(driver);
		waitUntilVisible(element);
		actions.moveToElement(element).build().perform();
	}

	public static void roboKey(String key) {
		String k = key.toLowerCase();
		try {
			Robot robot = new Robot();
			if (k.equals("enter")) {
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);

			} else if (k.equals("tab")) {
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
			}
		} catch (AWTException e) {
			System.out.println(e.getMessage());
		}

	}

	public static String saveOcrImage() {
		ITesseract image = new Tesseract();
		try {
			ocrString = image.doOCR(target);
			System.out.println("******************** OCR TEXT START ********************");
			System.out.println(ocrString);
			System.out.println("******************** OCR TEXT END ********************");
		} catch (TesseractException e) {
			e.printStackTrace();
		}
		return ocrString;
	}

	public static String getTimestamp() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		String timestamp = format.format(date);
		return timestamp;
	}

	public static void textToFile(String text) throws IOException {
		// check if directory exists
		File directory = new File(tessarectTextPath);
		if (!directory.exists()) {
			directory.mkdirs();// if not create directory
		}
		String filePath = directory.getAbsolutePath() + File.separator + "ocr.txt"; // Build the file path
		text = getTimestamp() + "\n" + text; // adding timestamp and new line

		// Create the text file.
		FileWriter fileWriter = new FileWriter(filePath);
		fileWriter.write(text);// write the text to the file, not yet saved
		fileWriter.close();// save the file

		System.out.println("String saved to file successfully.");
	}

}
