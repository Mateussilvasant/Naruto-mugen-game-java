package br.com.narutomugen.game.graphics;

import java.util.ArrayList;

import br.com.narutomugen.game.sceneries.GerenciadorCenarios;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Render {

    private GraphicsContext contexto;
    private Canvas canvas;

    public Render(double largura, double altura) {
        canvas = new Canvas(largura, altura);
        contexto = canvas.getGraphicsContext2D();
    }

    public void renderizar(ArrayList<ImageView> Cenario, int indice, GerenciadorCenarios gc) {
        contexto.drawImage(Cenario.get(indice).getImage(), gc.getX(), gc.getY());
    }

    public void renderizar(Image img, double x, double y, boolean invertido) {
        if (invertido) {
            contexto.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), (int) (x + img.getWidth()),
                    (int) (y - img.getHeight()), -img.getWidth(), img.getHeight());
        } else {
            contexto.drawImage(img, (int) (x - img.getWidth()), (int) (y - img.getHeight()));
        }
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public GraphicsContext getContexto() {
        return contexto;
    }

}
