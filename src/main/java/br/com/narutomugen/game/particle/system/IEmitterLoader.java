package br.com.narutomugen.game.particle.system;

import java.util.function.Function;

import javafx.geometry.Point2D;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public interface IEmitterLoader {

        public void initEmitter(Pane gameview, Function<Point2D, Point2D> vectorVelocity, BlendMode blending,
                        Color startColor, Color endColor, double particleRadius, int maxEmissionParticles,
                        double particleTime, int time);

        public void initEmitter(Pane gameview, Function<Point2D, Point2D> vectorVelocity, BlendMode blending,
                        Image texture, double particleRadius, int maxEmissionParticles, double particleTime, int time);

}
