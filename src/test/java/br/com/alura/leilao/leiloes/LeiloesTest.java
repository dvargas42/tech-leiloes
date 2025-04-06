package br.com.alura.leilao.leiloes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.login.LoginPage;

public class LeiloesTest {

    private LeiloesPage paginaLeiloes;
    private CadastroLeilaoPage paginaCadastro;

    @BeforeEach
    public void beforeEach() {
        LoginPage paginaLogin = new LoginPage();
        paginaLogin.preencherFormularioLogin("fulano", "pass");
        this.paginaLeiloes = paginaLogin.efetuarLogin();
        this.paginaCadastro = this.paginaLeiloes.carregarFormulario();
    }

    @AfterEach
    public void after() {
        this.paginaLeiloes.fechar();
    }

    @Test
    public void deveriaCadastrarLeilao() throws InterruptedException {
        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilão do dia " + hoje;
        String valorInicial = "500.00";

        this.paginaLeiloes = this.paginaCadastro.cadastrarLeilao(nome, valorInicial, hoje);
        Assert.assertTrue(this.paginaLeiloes.isLeilaoCadastrado(nome, valorInicial, hoje));
    }

    @Test
    public void deveriaValidarCadastroLeilao() throws InterruptedException {
        this.paginaLeiloes = this.paginaCadastro.cadastrarLeilao("", "", "");

        Assert.assertFalse(this.paginaCadastro.isPaginaAtual());
        Assert.assertTrue(this.paginaLeiloes.isPaginaAtual());
        Assert.assertTrue(this.paginaCadastro.isMensagensValidacaoVisiveis());
    }
}
