package pageui;

public class SearchPageUi {

	public static final String SEARCH_BOX = "xpath=//input[@name='query' and @class='search-form__query']";
	public static final String SEARCH_BTN = "xpath=//button[@class='search-form__submit']";
	public static final String FAST_SEARCH_BOX = "xpath=//input[@id='search_small']";
	public static final String SEARCH_TITLE_KEY = "xpath=//li[@class='active breadcrumb-title']";
	public static final String NO_ITEM = "xpath=//p[text()='Không còn sản phẩm nào thỏa mãn.']";
	public static final String SEARCH_RESULT = "xpath=//div[contains(@class, 'is-product-item')]";
	public static final String WIDGET_SEARCH_RESULT = "xpath=//div[@id='ui-widget-search']//li[@class='ui-menu-item' and not(@id='acp_footer_acp')]";
}
