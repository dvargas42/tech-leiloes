package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {

    private LoginPage paginaLogin;

    @BeforeEach
    public void beforeEach() {
        this.paginaLogin = new LoginPage();
    }

    @AfterEach
    public void after() {
        this.paginaLogin.fechar();
    }
    
    @Test
    public void deveriaEfetuarLoginComDadosValidos() throws InterruptedException {
        String username = "fulano";
        this.paginaLogin.preencherFormularioLogin(username, "pass");
        this.paginaLogin.efetuarLogin();

        Thread.sleep(1000);

        Assert.assertFalse(this.paginaLogin.isPaginaDeLogin());
        Assert.assertEquals(username, this.paginaLogin.getNomeUsuarioLogado());
    }

    @Test
    public void naoDeveriaLogarComDadosInvalidos() throws InterruptedException {
        this.paginaLogin.preencherFormularioLogin("fulano", "senhaIncorreta");
        this.paginaLogin.efetuarLogin();

        Thread.sleep(1000);
        Assert.assertTrue(this.paginaLogin.isPaginaDeLoginErro());
        Assert.assertTrue(this.paginaLogin.contemTexto("Usuário e senha inválidos."));
        Assert.assertNull(this.paginaLogin.getNomeUsuarioLogado());
    }

    @Test
    public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
        this.paginaLogin.navegarParaPaginaDeLances();

        Assert.assertTrue(this.paginaLogin.isPaginaDeLogin());
        Assert.assertFalse(this.paginaLogin.contemTexto("Leilões cadastrados"));
    }
   
}
