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
        adicionarComponente(jogoObjeto.getCanvas());
        jogoObjeto.prepararPartida();
        Motor.iniciar();
    }

    // Argumentos da JVM, propriedades do sistema, argumentos da linha de comando do
    // aplicativo.
    private static void configurarPropriedades() {

        System.setProperty("prism.verbose", "true");

        System.setProperty("prism.forceGPU", "true");

        System.setProperty("prism.order", "sw,es1,d3d");

        System.setProperty("prism.targetvram", "2G");

        System.setProperty("prism.vsync", "false");

        System.setProperty("javafx.animation.framerate", "30");

        System.setProperty("prism.disableRegionCaching", "false");

        System.setProperty("prism.allowhidpi", "false");
    }

    public static void main(String[] args) {
        configurarPropriedades();
        launch(args);
    }

}
