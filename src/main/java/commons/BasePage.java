package commons;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Allure;

public class BasePage {

	//Open URL cua.web
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
		sleepInSecond(5);
	}
	
	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	//Lam.moi page hien.tai
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	//Find phan.tu.tren.page
	private By getByLocator(String locatorType) {
		By by = null;
		if (locatorType.startsWith("ID=") || locatorType.startsWith("Id=") || locatorType.startsWith("id=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("CLASS=") || locatorType.startsWith("Class=") || locatorType.startsWith("class=")) {
			by = By.className(locatorType.substring(6));
		} else if (locatorType.startsWith("NAME=") || locatorType.startsWith("Name=") || locatorType.startsWith("name=")) {
			by = By.name(locatorType.substring(5));
		} else if (locatorType.startsWith("CSS=") || locatorType.startsWith("Css=") || locatorType.startsWith("css=")) {
			by = By.cssSelector(locatorType.substring(4));
		} else if (locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("xpath=") || locatorType.startsWith("XPath=")) {
			by = By.xpath(locatorType.substring(6));
		} else {
			throw new RuntimeException("Locator type is not supported!");
		}
		return by;
	}

	private String getDynamicXpath(String locatorType, String... dynamicValues) {
		if (locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("xpath=") || locatorType.startsWith("XPath=")) {
			locatorType = String.format(locatorType, (Object[]) dynamicValues);
		}
		return locatorType;
	}

	public WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}

	public List<WebElement> getListWebElement(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}

	//Thuc.hien.thao.tac.nhap.chuot
	public void clickToElement(WebDriver driver, String locatorType) {
		highlightElement(driver, locatorType);
		getWebElement(driver, locatorType).click();
	}

	public void clickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		highlightElement(driver, locatorType, dynamicValues);
		getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();
	}

	
	public void doubleClickToElement(WebDriver driver, String locatorType) {
		highlightElement(driver, locatorType);
		Actions actions = new Actions(driver);
		WebElement element = getWebElement(driver, locatorType);
		actions.doubleClick(element).perform();
	}

	public void doubleClickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		highlightElement(driver, locatorType, dynamicValues);
		Actions actions = new Actions(driver);
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		actions.doubleClick(element).perform();
	}

	//Gui.gtri.vao.cac.phan.tu
	public void sendkeyToElement(WebDriver driver, String locatorType, String textValue) {
		highlightElement(driver, locatorType);
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}

	public void sendkeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValues) {
		highlightElement(driver, locatorType, dynamicValues);
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}

	//Truy.xuat.thuoc.tinh
	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}

	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getAttribute(attributeName);
	}

	//Truy.xuat text
	public String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText();
	}

	public String getElementText(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getText();
	}

	//Truy.xuat.gia.tri CSS
	public String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	}

	public String getElementCssValue(WebDriver driver, String locatorType, String propertyName, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getCssValue(propertyName);
	}

	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	public int getElementSize(WebDriver driver, String locatorType) {
		return getListWebElement(driver, locatorType).size();
	}

	public int getElementSize(WebDriver driver, String locatorType, String... dynamicValues) {
		return getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).size();
	}

	//Check hien.thi
	public boolean isElementDisplayed(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isDisplayed();
	}

	public boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
	}

	public boolean isElementUndisplayed(WebDriver driver, String locatorType) {
		overrideImplicitTimeout(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, locatorType);
		overrideImplicitTimeout(driver, longTimeout);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isElementUndisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		overrideImplicitTimeout(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		overrideImplicitTimeout(driver, longTimeout);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public void overrideImplicitTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut));
	}

	//Kich hoat, kha dung
	public boolean isElementEnabled(WebDriver driver, String locatorType) {
		highlightElement(driver, locatorType);
		return getWebElement(driver, locatorType).isEnabled();
	}

	public boolean isElementEnabled(WebDriver driver, String locatorType, String... dynamicValues) {
		highlightElement(driver, locatorType, dynamicValues);
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}

	public boolean isElementSelected(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isSelected();
	}

	public void switchToFrameIframe(WebDriver driver, String locatorType, String... dynamicValues) {
		waitForElementVisible(driver, getDynamicXpath(locatorType, dynamicValues));
		driver.switchTo().frame(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String locatorType) {
		highlightElement(driver, locatorType);
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}

	public void hoverMouseToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		highlightElement(driver, locatorType, dynamicValues);
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues))).perform();
	}
	
	public void hoverToElement(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locatorType), key).perform();
	}

	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)), key).perform();
	}

	public void highlightElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locatorType);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void highlightElement(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	//Click to element using Java.script
	public void clickToElementByJS(WebDriver driver, String locatorType) {
		highlightElement(driver, locatorType);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
	}

	//Click to element using Java.script
	public void clickToElementByJS(WebDriver driver, String locatorType, String... dynamicValues) {
		highlightElement(driver, locatorType, dynamicValues);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
	}

	//Scroll to element before interact
	public void scrollToElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
	}

	//Scroll to element before interact
	public void scrollToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
	}
	
	public void scrollToElement(WebDriver driver, WebElement element) {
		new Actions(driver).scrollToElement(element).perform();
	}

	//Remove attribute of element in DOM
	public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
	}

	//Wait for element is present on the DOM of a page and visible.
	public void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}

	//Wait for dynamic element is present on the DOM of a page and visible.
	public void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver,  Duration.ofSeconds(longTimeout));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	//Wait for all elements present on the web page that match the locator are visible.
	public void waitForAllElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver,  Duration.ofSeconds(longTimeout));
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}

	//Wait for all elements present on the web page that match the locator are visible.
	public void waitForAllElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver,  Duration.ofSeconds(longTimeout));
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	//Wait for element is either invisible or not present on the DOM.
	public void waitForElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver,  Duration.ofSeconds(longTimeout));
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}

	//Wait for dynamic element is either invisible or not present on the DOM.
	public void waitForElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver,  Duration.ofSeconds(longTimeout));
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	//Wait for element undisplayed in DOM or not in DOM and override implicit timeout
	public void waitForElementUndisplayed(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver,  Duration.ofSeconds(shortTimeout));
		overrideImplicitTimeout(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
		overrideImplicitTimeout(driver, longTimeout);
	}

	//Wait for dynamic element undisplayed in DOM or not in DOM and override implicit timeout
	public void waitForElementUndisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(shortTimeout));
		overrideImplicitTimeout(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
		overrideImplicitTimeout(driver, longTimeout);
	}

	//Wait for all elements from given list to be invisible
	public void waitForAllElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorType)));
	}

	//Wait for all elements from given list to be invisible
	public void waitForAllElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues))));
	}

	//Wait for element is visible and enabled such that you can click it.
	public void waitForElementClickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}

	//Wait for dynamic element is visible and enabled such that you can click it.
	public void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	//Screenshot attachments for Allure
	public static void takeScreenshot(WebDriver driver, String name) {
		Allure.addAttachment(name, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
	}
	
	public static void attachTextContent(String text) {
		Allure.addAttachment(text, "");
	}

	//timeoutInSecond
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private long longTimeout = GlobalConstants.longTimeout;
	private long shortTimeout = GlobalConstants.shortTimeout;
}
