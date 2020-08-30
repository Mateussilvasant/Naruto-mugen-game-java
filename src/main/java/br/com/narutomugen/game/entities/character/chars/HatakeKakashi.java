package br.com.narutomugen.game.entities.character.chars;

import br.com.narutomugen.game.animation.Sprite;
import br.com.narutomugen.game.entities.character.Personagem;
import br.com.narutomugen.game.entities.character.actions.Actions;
import br.com.narutomugen.game.graphics.Render;
import br.com.narutomugen.game.graphics.textures.TexturaSprite;
import br.com.narutomugen.game.manager.actions.ActionComponent;
import br.com.narutomugen.game.particles.EmissorFogo;

public class HatakeKakashi extends Personagem {

	public EmissorFogo katonJutsu;

	public HatakeKakashi(int x, int y, Render render, boolean invertido) {
		super(x, y, invertido);
		carregarRecursos(render, invertido);
		carregarEstados();
	}

	private void carregarEstados() {
		controleEstado.adicionarEstado(new ActionComponent(Actions.PODER_1) {

			@Override
			public boolean action() {
				katonJutsu.update(getEixoX(), getEixoY() - getWidth());
				return false;
			}
		});
	}

	private void carregarRecursos(Render render, boolean invertido) {
		TexturaSprite texturaParado = new TexturaSprite();
		texturaParado.carregarTextura("/personagens/kakashi/tiles/Parado", 1.0);

		TexturaSprite texturaCorrer = new TexturaSprite();
		texturaCorrer.carregarTextura("/personagens/kakashi/tiles/Correndo", 1.0);

		TexturaSprite texturaPulando = new TexturaSprite();
		texturaPulando.carregarTextura("/personagens/kakashi/tiles/Pulando", 1.0);

		TexturaSprite texturaIntro = new TexturaSprite();
		texturaIntro.carregarTextura("/personagens/kakashi/tiles/Intro", 1.0);

		mapaSprites.put(Actions.CORRER_DIREITA.getValue(),
				new Sprite(render, texturaCorrer, 7, invertido ? false : false));
		mapaSprites.put(Actions.CORRER_ESQUERDA.getValue(),
				new Sprite(render, texturaCorrer, 7, invertido ? true : true));
		mapaSprites.put(Actions.PARADO.getValue(), new Sprite(render, texturaParado, 7, invertido));
		mapaSprites.put(Actions.PULAR.getValue(), new Sprite(render, texturaPulando, 7, invertido));
		mapaSprites.put(Actions.INICIAL.getValue(), new Sprite(render, texturaIntro, 1, invertido, false));

		katonJutsu = new EmissorFogo(render);
	}

	public void atualizarMecanicas() {
		super.atualizarMecanicas();
	}

	@Override
	public void executarPoder_1() {
		controleEstado.transitar(Actions.PODER_1);
	}

	@Override
	public void atualizarAnimacoes() {
		if (controleEstado.comparar(Actions.PODER_1)) {
			controleEstado.getEstadoAtual().dispatch();
		}

	}

	public void parar() {

		if (!controleEstado.verificaEstadoAtual(Actions.INICIAL.getValue())) {
			if (!controleEstado.getEstadoAtual().isRepeat()) {
				controleEstado.transitar(Actions.PARADO);
				katonJutsu.resetar();
			}
		}

	}

}
