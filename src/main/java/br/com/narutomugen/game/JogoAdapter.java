package br.com.narutomugen.game;

import javafx.scene.canvas.Canvas;

public abstract class JogoAdapter extends Motor
{
    private Render render;
    private JogoApp jogoApp;
    private TecladoEvent tecladoEvent;

    public JogoAdapter(double largura, double altura, JogoApp jogoApp)
    {
	this.render = new Render(largura, altura);
	this.jogoApp = jogoApp;
	this.tecladoEvent = new TecladoEvent(jogoApp.getScene());
    }

    public Render getRender()
    {
	return render;
    }

    public Canvas getCanvas()
    {
	return render.getCanvas();
    }

    public JogoApp getJogoApp()
    {
	return jogoApp;
    }

    public TecladoEvent getTecladoEvent()
    {
	return tecladoEvent;
    }

}
