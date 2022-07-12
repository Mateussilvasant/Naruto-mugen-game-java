package br.com.mateussilvasant.narutomugen.core.particle.system;

import java.util.function.Function;

import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.BlendMode;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.IGameRenderer;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.paint.ColorG;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.textures.ITextureImage;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.textures.SpriteImage;
import br.com.mateussilvasant.narutomugen.core.util.Vector2D;

public class Particle {

    private Vector2D position;
    private Vector2D initialPosition;
    private Vector2D velocity;

    private double radius;
    private double life = 1.0;
    private double decay;
    private double particleTime;
    private final double decayRate = 0.016;

    private SpriteImage particleImage;

    private ColorG startColor;
    private ColorG endColor;
    private Function<Particle, Vector2D> equationMotion;

    private BlendMode blendMode;

    public void init(Vector2D initialPosition, Vector2D velocity, double radius, double particleTime) {

        this.position = initialPosition;
        this.initialPosition = initialPosition;
        this.velocity = velocity;
        this.radius = radius;
        this.particleTime = particleTime;
        this.decay = decayRate / particleTime;
    }

    public void newParticleTexture(
            Vector2D initialPosition, Function<Particle, Vector2D> equationMotion,
            Vector2D velocity, double radius, double timeDuration,
            ITextureImage texture, ColorG startColor,
            ColorG endColor, BlendMode blendMode)
            throws CloneNotSupportedException {
        init(initialPosition, velocity, radius, timeDuration);
        this.particleImage = new SpriteImage(texture.getFirstFrame());
        this.equationMotion = equationMotion;
        this.blendMode = blendMode;
        this.startColor = startColor;
        this.endColor = endColor;
    }

    public void newParticleShape(
            Vector2D initialPosition, Function<Particle, Vector2D> equationMotion,
            Vector2D velocity, double radius, double timeDuration, BlendMode blendMode,
            ColorG startColor,
            ColorG endColor)
            throws CloneNotSupportedException {
        init(initialPosition, velocity, radius, timeDuration);
        this.equationMotion = equationMotion;
        this.startColor = startColor;
        this.endColor = endColor;
        this.blendMode = blendMode;
    }

    public void update() {

        if (equationMotion != null) {
            position = equationMotion.apply(this);
        } else {
            position = position.add(velocity);
        }

        life -= decay;

    }

    public void render(IGameRenderer render, boolean isShape) {

        if (isShape) {
            renderShape(render);
        } else {
            renderTexture(render);
        }
    }

    private void renderShape(IGameRenderer render) {

        ColorG result = startColor.interpolate(endColor, life);

        render.renderCircle(result, position.getX(), position.getY(), radius, life, blendMode);
    }

    private void renderTexture(IGameRenderer render) {

        ColorG result = startColor.interpolate(endColor, life);

        particleImage.setAlpha((float) life);

        render.render(particleImage, position.getX(), position.getY(), blendMode, result);
    }

    public void clear() {
        this.position = initialPosition;
        this.decay = decayRate / particleTime;
        this.life = 1.0;
    }

    public boolean particleIsAlive() {
        return life > 0;
    }

    public Vector2D getPosition() {
        return position;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

}
