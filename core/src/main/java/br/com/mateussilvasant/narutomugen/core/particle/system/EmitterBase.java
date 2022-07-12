package br.com.mateussilvasant.narutomugen.core.particle.system;

import java.util.function.Function;

import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.BlendMode;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.IGameRenderer;
import br.com.mateussilvasant.narutomugen.core.manager.states.store.SimpleStack;
import br.com.mateussilvasant.narutomugen.core.manager.states.store.Store;
import br.com.mateussilvasant.narutomugen.core.util.Vector2D;

public abstract class EmitterBase implements IEmitterLoader {

    protected Store<Particle> particles;

    protected Store<Particle> particlesTemp;

    protected Vector2D currentPoint;

    protected BlendMode blendMode;

    public EmitterBase(Store<Particle> particles, Store<Particle> particlesTemp) {
        this.particles = particles;
        this.particlesTemp = particlesTemp;
        this.blendMode = null;
    }

    public EmitterBase(int capacity) {
        this(new SimpleStack<Particle>(capacity), new SimpleStack<Particle>(capacity));
    }

    protected abstract void createParticles(double particleTime, Function<Particle, Vector2D> equationMotion);

    public void update(double delta, Vector2D currentPoint, IGameRenderer gameRenderer) {

        this.currentPoint = currentPoint;

        updateParticles(delta, gameRenderer);
    }

    protected abstract void updateParticles(double delta, IGameRenderer gameRenderer);

    protected abstract void emitParticles(double delta, IGameRenderer gameRenderer);

    protected void addParticle(Particle particle) {

    }

}
