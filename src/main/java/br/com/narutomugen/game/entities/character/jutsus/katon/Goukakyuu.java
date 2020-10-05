package br.com.narutomugen.game.entities.character.jutsus.katon;

import br.com.narutomugen.game.engine.Motor;
import br.com.narutomugen.game.particle.system.Particle;
import br.com.narutomugen.game.particle.system.ParticleEmitter;
import javafx.geometry.Point2D;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Goukakyuu {

	private ParticleEmitter fireEmitter;
	private Point2D position;
	private final double speed = (2 * Math.PI) / 5;
	private double angle = 0;
	private double radius = 120;

	public Goukakyuu(Pane view, int x, int y) {

		fireEmitter = new ParticleEmitter(300);
		fireEmitter.setInitialPosition(new Point2D(x, y));
		fireEmitter.initEmitter(view, (Particle current) -> rotatePointParticle(current.getPosition()), null,
				BlendMode.HARD_LIGHT,
				new Image(Particle.class.getResourceAsStream("/effects/particles/fireparticle12.png"), 64, 64, true,
						true),
				64, 300, 3.8, 1);

		position = new Point2D(x, y);

	}

	public void update(double delta) {

		moveJutsu();
		fireEmitter.update(delta, fireEmitter.getInitialPosition());

	}

	private Point2D rotatePointParticle(Point2D current) {

		angle += speed * Motor.getDelta();
		double x = (Math.cos(angle) * (Math.random() * radius)) + position.getX();
		double y = (Math.sin(angle) * (Math.random() * radius)) + position.getY();

		return new Point2D(x, y);
	}

	public void moveJutsu() {
		position = position.add(250 * Motor.getDelta(), 0);
	}

	public boolean emissionEnd() {
		return fireEmitter.emissionEnd();
	}

	public void clear() {
		fireEmitter.clearParticles();
	}

}
