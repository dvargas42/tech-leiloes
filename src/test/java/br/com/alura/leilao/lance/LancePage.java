package br.com.alura.leilao.lance;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.alura.leilao.PageObject;

public class LancePage extends PageObject {
    
    private static final String URL_LEILOES = "http://localhost:8080/leiloes/2";
    private static final String URL_LANCE = "http://localhost:8080/lances";
    

    public LancePage(WebDriver browser) {
        super(browser);
    }

    public void preencherCampoLance(String valorLance) {
        this.browser.findElement(By.id("valorLance")).sendKeys(valorLance);
        this.browser.findElement(By.id("btnDarLance")).submit();
    }

    public boolean isPaginaAtual() {
        return browser.getCurrentUrl().equals(URL_LEILOES);
    }

    public boolean isPaginaLance() {
        return browser.getCurrentUrl().equals(URL_LANCE);
    }

    public boolean isLanceCadastrado(String username, String valorLance, String hoje) {
        WebElement linhaTabelaLances = this.browser.findElement(By.cssSelector("#lancesDados tbody tr:last-child"));
        return linhaTabelaLances.getText().contains(username) && 
                linhaTabelaLances.getText().contains(valorLance.replace(".", ",")) &&
                linhaTabelaLances.getText().contains(hoje);
    }

    public boolean isMensagemSucessoVisivel() {
        return browser.getPageSource().contains("Lance adicionado com sucesso!");
    }

    public boolean isMensagemVelidacaoVisivel() {
        return browser.getPageSource().contains("Lance invalido!");
    }

    public boolean isMensagemValidacaoLanceVisivel() {
        return browser.getPageSource().contains("não deve ser nulo");
    }
}
