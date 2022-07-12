package br.com.mateussilvasant.narutomugen.core.particle.system;

import java.util.function.Function;

import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.BlendMode;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.IGameRenderer;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.paint.ColorG;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.textures.ITextureImage;
import br.com.mateussilvasant.narutomugen.core.manager.states.store.SimpleStack;
import br.com.mateussilvasant.narutomugen.core.manager.states.store.Store;
import br.com.mateussilvasant.narutomugen.core.util.Vector2D;

public class ParticleEmitter extends EmitterBase {

    private ITextureImage texture;
    private ColorG startColor;
    private ColorG endColor;
    private double particleRadius;

    private int maxParticles;
    private int maxEmissionParticles;
    private int maxDuration;

    private double emissionRate;
    private double durationEmission;
    private boolean lastEmission;
    private boolean emissionEnd;

    private Function<Vector2D, Vector2D> equationVelocity;
    private Vector2D initialPosition;

    private boolean isShape;

    public ParticleEmitter(int maxParticles) {
        super(maxParticles);
        this.maxParticles = maxParticles;
        this.maxEmissionParticles = 1;
        this.emissionRate = 0.0;
        this.lastEmission = false;
        this.emissionEnd = false;
        this.durationEmission = 0.0;
        this.maxDuration = 0;
        this.particleRadius = 32.0;
    }

    @Override
    public void initEmitter(Function<Particle, Vector2D> equationMotion,
            Function<Vector2D, Vector2D> equationVelocity, BlendMode blending,
            ColorG startColor,
            ColorG endColor,
            double particleRadius, int maxEmissionParticles, double particleTime, int time) {
        this.blendMode = blending;
        this.maxEmissionParticles = maxEmissionParticles;
        this.maxDuration = time;
        this.equationVelocity = equationVelocity;
        this.particleRadius = particleRadius;
        this.isShape = true;
        this.startColor = startColor;
        this.endColor = endColor;
        createParticles(particleTime, equationMotion);
    }

    @Override
    public void initEmitter(Function<Particle, Vector2D> equationMotion,
            Function<Vector2D, Vector2D> equationVelocity, BlendMode blending,
            ITextureImage texture, ColorG startColor,
            ColorG endColor, double particleRadius,
            int maxEmissionParticles, double particleTime, int time) {
        this.blendMode = blending;
        this.maxEmissionParticles = maxEmissionParticles;
        this.maxDuration = time;
        this.equationVelocity = equationVelocity;
        this.texture = texture;
        this.particleRadius = particleRadius;
        this.isShape = false;
        this.startColor = startColor;
        this.endColor = endColor;
        createParticles(particleTime, equationMotion);
    }

    @Override
    protected void createParticles(double particleTime, Function<Particle, Vector2D> equationMotion) {

        for (int i = 0; i < maxParticles; i++) {

            Particle particle = new Particle();

            Vector2D velocity = equationVelocity != null ? equationVelocity.apply(null) : Vector2D.ZERO;

            try {

                if (isShape) {

                    particle.newParticleShape(initialPosition, equationMotion, velocity,
                            particleRadius,
                            particleTime, blendMode, startColor, endColor);
                } else {
                    particle.newParticleTexture(initialPosition, equationMotion, velocity,
                            particleRadius,
                            particleTime, texture, startColor, endColor, blendMode);

                }

            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

            particles.store(particle);
            addParticle(particle);
        }

    }

    @Override
    protected void updateParticles(double delta, IGameRenderer gameRenderer) {

        if (!emissionEnd) {

            emitParticles(delta, gameRenderer);

            durationEmission = durationEmission + delta;
            emissionRate++;

            if (durationEmission > maxDuration) {
                lastEmission = true;
            }

        }

    }

    @Override
    public void emitParticles(double delta, IGameRenderer gameRenderer) {

        if (!particles.isEmpty()) {

            for (int i = 0; i <= (emissionRate * maxEmissionParticles); i++) {

                if (i < particles.size()) {

                    Particle particle = particles.getElement(i);

                    if (!particle.particleIsAlive()) {

                        Particle particleRemoved = particles.update();

                        if (!lastEmission) {
                            particles.store(particleRemoved);
                            particleRemoved.clear();
                        } else {
                            particleRemoved.clear();
                            particlesTemp.store(particleRemoved);

                            if (particles.isEmpty()) {
                                emissionEnd = true;
                            }

                        }

                    } else {
                        particle.update();
                        particle.render(gameRenderer, isShape);
                    }

                } else {
                    break;
                }
            }
        } else {
            emissionEnd = true;
        }

    }

    public void clear() {
        particles = particlesTemp;
        particlesTemp = new SimpleStack<Particle>(maxParticles);
        emissionRate = 0.0;
        durationEmission = 0.0;
        // lastEmission.set(false);
        emissionEnd = false;
    }

    public void setInitialPosition(Vector2D initialPosition) {
        this.initialPosition = initialPosition;
    }

    public Vector2D getInitialPosition() {
        return initialPosition;
    }

    public boolean emissionEnd() {
        return this.emissionEnd;
    }

    public Store<Particle> getParticles() {
        return particles;
    }

}
