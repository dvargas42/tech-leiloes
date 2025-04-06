package br.com.alura.leilao;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloWorldSelenium {

    @Test
    public void hello() throws Exception {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:8080/leiloes");
        browser.quit();
    }
}
