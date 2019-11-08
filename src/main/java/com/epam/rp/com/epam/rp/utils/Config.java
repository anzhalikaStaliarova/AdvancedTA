package com.epam.rp.com.epam.rp.utils;

import org.apache.commons.configuration2.CompositeConfiguration;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.io.FileHandler;

import java.io.File;
import java.nio.file.Paths;


public class Config {
    private static Configuration config;

    private static final String KEY_CHROME_BROWSER_BETA_PATH_WIN = "config.browser.chrome.beta.win";
    private static final String KEY_BROWSER = "config.browser";
    private static final String KEY_APP_NAME = "config.app.name";
    private static final String KEY_APP_URL = "config.app.url";
    private static final String REGULAR_USER_LOGIN = "config.regular.user.login";
    private static final String REGULAR_USER_PASSWORD="config.regular.user.password";
    private static final String ADMIN_LOGIN="config.admin.login";
    private static final String ADMIN_PASSWORD="config.admin.password";

    private static final String TEST_CONFIG_PROPERTIES_FILENAME = "testConfig.properties";
    private static final String KEY_PARAMS_WAITSECS_MAXTIMEOUT = "config.params.waitsecs.maxtimeout";
    private static final String TEST_COFFIG_PROPERTIES_OVERRIDE_FILENAME = "testConfigOverride.properties";
    public static final String RESOURCES_DIR = System.getProperty("resources.directory.path", Paths.get("src", "main", "resources").toAbsolutePath().toString());
    private static final String WEBDRIVERS_FOLDER = System.getProperty("webdriver.install.directory",
            Paths.get(RESOURCES_DIR, "drivers").toAbsolutePath().toString());
    static {
        try {
            initialize();
        } catch (Throwable t) {
            throw new IllegalStateException("Can't initialize Config object", t);
        }
    }

    public static final String getBrowser() {
        return getProperty(KEY_BROWSER);
    }

    public static final String getAppName() {
        return getProperty(KEY_APP_NAME);
    }

    public static  String getProperty(String propertyName) {
        return getProperty(propertyName, true);
    }
    public static final String getDriversFolderPath() {
        return WEBDRIVERS_FOLDER;
    }
    private static void initialize() throws ConfigurationException {

        if (config != null) {
            return;
        }

        File propertiesFile = getPropertiesFile(RESOURCES_DIR, TEST_CONFIG_PROPERTIES_FILENAME);
        File overrideFile = getPropertiesFile(RESOURCES_DIR, TEST_COFFIG_PROPERTIES_OVERRIDE_FILENAME);

        if (overrideFile.exists()) {
            config = loadConfigFiles(overrideFile, propertiesFile);
        } else {
            config = loadConfigFiles(propertiesFile);
        }

        System.getProperties().forEach((key, value) -> {
            config.setProperty((String) key, value);
        });


    }
    public static final String getLoginPageURL() {
        return getProperty(KEY_APP_URL);
    }

    public static final String getRegularUserLogin() {
        return getProperty(REGULAR_USER_LOGIN);
    }

    private static Configuration loadConfigFiles(File... files) throws ConfigurationException {

        CompositeConfiguration config = new CompositeConfiguration();

        for (File file : files) {
            PropertiesConfiguration propConfig = new PropertiesConfiguration();
            FileHandler propConfigFileHandler = new FileHandler(propConfig);
            propConfigFileHandler.load(file);

            config.addConfiguration(propConfig);
        }

        return config;
    }

    private static File getPropertiesFile(String dir, String filename) {
        File configFileDir = new File(dir);
        if (!configFileDir.exists()) {
            throw new IllegalStateException("Cannot find test properties file; parent dir " + dir + " missing");
        }

        File propertiesFile = new File(configFileDir, filename);
        if (!propertiesFile.exists() && filename.equalsIgnoreCase(TEST_CONFIG_PROPERTIES_FILENAME)) {
            throw new IllegalStateException("Cannot find test properties file; file does not exist: " + propertiesFile.getAbsolutePath());
        }

        return propertiesFile;
    }

    public static String getProperty(String propertyName, boolean required) {

        String value = null;

        try {

            value = config.getString(propertyName);

            if (required && (value == null)) {
                throw new IllegalArgumentException("No such property found: " + propertyName);
            }

        } catch (Throwable t) {
            t.printStackTrace();
        }

        return value;
    }
    public static final String getChromeBetaPath() {
        String path = getProperty(Config.KEY_CHROME_BROWSER_BETA_PATH_WIN);
        return Paths.get(path).toAbsolutePath().toString();
    }

    private static long getLong(String propertyName) {
        return Long.parseLong(getProperty(propertyName));
    }
    public static final long getWaitSecsMaxTimeout() {
        return getLong(KEY_PARAMS_WAITSECS_MAXTIMEOUT);
    }
}