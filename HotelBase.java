package org.hotel.base;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
public class HotelBase {
	public static WebDriver driver;
	public WebDriver configure() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		return driver;
	}
	
	public void max() {
		driver.manage().window().maximize();
	}
	public void launch(String url) {
		driver.get(url);
	}
	public void sendKeys(WebElement txt, String value) {
		txt.sendKeys(value);
	}
	public void click(WebElement btn) {
		btn.click();
	}
	public String getCurrentUrl() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}
	public String getTitle() {
		String title = driver.getTitle();
		return title;
	}
	public void close() {
		driver.close();
	}
	public void quit() {
		driver.quit();
	}
	public void navigateTo(String url) {
		driver.navigate().to(url);
	}
	public void back() {
		driver.navigate().back();
	}
	public void forward() {
		driver.navigate().forward();
	}
	public void refresh() {
		driver.navigate().refresh();
	}
	public void switchToFrame(String id) {
		driver.switchTo().frame(id);
	}
	public void switchToFrameElement(WebElement element) {
		driver.switchTo().frame(element);
	}
	public void switchToFrameIndex(int index) {
		driver.switchTo().frame(index);
	}
	public String parentWindowId() {
		String windowHandle = driver.getWindowHandle();
		return windowHandle;
	}
	public Set<String> allWindowId() {
		Set<String> windowHandles = driver.getWindowHandles();
		return windowHandles;
	}
	public String getText(WebElement element) {
		String text = element.getText();
		return text;
	}
	public String getAttribute(WebElement element, String value) {
		String attribute = element.getAttribute(value);
		return attribute;
	}
	public void selectByIndex(WebElement element, int index) {
		Select s = new Select(element);
		s.selectByIndex(index);
	}
	public void selectByValue(WebElement element, String value) {
		Select s = new Select(element);
		s.selectByValue(value);
	}
	public void selectByVisibleText(WebElement element, String txt) {
		Select s = new Select(element);
		s.selectByVisibleText(txt);
	}
	public List<WebElement> getOption(WebElement element) {
		Select s = new Select(element);
		List<WebElement> options = s.getOptions();
		return options;
	}
	public List<WebElement> getAllSelectedOption(WebElement element) {
		Select s = new Select(element);
		List<WebElement> allSelectedOptions = s.getAllSelectedOptions();
		return allSelectedOptions;
	}
	public void deselectByIndex(WebElement element, int index) {
		Select s = new Select(element);
		s.deselectByIndex(index);
	}
	public void deselectByValue(WebElement element, String value) {
		Select s = new Select(element);
		s.deselectByValue(value);
	}
	public void deselectByVisibleText(WebElement element, String txt) {
		Select s = new Select(element);
		s.deselectByVisibleText(txt);
	}
	public void deselectAll(WebElement element) {
		Select s = new Select(element);
		s.deselectAll();
	}
	public void moveToElement(WebElement target) {
		Actions a = new Actions(driver);
		a.moveToElement(target).perform();
	}
	public void dragAndDrop(WebElement source, WebElement target) {
		Actions a = new Actions(driver);
		a.dragAndDrop(source, target).perform();
	}
	public void doubleClick(WebElement element) {
		Actions a = new Actions(driver);
		a.doubleClick(element).perform();
	}
	public void rightClick(WebElement element) {
		Actions a = new Actions(driver);
		a.contextClick(element).perform();
	}
	public void alertAccept() {
		Alert a = driver.switchTo().alert();
		a.accept();
	}
	public void alertdismiss() {
		Alert a = driver.switchTo().alert();
		a.dismiss();
	}
	public void alertSendKeys(String txt) {
		Alert a = driver.switchTo().alert();
		a.sendKeys(txt);
	}
	public void alertGetText() {
		Alert a = driver.switchTo().alert();
		a.getText();
	}
	public String getData(String sheet, int rowNum, int cellNum) throws IOException {
		String value = null;
		File loc = new File("C:\\Users\\NIVIN\\eclipse-workspace\\Maven\\Excel\\Adactin hotel.xlsx");
		FileInputStream fIn = new FileInputStream(loc);
		Workbook w = new XSSFWorkbook(fIn);
		Sheet s = w.getSheet(sheet);
		Row r = s.getRow(rowNum);
		Cell c = r.getCell(cellNum);
		int type = c.getCellType();
		if (type == 1) {
			value = c.getStringCellValue();
		} else if (type == 0) {
			if (DateUtil.isCellInternalDateFormatted(c)) {
				Date date = c.getDateCellValue();
				SimpleDateFormat ss = new SimpleDateFormat("DD/MM/YYYY");
				value = ss.format(date);
			} else {
				double data = c.getNumericCellValue();
				long l = (long) data;
				value = String.valueOf(l);
			}
		}
		return value;
	}
}
