package br.com.narutomugen.game;

import br.com.narutomugen.game.app.JogoApp;
import br.com.narutomugen.game.engine.Motor;

public final class App extends JogoApp {

    public App() {
        super(1.50);
    }

    @Override
    public void onStart() {
        NarutoMugen jogoObjeto = new NarutoMugen(getLarguraWindow(), getAlturaWindow(), this);
        adicionarComponente(jogoObjeto.getCanvas());
        jogoObjeto.prepararPartida();
        Motor.iniciar();
    }

    // Argumentos da JVM, propriedades do sistema, argumentos da linha de comando do
    // aplicativo.
    private static void configurarPropriedades() {

        System.setProperty("prism.verbose", "true");

        System.setProperty("prism.forceGPU", "true");

        System.setProperty("prism.order", "d3d,sw");

        System.setProperty("prism.targetvram", "2G");

        System.setProperty("prism.vsync", "false");

        System.setProperty("prism.supershader", "true");

        System.setProperty("javafx.animation.fullspeed", "true");

        System.setProperty("quantum.singlethreaded", "false");

        System.setProperty("prism.allowhidpi", "false");

        System.setProperty("javafx.animation.framerate", "true");

    }

    public static void main(String[] args) {
        configurarPropriedades();
        launch(args);
    }

}
