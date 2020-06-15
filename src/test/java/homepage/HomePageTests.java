package homepage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import base.BaseTests;
import pages.LoginPage;
import pages.ProdutoPage;

public class HomePageTests extends BaseTests {
	
	@Test
	public void testContarProdutos_oitoProdutosDiferentes() {
		carregarPaginaInicial();
		assertThat(homePage.contarProdutos(), is(8));
	}
	
	@Test
	public void testValidarCarrinhoZerado_ZeroItensNoCarrinho() {
		int produtosNoCarrinho = homePage.obterQuantidadeProdutosNoCarrinho();
		assertThat(produtosNoCarrinho, is(0));
	}
	
	@Test
	public void testValidarDetalhesDoProduto_DescricaoEValorIguais() {
		int indice = 0;
		
		String nomeProduto_HomePage = homePage.obterNomeProduto(indice);
		String precoProduto_HomePage = homePage.obterprecoProduto(indice);
		
		ProdutoPage produtoPage = homePage.clicarProduto(indice);
		
		String nomeProduto_ProdutoPage = produtoPage.obterNomeProduto();
		String precoProduto_ProdutoPage = produtoPage.obterPrecoProduto();
		
		assertThat(nomeProduto_HomePage.toUpperCase(), is(nomeProduto_ProdutoPage.toUpperCase()));
		assertThat(precoProduto_HomePage, is(precoProduto_ProdutoPage));
	}
	
	@Test
	public void testLoginComSucesso_UsuarioLogado() {
		//Clicar no btn Sign na home page
		LoginPage loginPage = homePage.clicarBotaoSignIn();
		
		//Preecher usuario e senha
		loginPage.preencherEmail("murillo@test.com");
		loginPage.preencherPassword("q1w2e3r4t5*");
		
		//Clicar no btn Sign In para logar
		loginPage.clicarBotaoSignIn();
		
		//Validar se o usuario está logado de fato
		assertThat(homePage.estaLogado("Murillo Pereira"), is(true));
				
	}
}
