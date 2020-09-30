package br.com.narutomugen.game.particle.system;

import javafx.geometry.Point2D;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Particle {

    private Point2D position;
    private Point2D initialPosition;
    private Point2D velocity;

    private double radius;
    private double life = 1.0;
    private double decay;
    private double particleTime;
    private final double decayRate = 0.016;

    private ImageView particleImage;

    private Ellipse particleShape;
    private Color startColor;
    private Color endColor;

    public void init(Point2D initialPosition, Point2D velocity, double radius, double particleTime) {
        this.position = initialPosition;
        this.initialPosition = initialPosition;
        this.velocity = velocity;
        this.radius = radius;
        this.particleTime = particleTime;
        this.decay = decayRate / particleTime;
    }

    public void newParticleNode(Point2D initialPosition, Point2D velocity, double radius, double timeDuration,
            Color startColor, Color endColor) {
        init(initialPosition, velocity, radius, timeDuration);

        this.particleShape = new Ellipse(radius, radius);
        this.particleShape.setCache(true);
        this.particleShape.setCacheHint(CacheHint.SPEED);
        this.startColor = startColor;
        this.endColor = endColor;
    }

    public void newParticleTexture(Point2D initialPosition, Point2D velocity, double radius, double timeDuration,
            Image texture) {
        init(initialPosition, velocity, radius, timeDuration);
        this.particleImage = new ImageView(texture);
        this.particleImage.setCache(true);
        this.particleImage.setCacheHint(CacheHint.SPEED);
    }

    public void update() {
        position = position.add(velocity);
        life -= decay;
    }

    public void render() {

        if (particleShape != null) {
            renderShape();
        } else {
            renderTexture();
        }
    }

    private void renderShape() {
        particleShape.setOpacity(life);
        particleShape.setTranslateX(position.getX());
        particleShape.setTranslateY(position.getY());
        particleShape.setFill(startColor.interpolate(endColor, life));
    }

    private void renderTexture() {
        particleImage.setOpacity(life);
        particleImage.setTranslateX(position.getX());
        particleImage.setTranslateY(position.getY());
    }

    public void clear() {
        this.position = initialPosition;
        this.decay = decayRate / particleTime;
        this.life = 1.0;
    }

    public boolean particleIsAlive() {
        return life > 0;
    }

    public Node getParticleView() {
        return particleShape != null ? particleShape : particleImage;
    }

}
