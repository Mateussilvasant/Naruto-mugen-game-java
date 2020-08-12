package br.com.narutomugen.game;

import javafx.animation.AnimationTimer;

public abstract class Motor
{

    private static AnimationTimer temporizador;
    private static float fatorDelta;
    private long tempoAtual = 0;
    private long tempoPassado = 0;
    private int anteriorFPS = 0;
    private int atualFPS = 0;

    private double deltaAcumulado = 0;

    public Motor()
    {
	temporizador = new AnimationTimer()
	{
	    @Override
	    public void handle(long now)
	    {
		onRepetir(now);
	    }
	};
    }

    private void onRepetir(long tempoAgora)
    {
	// Data e hora atual do sistema em nanosegundos
	this.tempoAtual = tempoAgora;

	// Calcula a diferenca entre o momento atual e do quadro anterior e
	// converte
	// de nanosegundos para segundos
	Motor.fatorDelta = (float) ((this.tempoAtual - this.tempoPassado) / 1E9);

	// Acumula o valor de delta
	this.deltaAcumulado += Motor.fatorDelta;

	this.atualFPS++;

	// Limita o numero de frames para contar a cada segundo
	if (this.deltaAcumulado >= 1)
	{
	    this.anteriorFPS = this.atualFPS;
	    this.atualFPS = 0;
	    this.deltaAcumulado = 0;
	}

	if (fatorDelta < 1)
	{
	    // Atualiza o conteudo da tela
	    this.atualizar();
	}

	// Momento da ultima atualizacao
	this.tempoPassado = this.tempoAtual;

    }

    public abstract void atualizar();

    public static void iniciar()
    {
	temporizador.start();
    }

    public static void parar()
    {
	temporizador.stop();
    }

    public static double getDelta()
    {
	return fatorDelta;
    }

    public int getFPS()
    {
	return anteriorFPS;
    }

}