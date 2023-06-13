package pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageui.CollectionPageUi;

public class CollectionPage extends BasePage {

	private WebDriver driver;

	public CollectionPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void addProductToCart(int number) {
		attachTextContent("Chọn " + number + " cho vào giỏ hàng");
		List<WebElement> products = getListWebElement(driver, CollectionPageUi.PRODUCT_CONTAINER);
		for (int i = 0; i < number; i++) {
			scrollToElement(driver, products.get(i));
			hoverToElement(driver, products.get(i));
			sleepInSecond(1);
			products.get(i).findElement(By.className("product-item__add")).click();
			sleepInSecond(1);
		}
	}
	
	public String viewDetailProduct(int i) {
		attachTextContent("Xem chi tiết sản phẩm");
		List<WebElement> products = getListWebElement(driver, CollectionPageUi.PRODUCT_CONTAINER);
			scrollToElement(driver, products.get(i));
			hoverToElement(driver, products.get(i));
			sleepInSecond(1);
			String itemName = products.get(i).findElement(By.className("product-item__name")).findElement(By.tagName("h3")).getText();
			products.get(i).findElement(By.className("product-item__more")).click();
			sleepInSecond(1);
			return itemName;	
	}
	
	public void inputQuantity(int quantity) {
		attachTextContent("Nhập số lượng: " + quantity);
		scrollToElement(driver, CollectionPageUi.QUANTITY_INPUT);
		sendkeyToElement(driver, CollectionPageUi.QUANTITY_INPUT, quantity + "");
	}
	
	public void clickAddBtn() {
		attachTextContent("Click Mua hàng");
		clickToElement(driver, CollectionPageUi.ADD_BTN);
		sleepInSecond(5);
	}
}
