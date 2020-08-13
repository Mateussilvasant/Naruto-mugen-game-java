package br.com.narutomugen.game.particles;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Paint;

public class Particula {

    private double x;
    private double y;
    private Point2D velocidade;
    private double raio;
    private Paint cor;
    private BlendMode blendMode;
    private double vida = 0.5;
    private double decaimento;

    public Particula(double x, double y, Point2D velocidade, double raio, double tempoEsgotado, Paint cor,
            BlendMode blendMode) {
        this.x = x;
        this.y = y;
        this.velocidade = velocidade;
        this.raio = raio;
        this.cor = cor;
        this.blendMode = blendMode;
        this.decaimento = 0.016 / tempoEsgotado;
    }

    public void update() {
        x += velocidade.getX();
        y += velocidade.getY();
        vida -= decaimento;
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void render(GraphicsContext g) {
        g.setGlobalAlpha(vida);
        g.setGlobalBlendMode(blendMode);
        g.setFill(cor);
        g.fillOval(x, y, raio, raio);
    }

}
