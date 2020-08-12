package br.com.narutomugen.game;

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

    public static void main(String[] args) {
        launch(args);
    }

}
