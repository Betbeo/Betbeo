package pageobject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageui.CartPageUi;

public class CartPage extends BasePage{

	private WebDriver driver;

	public CartPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void inputCartQuantity(int rowIndex, int value) {
		attachTextContent("Thay đổi số lượng mặt hàng của hàng " + rowIndex + " thành " + value);
		scrollToElement(driver,  CartPageUi.CART_QUANTITY, rowIndex + "");
		sendkeyToElement(driver, CartPageUi.CART_QUANTITY, value + "", rowIndex + "");
	}
	
	public void removeItem(int rowIndex) {
		attachTextContent("Xóa " + rowIndex + " khỏi giỏ hàng");
		scrollToElement(driver,  CartPageUi.CART_QUANTITY, rowIndex + "");
		clickToElement(driver, CartPageUi.CART_REMOVE, rowIndex + "");
	}
	
	public void clickUpdateBtn() {
		attachTextContent("Click nút Cập nhật");
		scrollToElement(driver, CartPageUi.UPDATE_BTN);
		clickToElement(driver, CartPageUi.UPDATE_BTN);
	}
	
	public void viewDetailItem(int rowIndex) {
		attachTextContent("Xem chi tiết sản phẩm từ giỏ hàng");
		scrollToElement(driver, CartPageUi.CART_IMG_ITEM, rowIndex + "");
		clickToElement(driver, CartPageUi.CART_IMG_ITEM, rowIndex + "");
	}
	
	
}
