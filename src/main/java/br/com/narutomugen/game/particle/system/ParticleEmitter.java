package br.com.narutomugen.game.particle.system;

import java.util.function.Function;

import javafx.geometry.Point2D;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ParticleEmitter extends EmitterBase {

    private Image texture;

    private int maxParticles;

    private int maxEmissionParticles;

    private double emissionRate;

    private boolean lastEmission;

    private boolean emissionEnd;

    private double durationEmission;

    private int maxDuration;

    private Function<Point2D, Point2D> vectorVelocity;

    private Point2D initialPosition;

    private double particleRadius;

    private Color startColor;

    private Color endColor;

    public ParticleEmitter(int maxParticles) {
        super(maxParticles);
        this.maxParticles = maxParticles;
        this.maxEmissionParticles = 1;
        this.emissionRate = 0.0;
        this.lastEmission = false;
        this.emissionEnd = false;
        this.durationEmission = 0.0;
        this.maxDuration = 0;
        this.particleRadius = 32;
    }

    @Override
    public void initEmitter(Pane gameview, Function<Point2D, Point2D> vectorVelocity, BlendMode blending,
            Color startColor, Color endColor, double particleRadius, int maxEmissionParticles, double particleTime,
            int time) {
        this.blendMode = blending;
        this.maxEmissionParticles = maxEmissionParticles;
        this.maxDuration = time;
        this.vectorVelocity = vectorVelocity;
        this.particleRadius = particleRadius;
        this.startColor = startColor;
        this.endColor = endColor;

        loadBackground(gameview);
        createParticles(particleTime);
    }

    @Override
    public void initEmitter(Pane gameview, Function<Point2D, Point2D> vectorVelocity, BlendMode blending, Image texture,
            double particleRadius, int maxEmissionParticles, double particleTime, int time) {
        this.blendMode = blending;
        this.maxEmissionParticles = maxEmissionParticles;
        this.maxDuration = time;
        this.vectorVelocity = vectorVelocity;
        this.texture = texture;
        this.particleRadius = particleRadius;

        loadBackground(gameview);
        createParticles(particleTime);
    }

    @Override
    protected void createParticles(double particleTime) {

        for (int i = 0; i < maxParticles; i++) {

            Particle particle = new Particle();

            if (texture != null) {
                particle.newParticleTexture(initialPosition, vectorVelocity.apply(null), particleRadius, particleTime,
                        texture);
            } else {
                particle.newParticleNode(initialPosition, vectorVelocity.apply(null), particleRadius, particleTime,
                        startColor, endColor);
            }

            store.store(particle);
            addParticle(particle);
        }

    }

    @Override
    protected void updateParticles(double delta) {

        emitParticles(delta);

        durationEmission = durationEmission + 0.01;
        emissionRate++;

        if (durationEmission > maxDuration) {
            lastEmission = true;
        }

    }

    @Override
    public void emitParticles(double delta) {
        int i = 0;

        if (!store.isEmpty()) {

            for (i = 0; i <= (emissionRate * maxEmissionParticles); i++) {

                if (i < store.getAllElements().size()) {

                    Particle particle = store.getAllElements().get(i);

                    if (!particle.particleIsAlive()) {

                        Particle particleRemoved = store.update();

                        if (!lastEmission) {
                            particleRemoved.clear();
                            store.store(particleRemoved);
                        }

                    } else {
                        particle.update();
                        particle.render();

                    }

                } else {
                    break;
                }
            }
        } else {
            emissionEnd = true;
        }

    }

    public void setInitialPosition(Point2D initialPosition) {
        this.initialPosition = initialPosition;
    }

    public Point2D getInitialPosition() {
        return initialPosition;
    }

    public boolean emissionEnd() {
        return this.emissionEnd;
    }

}
