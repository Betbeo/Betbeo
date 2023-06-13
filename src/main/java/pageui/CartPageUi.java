package pageui;

public class CartPageUi {

	public static final String CART_BREADCRUMB = "xpath=//li[@class='active breadcrumb-title']";
	public static final String ROW_IN_TABLE = "xpath=//div[@class='form-cart']//tbody//tr";
	public static final String CART_QUANTITY = "xpath=//div[@class='form-cart']//tbody//tr[%s]//input";
	public static final String CART_REMOVE = "xpath=//div[@class='form-cart']//tbody//tr[%s]//a[@class='header-cart-list__remove']";
	public static final String CART_IMG_ITEM = "xpath=//div[@class='form-cart']//tbody//tr[%s]//img";
	public static final String UPDATE_BTN = "xpath=//button[text()='Cập nhật']";
	public static final String NO_ITEM = "xpath=//h2[text()='Không có sản phẩm nào trong giỏ hàng']";
	
}
