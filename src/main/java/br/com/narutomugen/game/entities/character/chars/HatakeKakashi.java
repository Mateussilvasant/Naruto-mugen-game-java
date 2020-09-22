package br.com.narutomugen.game.entities.character.chars;

import br.com.narutomugen.game.animation.Sprite;
import br.com.narutomugen.game.entities.character.Personagem;
import br.com.narutomugen.game.entities.character.actions.ActionCommand;
import br.com.narutomugen.game.graphics.Render;
import br.com.narutomugen.game.graphics.textures.TexturaSprite;
import br.com.narutomugen.game.manager.actions.ActionComponent;
import br.com.narutomugen.game.particles.EmissorFogo;
import javafx.scene.layout.Pane;

public class HatakeKakashi extends Personagem {

	public EmissorFogo katonJutsu;

	public HatakeKakashi(int x, int y, Pane view, Render render, boolean invertido) {
		super(x, y, invertido);
		carregarRecursos(render, view,invertido);
		carregarEstados();
	}

	private void carregarEstados() {
		controleEstado.adicionarEstado(new ActionComponent(ActionCommand.PODER_1) {

			@Override
			public boolean action() {
				katonJutsu.update();
				return false;
			}
		});
	}

	private void carregarRecursos(Render render, Pane view, boolean invertido) {
		
		TexturaSprite texturaParado = new TexturaSprite();
		texturaParado.carregarTextura("/personagens/kakashi/tiles/Parado", 1.0);

		TexturaSprite texturaCorrer = new TexturaSprite();
		texturaCorrer.carregarTextura("/personagens/kakashi/tiles/Correndo", 1.0);

		TexturaSprite texturaPulando = new TexturaSprite();
		texturaPulando.carregarTextura("/personagens/kakashi/tiles/Pulando", 1.0);

		TexturaSprite texturaIntro = new TexturaSprite();
		texturaIntro.carregarTextura("/personagens/kakashi/tiles/Intro", 1.0);

		mapaSprites.put(ActionCommand.CORRER_DIREITA.getValue(),
				new Sprite(render, texturaCorrer, 7, invertido ? false : false));
		mapaSprites.put(ActionCommand.CORRER_ESQUERDA.getValue(),
				new Sprite(render, texturaCorrer, 7, invertido ? true : true));
		mapaSprites.put(ActionCommand.PARADO.getValue(), new Sprite(render, texturaParado, 7, invertido));
		mapaSprites.put(ActionCommand.PULAR.getValue(), new Sprite(render, texturaPulando, 7, invertido));
		mapaSprites.put(ActionCommand.INICIAL.getValue(), new Sprite(render, texturaIntro, 1, invertido, false));

		katonJutsu = new EmissorFogo(render,view);
		katonJutsu.load(146, 260);

	}

	public void atualizarMecanicas() {
		super.atualizarMecanicas();
	}

	@Override
	public void executarPoder_1() {
		controleEstado.transitar(ActionCommand.PODER_1);
	}

	@Override
	public void atualizarAnimacoes() {
		if (controleEstado.comparar(ActionCommand.PODER_1)) {
			controleEstado.getEstadoAtual().dispatch();
		}

	}

	public void parar() {

		if (!controleEstado.verificaEstadoAtual(ActionCommand.INICIAL.getValue())) {
			if (!controleEstado.getEstadoAtual().isRepeat()) {
				controleEstado.transitar(ActionCommand.PARADO);
				katonJutsu.resetar();
			}
		}

	}

}
