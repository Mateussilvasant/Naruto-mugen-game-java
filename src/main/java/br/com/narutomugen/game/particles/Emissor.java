package br.com.narutomugen.game.particles;

import java.util.List;

import javafx.scene.layout.Pane;

public abstract class Emissor {
    protected Pane view;

    public Emissor(Pane view) {
        this.view = view;
    }

    protected abstract void update();

}
