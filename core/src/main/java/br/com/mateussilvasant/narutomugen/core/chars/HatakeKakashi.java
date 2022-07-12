package br.com.mateussilvasant.narutomugen.core.chars;

import br.com.mateussilvasant.narutomugen.core.NarutoMugen;
import br.com.mateussilvasant.narutomugen.core.animation.Frame;
import br.com.mateussilvasant.narutomugen.core.entities.character.CharacterGame;
import br.com.mateussilvasant.narutomugen.core.entities.character.actions.ActionCommand;
import br.com.mateussilvasant.narutomugen.core.gamecore.constants.EDirection;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.IGameRenderer;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.textures.TextureImage;
import br.com.mateussilvasant.narutomugen.core.jutsus.katon.Goukakyuu;
import br.com.mateussilvasant.narutomugen.core.manager.actions.ActionComponent;

public class HatakeKakashi extends CharacterGame {

	public Goukakyuu katonJutsu;

	public HatakeKakashi(int x, int y, EDirection direction) {
		super(x, y, direction);
		carregarRecursos(direction);
		carregarEstados();
	}

	private void carregarEstados() {
		machineState.nextState(new ActionComponent(ActionCommand.PODER_1) {

			@Override
			public boolean action(double delta, IGameRenderer gameRenderer) {

				katonJutsu.update(delta, gameRenderer);

				return katonJutsu.isEmissionOver();
			}
		});
	}

	private void carregarRecursos(EDirection direction) {

		boolean invert = direction.equals(EDirection.LEFT) ? true : false;

		TextureImage texturaParado = new TextureImage();
		texturaParado.loadTexturesByFolder("/personagens/kakashi/tiles/Parado", NarutoMugen.SCALE_WINDOW);

		TextureImage texturaCorrer = new TextureImage();
		texturaCorrer.loadTexturesByFolder("/personagens/kakashi/tiles/Correndo", NarutoMugen.SCALE_WINDOW);

		TextureImage texturaPulando = new TextureImage();
		texturaPulando.loadTexturesByFolder("/personagens/kakashi/tiles/Pulando", NarutoMugen.SCALE_WINDOW);

		TextureImage texturaIntro = new TextureImage();
		texturaIntro.loadTexturesByFolder("/personagens/kakashi/tiles/Intro", NarutoMugen.SCALE_WINDOW);

		sprites.put(ActionCommand.CORRER_DIREITA.getValue(),
				new Frame(texturaCorrer, 7, invert ? EDirection.RIGHT : EDirection.RIGHT));

		sprites.put(ActionCommand.CORRER_ESQUERDA.getValue(),
				new Frame(texturaCorrer, 7, invert ? EDirection.LEFT : EDirection.LEFT));

		sprites.put(ActionCommand.PARADO.getValue(), new Frame(texturaParado, 7, direction));

		sprites.put(ActionCommand.PULAR.getValue(), new Frame(texturaPulando, 7, direction));

		sprites.put(ActionCommand.INICIAL.getValue(), new Frame(texturaIntro, 1, direction, false));

		katonJutsu = new Goukakyuu(1000, 500, EDirection.LEFT);

	}

	public void updateMechanics(double delta, IGameRenderer gameRenderer) {
		super.updateMechanics(delta, gameRenderer);
	}

	@Override
	public void runPower_01() {
		machineState.transitar(ActionCommand.PODER_1);
	}

	@Override
	public void render(double delta, IGameRenderer gameRenderer) {
		if (machineState.isCurrentState(ActionCommand.PODER_1)) {
			machineState.currentState().dispatch(delta, gameRenderer);
		}

	}

	public void parar() {

		if (!machineState.isCurrentState(ActionCommand.INICIAL.getValue())) {
			if (!machineState.currentState().isRepeat()) {
				machineState.transitar(ActionCommand.PARADO);
			}
		}

	}

}
