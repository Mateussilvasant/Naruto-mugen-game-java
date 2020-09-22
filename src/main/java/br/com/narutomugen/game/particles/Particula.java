package br.com.narutomugen.game.particles;

import br.com.narutomugen.game.manager.objects.Depositable;
import javafx.geometry.Point2D;
import javafx.scene.CacheHint;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;

public class Particula implements Depositable {

    private double initialX;
    private double initialY;
    private double x;
    private double y;
    private Point2D velocidade;
    private double raio;
    private Paint cor;
    private BlendMode blendMode;
    private double vida = 1.0;
    private double decaimento;
    private double tempoEsgotado;
    private ImageView view;

    public Particula() {
        clear();
    }

    public final void init(Image img, double x, double y, Point2D velocidade, double raio, double tempoEsgotado,
            Paint cor, BlendMode blendMode) {
        this.x = x;
        this.y = y;
        this.initialX = x;
        this.initialY = y;  
        this.velocidade = velocidade;
        this.raio = raio;
        this.cor = cor;
        this.blendMode = blendMode;
        this.tempoEsgotado = tempoEsgotado;
        this.decaimento = 0.016 / tempoEsgotado;

        view = new ImageView(img);

        view.setCache(true);
        view.setCacheHint(CacheHint.SPEED);
    }

    public void update() {
        x += velocidade.getX();
        y += velocidade.getY();
        vida -= decaimento;
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void render(boolean blend) {
        // view.setFill(cor);
        view.setOpacity(vida);
        //view.setBlendMode(blend ? blendMode : null);
        view.setTranslateX(x);
        view.setTranslateY(y);
    }

    public ImageView getView() {
        return view;
    }

    @Override
    public void clear() {
        this.x = initialX;
        this.y = initialY;
        //this.velocidade = Point2D.ZERO;
        //this.raio = 0.0;
        //this.cor = Color.rgb(0, 0, 0, 0);
        //this.blendMode = BlendMode.SRC_OVER;
        this.decaimento = 0.016 / tempoEsgotado;
        this.vida = 1.0;
        //this.view = null;
    }

}
