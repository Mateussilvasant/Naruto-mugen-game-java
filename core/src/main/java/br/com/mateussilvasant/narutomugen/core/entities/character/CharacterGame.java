package br.com.mateussilvasant.narutomugen.core.entities.character;

import java.util.HashMap;

import br.com.mateussilvasant.narutomugen.core.animation.IFrame;
import br.com.mateussilvasant.narutomugen.core.entities.character.actions.ActionCommand;
import br.com.mateussilvasant.narutomugen.core.gamecore.constants.EDirection;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.IGameRenderer;
import br.com.mateussilvasant.narutomugen.core.manager.actions.ActionComponent;

public abstract class CharacterGame {

	protected double x;
	protected double y;
	protected double startY;
	protected double velocity;
	protected EDirection direction;
	protected double force;

	protected HashMap<Integer, IFrame> sprites;
	protected MachineState machineState;

	public CharacterGame(int x, int y, EDirection direction) {

		this.sprites = new HashMap<Integer, IFrame>();
		this.direction = direction;
		this.x = x;
		this.y = y;
		this.velocity = 250;
		this.startY = y;
		this.force = 20;

		machineState = new MachineState(ActionCommand.values().length);

		initActions();
		machineState.transitar(ActionCommand.INICIAL);

	}

	private void initActions() {
		actionToRunRight();
		actionToRunLeft();
		actionToStop();
		actionToJumping();
		actionToIntro();
	}

	private void actionToRunRight() {
		machineState.nextState(new ActionComponent(ActionCommand.CORRER_DIREITA) {

			@Override
			public boolean action(double delta, IGameRenderer gameRenderer) {
				direction = EDirection.RIGHT;
				x = x + (getVelocity() * delta);
				sprites.get(ActionCommand.CORRER_DIREITA.getValue()).renderFrame(getX(), getY(),
						gameRenderer);
				return false;
			}
		});
	}

	private void actionToRunLeft() {

		machineState.nextState(new ActionComponent(ActionCommand.CORRER_ESQUERDA) {

			@Override
			public boolean action(double delta, IGameRenderer gameRenderer) {
				direction = EDirection.LEFT;
				x = x - (getVelocity() * delta);
				sprites.get(ActionCommand.CORRER_ESQUERDA.getValue()).renderFrame(getX(), getY(),
						gameRenderer);
				return false;
			}
		});
	}

	private void actionToStop() {
		machineState.nextState(new ActionComponent(ActionCommand.PARADO) {

			@Override
			public boolean action(double delta, IGameRenderer gameRenderer) {
				IFrame stopSprite = sprites.get(ActionCommand.PARADO.getValue());
				checkCharacterDirection(stopSprite);
				stopSprite.renderFrame(getX(), getY(), gameRenderer);
				return true;
			}
		});
	}

	private void actionToJumping() {

		machineState.nextState(new ActionComponent(ActionCommand.PULAR) {

			@Override
			public boolean action(double delta, IGameRenderer gameRenderer) {
				IFrame jumpingSprite = sprites.get(ActionCommand.PULAR.getValue());
				checkCharacterDirection(jumpingSprite);

				force--;
				setY(getY() + force);

				if (getY() == startY) {
					force = 20;
					jumpingSprite.renderFrame(1, getX(), getY(), gameRenderer);
					machineState.transitar(ActionCommand.PARADO);
				} else if (force > 0) {
					jumpingSprite.renderFrame(2, getX(), getY(), gameRenderer);

				} else if (force < 0) {
					jumpingSprite.renderFrame(0, getX(), getY(), gameRenderer);
				}

				return true;
			}

		});
	}

	private void actionToIntro() {

		machineState.nextState(new ActionComponent(ActionCommand.INICIAL) {

			@Override
			public boolean action(double delta, IGameRenderer gameRenderer) {
				IFrame spriteInicial = sprites.get(ActionCommand.INICIAL.getValue());
				spriteInicial.renderFrame(getX(), getY(), gameRenderer);

				if (spriteInicial.isEndAnimation()) {
					machineState.transitar(ActionCommand.PARADO);
				}
				return true;
			}

		});
	}

	private void checkCharacterDirection(IFrame sprite) {

		if (direction.equals(EDirection.LEFT)) {
			if (sprite.getDirection().equals(EDirection.RIGHT)) {
				sprite.setDirection(EDirection.LEFT);
			}
		} else if (direction.equals(EDirection.RIGHT)) {
			if (sprite.getDirection().equals(EDirection.LEFT)) {
				sprite.setDirection(EDirection.RIGHT);
			}
		}

	}

	public void stopCharacter() {
		if (!machineState.isCurrentState(ActionCommand.INICIAL.getValue())) {
			if (!machineState.currentState().isRepeat()) {
				machineState.transitar(ActionCommand.PARADO);
			}
		}

	}

	public void updateMechanics(double delta, IGameRenderer gameRenderer) {
		// System.out.println("Estado Atual: " + controleEstado.getEstadoAtual().getId()
		// + " Estado Anterior: "+ controleEstado.getEstadoAnterior().getId());

		machineState.currentState().dispatch(delta, gameRenderer);

	}

	public abstract void render(double delta, IGameRenderer gameRenderer);

	public void runRight() {
		machineState.transitar(ActionCommand.CORRER_DIREITA);
	}

	public void runLeft() {
		machineState.transitar(ActionCommand.CORRER_ESQUERDA);
	}

	public void jumpUp() {
		machineState.transitar(ActionCommand.PULAR);
	}

	public abstract void runPower_01();

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getWidth() {
		return sprites.get(machineState.previousState().getId()).getWidth();
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

}
