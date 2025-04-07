package br.com.alura.leilao.lance;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.leiloes.LeiloesPage;
import br.com.alura.leilao.login.LoginPage;

public class LanceTest {

    private LeiloesPage paginaLeiloes;
    private LancePage paginaLance;

    @AfterEach
    public void afterEach() {
        this.paginaLance.fechar();
    }

    @Test
    public void deveriaCadastrarLance() {
        String username = "fulano";
        String valorLance = "500";
        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        LoginPage paginaLogin = new LoginPage();
        paginaLogin.preencherFormularioLogin(username, "pass");
        this.paginaLeiloes = paginaLogin.efetuarLogin();
        this.paginaLance = this.paginaLeiloes.carregarPaginaLance();
        this.paginaLance.preencherCampoLance(valorLance);

        Assert.assertTrue(this.paginaLance.isPaginaAtual());
        Assert.assertTrue(this.paginaLance.isLanceCadastrado(username, valorLance, hoje));
        Assert.assertTrue(this.paginaLance.isMensagemSucessoVisivel());
    }

    @Test
    public void naoDeveriaCadastrarLancesSeguidosMesmoUsuario() {
        String username = "ciclano";
        String valorPrimeiroLance = "600";
        String valorSegundoLance = "700";
        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        
        LoginPage paginaLogin = new LoginPage();
        paginaLogin.preencherFormularioLogin(username, "pass");
        this.paginaLeiloes = paginaLogin.efetuarLogin();
        this.paginaLance = this.paginaLeiloes.carregarPaginaLance();
        this.paginaLance.preencherCampoLance(valorPrimeiroLance);
        this.paginaLance.preencherCampoLance(valorSegundoLance);

        Assert.assertTrue(this.paginaLance.isPaginaAtual());
        Assert.assertFalse(this.paginaLance.isLanceCadastrado(username, valorSegundoLance, hoje));
        Assert.assertTrue(this.paginaLance.isLanceCadastrado(username, valorPrimeiroLance, hoje));
        Assert.assertTrue(this.paginaLance.isMensagemVelidacaoVisivel());
    }

    @Test
    public void naoDeveriaPermitirLanceVazio() {
        String username = "fulano";
        String valorLance = "";

        LoginPage paginaLogin = new LoginPage();
        paginaLogin.preencherFormularioLogin(username, "pass");
        this.paginaLeiloes = paginaLogin.efetuarLogin();
        this.paginaLance = this.paginaLeiloes.carregarPaginaLance();
        this.paginaLance.preencherCampoLance(valorLance);

        Assert.assertTrue(this.paginaLance.isPaginaLance());
        Assert.assertTrue(this.paginaLance.isMensagemValidacaoLanceVisivel());
    }
}
