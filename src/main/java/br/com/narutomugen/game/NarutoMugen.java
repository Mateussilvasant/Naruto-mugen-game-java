package br.com.narutomugen.game;

import br.com.narutomugen.game.app.JogoAdapter;
import br.com.narutomugen.game.app.JogoApp;
import br.com.narutomugen.game.entities.character.chars.HatakeKakashi;
import br.com.narutomugen.game.events.player.ETipoControle;
import br.com.narutomugen.game.events.player.Jogador;
import br.com.narutomugen.game.sceneries.GerenciadorCenarios;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class NarutoMugen extends JogoAdapter {

	private int cenarioEscolhido;
	private GerenciadorCenarios cenarios;
	private Jogador playerOne;
	private Jogador playerTwo;

	public NarutoMugen(double largura, double altura, JogoApp view) {
		super(largura, altura, view);
	}

	public void prepararPartida() {
		cenarios = new GerenciadorCenarios();
		carregarCenario(cenarios);
		preparaPlayer();
	}

	private void preparaPlayer() {

		double alt = getJogoApp().getAlturaWindow();
		double larg = getJogoApp().getLarguraWindow();

		int x1 = (int) (larg * 0.08);
		int x2 = (int) (larg * 0.80);

		int y = (int) (alt * 0.843);

		playerOne = new Jogador(new HatakeKakashi(x1, y, getRender(), false), getTecladoEvent(), ETipoControle.PLAYER1);
		playerTwo = new Jogador(new HatakeKakashi(x2, y, getRender(), true), getTecladoEvent(), ETipoControle.PLAYER2);
	}

	private void limparContexto() {
		getRender().getContexto().setGlobalAlpha(1.0);
		getRender().getContexto().setGlobalBlendMode(BlendMode.SRC_OVER);
		getRender().getContexto().setFill(Color.BLACK);
		getRender().getContexto().fillRect(0, 0, getJogoApp().getLarguraWindow(), getJogoApp().getAlturaWindow());
	}

	@Override
	public void atualizar() {
		limparContexto();
		cenario();


		playerOne.atualizarMecanicas();
		playerOne.atualizarAnimacoes();


		getJogoApp().getEstagio().setTitle("FPS: " + getFPS());
	}

	private void carregarCenario(GerenciadorCenarios gc) {
		try {

			cenarioEscolhido = 0;

			ImageView imageView = new ImageView(
					new Image(NarutoMugen.class.getResourceAsStream("/cenarios/konohavila/konohavila.png"),
							getJogoApp().getLarguraWindow(), getJogoApp().getAlturaWindow(), false, false));

			gc.Cenarios.add(imageView);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cenario() {
		getRender().renderizar(cenarios.Cenarios, cenarioEscolhido, cenarios);
	}

}
