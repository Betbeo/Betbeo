package factorybrowsers;

import java.util.Collections;
import java.util.HashMap;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriverManager implements BrowserFactory {

	@Override
	public WebDriver getBrowserDriver() {
		ChromeOptions options = new ChromeOptions();
		options.setPageLoadStrategy(PageLoadStrategy.NONE);
		// InPrivateMode
		options.addArguments("--incognito");
		// disable Automation Info Bar
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		// AutoSave download
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		options.setExperimentalOption("prefs", chromePrefs);
		return WebDriverManager.chromedriver().capabilities(options).create();
	}

}
