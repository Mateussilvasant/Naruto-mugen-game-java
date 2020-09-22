package br.com.narutomugen.game.particles;

import br.com.narutomugen.game.graphics.Render;
import br.com.narutomugen.game.manager.states.store.SimpleStack;
import br.com.narutomugen.game.manager.states.store.Store;
import javafx.geometry.Point2D;
import javafx.scene.CacheHint;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class EmissorFogo extends Emissor {

	private Pane pane;

	private Store<Particula> particles;

	private final static int maxParticles = 1000;

	private final int maxEmissionParticles = 5;

	private Render render;

	private Image imgParticle;

	public EmissorFogo(Render render, Store<Particula> store, Pane view) {
		super(view);

		imgParticle = new Image(Particula.class.getResourceAsStream("/effects/particles/fireparticle4.png"), 64, 64,
				true, false);

		this.render = render;
		this.particles = store;

		pane = new Pane();
		pane.setCacheShape(true);
		pane.setCache(true);
		pane.setCacheHint(CacheHint.SPEED);
		pane.setBlendMode(BlendMode.ADD);

		view.getChildren().add(pane);
	}

	public EmissorFogo(Render render, Pane view) {
		this(render, new SimpleStack<Particula>(maxParticles), view);
	}

	public void load(int x, int y) {
		loadParticules(maxParticles, x, y);
	}

	private void loadParticules(int numberParticles, int x, int y) {

		particles.getAllElements().clear();

		for (int i = 0; i < numberParticles; i++) {
			Particula particle = new Particula();
			particle.init(imgParticle,(int) ((Math.random() - 0.5) * x), (int) ((Math.random() - 0.5) *y),
					new Point2D(Math.cos((Math.random() - 0.5) * 0.90), Math.sin(Math.random() * 1.0)), 10.0, 10.0,
					Color.rgb(255, 51, 0), BlendMode.ADD);
			particles.store(particle);
			pane.getChildren().add(particle.getView());
		}

	}

	private int emissionRate = 0;

	@Override
	public void update() {

		if (!particles.isEmpty()) {

			for (int i = 0; i <= (emissionRate * maxEmissionParticles) + maxEmissionParticles; i++) {

				if (i < particles.getAllElements().size()) {

					Particula particle = particles.getAllElements().get(i);

					if (!particle.estaVivo()) {

						Particula particleRemoved = particles.update();
						particleRemoved.clear();

						particles.store(particleRemoved);

					} else {
						particle.update();
						particle.render(i % 8 == 0);
					}

				} else {
					break;
				}
			}

		} else {
			resetar();
		}
		emissionRate++;

	}

	public void resetar() {
		particles.getAllElements().clear();
		pane.getChildren().removeAll();
	}

}
