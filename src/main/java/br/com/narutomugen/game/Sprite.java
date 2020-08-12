package br.com.narutomugen.game;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Sprite
{

    private TexturaSprite spriteTextura;
    private Render render;
    private int velocidade;
    private boolean inverterAnimacao;
    private float contadorQuadro;
    private boolean animacaoRepetir;
    private boolean animacaoFim;

    public Sprite(Render render, TexturaSprite spriteTextura, int velocidade)
    {
	this.animacaoRepetir = true;
	this.inverterAnimacao = false;
	this.animacaoFim = false;
	this.velocidade = velocidade;
	this.spriteTextura = spriteTextura;
	this.render = render;
    }

    public Sprite(Render render, TexturaSprite spriteTextura, int velocidade, boolean inverter)
    {
	this.animacaoRepetir = true;
	this.animacaoFim = false;
	this.inverterAnimacao = inverter;
	this.velocidade = velocidade;
	this.spriteTextura = spriteTextura;
	this.render = render;
    }

    public Sprite(Render render, TexturaSprite spriteTextura, int velocidade, boolean inverter, boolean animacaoRepetir)
    {
	this.animacaoFim = false;
	this.animacaoRepetir = animacaoRepetir;
	this.inverterAnimacao = inverter;
	this.velocidade = velocidade;
	this.spriteTextura = spriteTextura;
	this.render = render;
    }

    public void setInverterAnimacao(boolean inverterAnimacao)
    {
	this.inverterAnimacao = inverterAnimacao;
    }

    public void setVelocidadeAnimacao(int velocidade)
    {
	this.velocidade = velocidade;
    }

    public int getVelocidadeAnimacao()
    {
	return velocidade;
    }

    public boolean getInvertido()
    {
	return inverterAnimacao;
    }

    public boolean fimAnimacao()
    {
	return animacaoFim;
    }

    public double getWidth()
    {
	return spriteTextura.getFrame((int) contadorQuadro).getWidth();
    }

    public double getHeight()
    {
	return spriteTextura.getFrame((int) contadorQuadro).getHeight();
    }

    public Rectangle getLimitesFrame(int pos)
    {
	Image image = spriteTextura.getFrame(pos);
	return new Rectangle(image.getWidth(), image.getHeight());
    }

    public void animar(double x, double y)
    {
	renderizarAnimacao(x, y);
	controlarAnimacao();
    }

    public void animar(int indiceQuadro, double x, double y)
    {
	renderizarAnimacao(indiceQuadro, x, y, inverterAnimacao);
	controlarAnimacao();
    }

    public void animar(int indiceQuadro, double x, double y, boolean inverter)
    {
	renderizarAnimacao(indiceQuadro, x, y, inverter);
	controlarAnimacao();
    }

    private void controlarAnimacao()
    {

	if (!animacaoFim) // Verifica o estado da animacao
	{

	    contadorQuadro += (Motor.getDelta() * velocidade);

	    if ((int) contadorQuadro > (spriteTextura.getSize() - 1)) // Reinicia Animação
	    {

		if (!animacaoRepetir)
		{
		    animacaoFim = true;
		} else
		{
		    contadorQuadro = 0;
		}

	    }

	}

    }

    public void animacaoSemRepetir()
    {
	animacaoRepetir = false;
    }

    public void renderizarAnimacao(double x, double y)
    {
	render.renderizar(spriteTextura.getFrame((int) contadorQuadro), x, y, inverterAnimacao);
    }

    public void renderizarAnimacao(int indice, double x, double y, boolean inverter)
    {
	render.renderizar(spriteTextura.getFrame(indice), x, y, inverter);
    }

}
