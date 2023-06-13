package commons;

import static org.testng.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import enums.BrowserList;
import factorybrowsers.BrowserNotSupportException;
import factorybrowsers.ChromeDriverManager;
import factorybrowsers.EdgeDriverManager;
import io.qameta.allure.Allure;
import utilities.ConvertVideoToMp4;
import utilities.DataHelper;
import utilities.ScreenRecorderHelper;

public class BaseTest {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	protected DataHelper faker = DataHelper.getData();
	protected static String browserName, domain;

	@BeforeSuite(alwaysRun = true)
	@Parameters({ "browserName", "domain" })
	public void initBeforeSuite(String browserName, String domain) {
		BaseTest.browserName = browserName;
		BaseTest.domain = domain;
		deleteTestRecordings();
		deleteVideoConverted();
		deleteAllureReport();
	}
	
	@BeforeClass(alwaysRun = true)
	public void beforeClass(ITestContext iTestContext) throws Exception {
		String className = iTestContext.getCurrentXmlTest().getClasses().stream().findFirst().get().getName();
		System.out.println("Class test: " + className);
		ScreenRecorderHelper.startRecord("Test-recording_" + className);
	}
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method method) {
		System.out.println("Method test: " + method.getName());
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) {
		try {
			if (result.getStatus() == ITestResult.FAILURE) {
				System.out.println("Test case execution status is FAILURE");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterClass(alwaysRun = true)
	public void afterScreenTest() throws Exception {
		closeBrowserDriver();
		ScreenRecorderHelper.stopRecord();
		attachVideoToAllure();
	}

	protected WebDriver getBrowserDriver() {
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());

		System.out.println("Run with - " + browserName);

		switch (browser) {
		case CHROME:
			driver.set(new ChromeDriverManager().getBrowserDriver());
			break;
		case EDGE:
			driver.set(new EdgeDriverManager().getBrowserDriver());
			break;
		default:
			throw new BrowserNotSupportException(browserName);
		}

		driver.get().manage().window().maximize();
		driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.longTimeout));
		return driver.get();
	}

	public WebDriver getDriverInstance() {
		return driver.get();
	}

	public void attachVideoToAllure() {
		try {
			String sourceUrl = GlobalConstants.testRecordings + File.separator + ScreenRecorderHelper.fileName;
			String tagertUrl = GlobalConstants.videoConverted + File.separator + "Video Converted_"
					+ faker.generateFakeDateTimeNow("dd-MM-yyyy HH-mm-ss") + ".mp4";
			ConvertVideoToMp4.convert(sourceUrl, tagertUrl);
			byte[] byteArr = IOUtils.toByteArray(new FileInputStream(tagertUrl));
			Allure.addAttachment("Screen Recording at " + faker.generateFakeDateTimeNow("dd/MM/yyyy HH:mm:ss"),
					"video/mp4", new ByteArrayInputStream(byteArr), "mp4");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteAllureReport() {
		try {
			String pathFolderDownload = GlobalConstants.allureResult;
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	public void deleteTestRecordings() {
		String path = GlobalConstants.testRecordings;
		File directory = new File(path);
		File[] files = directory.listFiles();
		for (File file : files) {
			file.delete();
		}
	}
	
	public void deleteVideoConverted() {
		String path = GlobalConstants.videoConverted;
		File directory = new File(path);
		File[] files = directory.listFiles();
		for (File file : files) {
			file.delete();
		}
	}

	protected void closeBrowserDriver() {
		String cmd = null;
		try {
			String osName = System.getProperty("os.name").toLowerCase();

			String driverInstanceName = driver.toString().toLowerCase();

			String browserDriverName = null;

			if (driverInstanceName.contains("chrome")) {
				browserDriverName = "chromedriver";
			} else if (driverInstanceName.contains("internetexplorer")) {
				browserDriverName = "IEDriverServer";
			} else if (driverInstanceName.contains("firefox")) {
				browserDriverName = "geckodriver";
			} else if (driverInstanceName.contains("edge")) {
				browserDriverName = "msedgedriver";
			} else if (driverInstanceName.contains("opera")) {
				browserDriverName = "operadriver";
			} else {
				browserDriverName = "safaridriver";
			}

			if (osName.contains("window")) {
				cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
			} else {
				cmd = "pkill " + browserDriverName;
			}

			if (driver != null) {
				driver.get().manage().deleteAllCookies();
				driver.get().quit();
				driver.remove();
			}
		} catch (Exception e) {
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void assert2List(List<String> actual, List<String> expected) {
		assertEquals(actual.size(), expected.size());
		for (int i = 0; i < expected.size(); i++) {
			assertEquals(actual.get(i), expected.get(i));
		}
	}

}
