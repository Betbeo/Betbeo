package commons;

import java.io.File;

public class GlobalConstants {
    public static final String projectPath = System.getProperty("user.dir");
    public static final String testRecordings = projectPath + File.separator + "test-recordings";
    public static final String videoConverted = projectPath + File.separator + "video-converted";
    public static final String allureResult = projectPath + File.separator + "allure-results";
    public static final long shortTimeout = 5;

    public static final long longTimeout = 30;
    public static final long retryTest = 3;
}
