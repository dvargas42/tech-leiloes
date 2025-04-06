package br.com.alura.leilao;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class PageObject {
    
    protected WebDriver browser;

    public PageObject() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-password-manager-reauthentication");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-autofill-keyboard-accessory-view");
        options.addArguments("--disable-credentials-enable-service");
        options.addArguments("--disable-credential-saving");
        options.addArguments("--incognito");

        this.browser = new ChromeDriver(options);
        this.manageTimeouts();
    }

    public PageObject(WebDriver browser) {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        this.browser = browser;
        this.manageTimeouts();
    }

    public void fechar() {
        this.browser.quit();
    }

    public void manageTimeouts() {
        this.browser.manage().timeouts()
                .implicitlyWait(5, TimeUnit.SECONDS)
                .pageLoadTimeout(10, TimeUnit.SECONDS);
    }
}
