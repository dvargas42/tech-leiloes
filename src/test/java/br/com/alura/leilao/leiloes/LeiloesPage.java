package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.alura.leilao.PageObject;
import br.com.alura.leilao.lance.LancePage;

public class LeiloesPage extends PageObject{

    private static final String URL_LEILOES = "http://localhost:8080/leiloes";
    private static final String URL_CADASTRO_LEILOES = "http://localhost:8080/leiloes/new";

    public LeiloesPage(WebDriver browser) {
        super(browser);
    }

    public CadastroLeilaoPage carregarFormulario() {
        this.browser.navigate().to(URL_CADASTRO_LEILOES);
        return new CadastroLeilaoPage(browser);
    }

    public boolean isLeilaoCadastrado(String nome, String valor, String hoje) {
        WebElement linhaTabela = this.browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));
        WebElement colunaNome = linhaTabela.findElement(By.cssSelector("td:nth-child(1)"));
        WebElement colunaDataAbertura = linhaTabela.findElement(By.cssSelector("td:nth-child(2)"));
        WebElement colunaValorInicial = linhaTabela.findElement(By.cssSelector("td:nth-child(3)"));

        return colunaNome.getText().equals(nome) && 
                colunaDataAbertura.getText().equals(hoje) &&
                colunaValorInicial.getText().equals(valor);
    }

    public boolean isPaginaAtual() {
        return browser.getCurrentUrl().equals(URL_LEILOES);
    }

    public LancePage carregarPaginaLance() {
        browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:nth-child(2) td:last-child a")).click();
        return new LancePage(browser);
    }
}
