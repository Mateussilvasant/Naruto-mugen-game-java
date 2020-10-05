package br.com.narutomugen.game.particle.system;

import java.util.function.Function;

import br.com.narutomugen.game.manager.states.store.SimpleStack;
import br.com.narutomugen.game.manager.states.store.Store;
import javafx.geometry.Point2D;
import javafx.scene.CacheHint;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public abstract class EmitterBase implements IEmitterLoader {

    protected Store<Particle> store;

    private Pane particlesRegion;

    protected Point2D currentPoint;

    protected BlendMode blendMode;

    public EmitterBase(Store<Particle> store) {

        this.store = store;
        this.blendMode = null;

    }

    public EmitterBase(int capacity) {
        this(new SimpleStack<Particle>(capacity));
    }

    protected Pane createPane() {
        Pane pane = new Pane();
        pane.setCache(true);
        pane.setCacheHint(CacheHint.SPEED);
        pane.setCacheShape(true);
        pane.setBlendMode(blendMode);
        return pane;
    }

    protected void loadBackground(Pane gameView) {
        this.particlesRegion = createPane();
        gameView.getChildren().add(particlesRegion);
    }

    protected abstract void createParticles(double particleTime, Function<Particle, Point2D> equationMotion);

    public void update(double delta, Point2D currentPoint) {

        this.currentPoint = currentPoint;

        updateParticles(delta);
    }

    protected abstract void updateParticles(double delta);

    protected abstract void emitParticles(double delta);

    protected void addParticle(Particle particle) {

        if (particle.getParticleView() instanceof ImageView) {
            particlesRegion.getChildren().add((ImageView) particle.getParticleView());
        } else {
            particlesRegion.getChildren().add((Circle) particle.getParticleView());
        }

    }

    public void clearParticles() {
        particlesRegion.getChildren().removeAll();
    }

}
