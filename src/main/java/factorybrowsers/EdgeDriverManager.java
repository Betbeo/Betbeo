package factorybrowsers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeDriverManager implements BrowserFactory {

    @Override
    public WebDriver getBrowserDriver() {
    	EdgeOptions options = new EdgeOptions();
        // InPrivateMode
        options.addArguments("InPrivate");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        // disable notifications popup
        options.addArguments("--disable-notifications");
        // disable location popup
        options.addArguments("--disable-geolocation");
        // disable Automation Info Bar
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        // disable "Save password"
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        // AutoSave download
        HashMap<String, Object> edgePrefs = new HashMap<String, Object>();
        edgePrefs.put("profile.default_content_settings.popups", 0);
        options.setExperimentalOption("prefs", edgePrefs);
        return new EdgeDriver(options);
    }

}
