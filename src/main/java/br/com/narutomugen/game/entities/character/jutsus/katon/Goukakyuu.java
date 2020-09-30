package br.com.narutomugen.game.entities.character.jutsus.katon;

import java.util.function.Function;

import br.com.narutomugen.game.particle.system.Particle;
import br.com.narutomugen.game.particle.system.ParticleEmitter;
import javafx.geometry.Point2D;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Goukakyuu {

	private ParticleEmitter fireEmitter;

	public Goukakyuu(Pane view, int x, int y) {

		fireEmitter = new ParticleEmitter(1000);
		fireEmitter.setInitialPosition(new Point2D(x, y));
		fireEmitter.initEmitter(view, getInitialVelocity(), BlendMode.HARD_LIGHT,
				new Image(Particle.class.getResourceAsStream("/effects/particles/fireparticle8.png"), 32, 32, true,
						true),
				32, 1, 1.2, 30);
	}

	public void update(double delta) {
		fireEmitter.update(delta, fireEmitter.getInitialPosition());
	}

	private Function<Point2D, Point2D> getInitialVelocity() {

		return (Point2D point) -> {
			double angle = Math.toRadians(270 + Math.random() * 360);
			return new Point2D(Math.cos(angle), Math.sin(angle));
		};

	}

	public boolean emissionEnd() {
		return fireEmitter.emissionEnd();
	}

	public void clear() {
		fireEmitter.clearParticles();
	}

}
