package br.com.mateussilvasant.narutomugen.core.events.player;

import br.com.mateussilvasant.narutomugen.core.entities.character.CharacterGame;
import br.com.mateussilvasant.narutomugen.core.events.KeyboardListener;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.IGameRenderer;

public class Player {

	public CharacterGame characterControl;

	private String keys[];

	private KeyboardListener event;

	public Player(CharacterGame characterControl, KeyboardListener event, EControlCommand controlCommand) {
		setCharacterControl(characterControl);
		keys = controlCommand.getCommands();
		this.event = event;
	}

	public void updateMechanics(double delta, IGameRenderer gameRenderer) {
		listener();
		characterControl.updateMechanics(delta, gameRenderer);
	}

	public void render(double delta, IGameRenderer gameRenderer) {
		characterControl.render(delta, gameRenderer);
	}

	private void listener() {

		if (event.pressed(keys[0])) {
			characterControl.runRight();
		}

		if (event.pressed(keys[1])) {
			characterControl.runLeft();
		}

		if (event.pressed(keys[2])) {
			characterControl.jumpUp();
		}

		if (event.pressed(keys[3])) {
			characterControl.runPower_01();
		}

		if (event.noCommand()) {
			characterControl.stopCharacter();
		}

	}

	public void setCharacterControl(CharacterGame characterControl) {
		this.characterControl = characterControl;
	}

}
