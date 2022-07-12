package br.com.mateussilvasant.narutomugen.core;

import br.com.mateussilvasant.narutomugen.core.chars.HatakeKakashi;
import br.com.mateussilvasant.narutomugen.core.events.player.EControlCommand;
import br.com.mateussilvasant.narutomugen.core.events.player.Player;
import br.com.mateussilvasant.narutomugen.core.gamecore.GameManager;
import br.com.mateussilvasant.narutomugen.core.gamecore.constants.EDirection;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.IGameRenderer;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.textures.ITextureImage;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.textures.TextureImage;
import br.com.mateussilvasant.narutomugen.core.sceneries.StageBase;

public class NarutoMugen extends GameManager {

	private StageBase stageBase;

	private Player playerOne;

	private Player playerTwo;

	private void initPlayers() {

		int x1 = (int) (stageBase.getX() + (stageBase.getX() * 0.80));
		int x2 = (int) (x1 + 500);

		int y = (int) (stageBase.getY() + (stageBase.getY() * 0.40));

		playerOne = new Player(new HatakeKakashi(
				x1,
				y, EDirection.RIGHT), keyboardListener(),
				EControlCommand.PLAYER1);

		playerTwo = new Player(new HatakeKakashi(x2, y, EDirection.LEFT), keyboardListener(), EControlCommand.PLAYER2);
	}

	private void loadScenary() {
		try {

			ITextureImage cenario = new TextureImage();
			cenario.loadTextureByFile("/cenarios/konohavila/konohavila.png", SCALE_WINDOW);

			stageBase.addScene(cenario);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void renderScenary() {
		gameRender().render(stageBase.getFirstScene(), 0, stageBase.getX(), stageBase.getY());
	}

	@Override

	public void initGame() {
		stageBase = new StageBase();
		loadScenary();
		stageBase.moveToCenter(WORLD_WIDTH, WORLD_HEIGHT);
		initPlayers();
	}

	@Override
	public void releaseGame() {

	}

	@Override
	public void update(double deltaTime, IGameRenderer gameRenderer) {

		renderScenary();
		playerOne.updateMechanics(deltaTime, gameRenderer);
		playerOne.render(deltaTime, gameRenderer);

		playerTwo.updateMechanics(deltaTime, gameRenderer);
		playerTwo.render(deltaTime, gameRenderer);
	}

	@Override
	public void resizeWindow() {
		// todo
	}

}
