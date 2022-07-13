package br.com.mateussilvasant.narutomugen.core.jutsus.katon;

import java.util.function.Function;

import br.com.mateussilvasant.narutomugen.core.gamecore.GameManager;
import br.com.mateussilvasant.narutomugen.core.gamecore.constants.EDirection;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.BlendMode;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.IGameRenderer;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.paint.ColorG;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.textures.ITextureImage;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.textures.TextureImage;
import br.com.mateussilvasant.narutomugen.core.particle.system.IEmitter;
import br.com.mateussilvasant.narutomugen.core.particle.system.Particle;
import br.com.mateussilvasant.narutomugen.core.particle.system.ParticleEmitter;
import br.com.mateussilvasant.narutomugen.core.util.Vector2D;

public class Goukakyuu implements IEmitter {

	private ParticleEmitter fireballEmitter;
	private ITextureImage texture;

	private Vector2D currentPosition;
	private EDirection direction;
	private float currentAngularVelocity = 0;

	// constants
	private final float ACCELERATION = 2.51f;
	private final float RADIUS = 80;
	private final int GAP_CENTER_POINT = (int) (RADIUS * 0.50);

	public Goukakyuu(int x, int y, EDirection direction) {

		currentPosition = new Vector2D(x, y);
		this.direction = direction;

		final Function<Particle, Vector2D> equationMotion = (Particle current) -> updateParticlePosition(current);
		final Function<Vector2D, Vector2D> equationVelocity = null;
		final BlendMode blendMode = BlendMode.MULTIPLY;
		final ColorG startColor = ColorG.hex("#ff3c00");
		final ColorG endColor = ColorG.hex("#ffed00");
		final float particleRadius = 52;
		final int maxEmissionParticles = 20;
		final float particleTime = 1.0f;
		final float time = 0.60f;

		texture = new TextureImage();
		texture.loadTextureByFile("/effects/particles/fire_base.png", (int) particleRadius, (int) particleRadius);

		fireballEmitter = new ParticleEmitter(1000);
		fireballEmitter.setInitialPosition(currentPosition);
		fireballEmitter.initEmitter(equationMotion, equationVelocity,
				blendMode,
				texture,
				startColor,
				endColor,
				particleRadius, maxEmissionParticles, particleTime, time);

	}

	@Override
	public void update(double delta, IGameRenderer gameRenderer) {

		updateJutsuPosition(delta);
		fireballEmitter.update(delta, fireballEmitter.getInitialPosition(), gameRenderer);

	}

	@Override
	public boolean isEmissionOver() {

		if (fireballEmitter.emissionEnd()) {
			resetParticleEmitter();
			return false;
		}
		return true;

	}

	private void updateJutsuPosition(double delta) {

		if (direction.equals(EDirection.LEFT)) {
			currentPosition = currentPosition.add(600 * delta, 0);
		} else {
			currentPosition = currentPosition.subtract(600 * delta, 0);
		}

	}

	private Vector2D updateParticlePosition(Particle currentParticle) {

		currentAngularVelocity += ACCELERATION * GameManager.DELTA_TIME();

		float distance = RADIUS;
		float newRadius = RADIUS - currentAngularVelocity;

		if (newRadius >= -1) {
			distance = newRadius;
		}

		double x = (Math.cos(currentAngularVelocity) * (distance)) + currentPosition.getX()
				+ (Math.random() * (RADIUS + GAP_CENTER_POINT));
		double y = (Math.sin(currentAngularVelocity) * (distance)) + currentPosition.getY()
				+ (Math.random() * (RADIUS + GAP_CENTER_POINT));

		return new Vector2D(x, y);
	}

	private void resetParticleEmitter() {

		fireballEmitter.clear();
		currentPosition = fireballEmitter.getInitialPosition();
		currentAngularVelocity = 0;

	}

}
