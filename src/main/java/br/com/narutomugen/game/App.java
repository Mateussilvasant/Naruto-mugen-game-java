package br.com.narutomugen.game;

import br.com.narutomugen.game.app.JogoApp;
import br.com.narutomugen.game.engine.Motor;

public final class App extends JogoApp {

    public App() {
        super(1.10);
    }

    @Override
    public void onStart() {
        NarutoMugen jogoObjeto = new NarutoMugen(getLarguraWindow(), getAlturaWindow(), this);
        jogoObjeto.prepararPartida();
        adicionarComponente(jogoObjeto.getCanvas());
        Motor.iniciar();
    }

    // Argumentos da JVM, propriedades do sistema, argumentos da linha de comando do
    // aplicativo.
    private static void configurarPropriedades() {

        System.setProperty("prism.cacheshapes", "false");

        System.setProperty("prism.verbose", "true");

        System.setProperty("prism.forceGPU", "true");

        System.setProperty("prism.order", "d3d,es1,sw");

        System.setProperty("prism.targetvram", "2G");

        System.setProperty("prism.vsync", "true");

        System.setProperty("javafx.animation.framerate", "60");

        System.setProperty("prism.threadcheck", "true");

        System.setProperty("prism.dirtyopts", "false");

        System.setProperty("prism.disableRegionCaching", "false");

        System.setProperty("prism.allowhidpi", "false");
    }

    public static void main(String[] args) {
        configurarPropriedades();
        launch(args);
    }

}
