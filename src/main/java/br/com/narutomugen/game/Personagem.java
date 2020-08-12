package br.com.narutomugen.game;

import java.util.HashMap;

public abstract class Personagem
{

    protected double eixoX;
    protected double eixoY;
    protected double inicioY;
    protected double velocidade;
    protected EDirecao direcao;
    protected double forca;

    protected HashMap<Integer, Sprite> mapaSprites;
    protected MaquinaEstado controleEstado;

    public Personagem(int x, int y, boolean inverter)
    {
	mapaSprites = new HashMap<Integer, Sprite>();
	controleEstado = new MaquinaEstado(ETipoEstado.values().length);
	inicioY = y;
	forca = 20;
	direcao = inverter ? EDirecao.ESQUERDA : EDirecao.DIREITA;
	setEixoX(x);
	setEixoY(y);
	setVelocidade(150);

	iniciarEstados();
	controleEstado.transitar(ETipoEstado.INICIAL);
    }

    private void iniciarEstados()
    {
	estadoCorrerDireita();
	estadoCorrerEsquerda();
	estadoParado();
	estadoPular();
	estadoInicial();
    }

    private void estadoCorrerDireita()
    {
	controleEstado.adicionarEstado(new Estado(ETipoEstado.CORRER_DIREITA)
	{

	    @Override
	    public boolean acaoEstado()
	    {
		direcao = EDirecao.DIREITA;
		eixoX = eixoX + (getVelocidade() * Motor.getDelta());
		mapaSprites.get(ETipoEstado.CORRER_DIREITA.getValue()).animar(getEixoX(), getEixoY());
		return false;
	    }
	});
    }

    private void estadoCorrerEsquerda()
    {

	controleEstado.adicionarEstado(new Estado(ETipoEstado.CORRER_ESQUERDA)
	{

	    @Override
	    public boolean acaoEstado()
	    {
		direcao = EDirecao.ESQUERDA;
		eixoX = eixoX - (getVelocidade() * Motor.getDelta());
		mapaSprites.get(ETipoEstado.CORRER_ESQUERDA.getValue()).animar(getEixoX(), getEixoY());
		return false;
	    }
	});
    }

    private void estadoParado()
    {
	controleEstado.adicionarEstado(new Estado(ETipoEstado.PARADO)
	{

	    @Override
	    public boolean acaoEstado()
	    {
		Sprite paradoSprite = mapaSprites.get(ETipoEstado.PARADO.getValue());
		verificarDirecaoSprite(paradoSprite);
		paradoSprite.animar(getEixoX(), getEixoY());
		return true;
	    }
	});
    }

    private void estadoPular()
    {

	controleEstado.adicionarEstado(new Estado(ETipoEstado.PULAR)
	{

	    @Override
	    public boolean acaoEstado()
	    {
		Sprite spritePular = mapaSprites.get(ETipoEstado.PULAR.getValue());
		verificarDirecaoSprite(spritePular);

		forca--;
		setEixoY(getEixoY() - forca);

		if (getEixoY() == inicioY)
		{
		    forca = 20;
		    spritePular.animar(1, getEixoX(), getEixoY());
		    controleEstado.transitar(ETipoEstado.PARADO);
		} else if (forca > 0)
		{
		    spritePular.animar(2, getEixoX(), getEixoY());

		} else if (forca < 0)
		{
		    spritePular.animar(0, getEixoX(), getEixoY());
		}

		return true;
	    }

	});
    }

    private void estadoInicial()
    {

	controleEstado.adicionarEstado(new Estado(ETipoEstado.INICIAL)
	{

	    @Override
	    public boolean acaoEstado()
	    {
		Sprite spriteInicial = mapaSprites.get(ETipoEstado.INICIAL.getValue());
		spriteInicial.animar(getEixoX(), getEixoY());

		if (spriteInicial.fimAnimacao())
		{
		    controleEstado.transitar(ETipoEstado.PARADO);
		}
		return true;
	    }

	});
    }

    private void verificarDirecaoSprite(Sprite sprite)
    {

	if (direcao.equals(EDirecao.ESQUERDA))
	{
	    if (!sprite.getInvertido())
	    {
		sprite.setInverterAnimacao(true);
	    }
	} else
	{
	    if (sprite.getInvertido())
	    {
		sprite.setInverterAnimacao(false);
	    }
	}

    }

    public void parar()
    {
	if (!controleEstado.verificaEstadoAtual(ETipoEstado.INICIAL.getValue()))
	{
	    if (!controleEstado.getEstadoAtual().getRepetir())
	    {
		controleEstado.transitar(ETipoEstado.PARADO);
	    }
	}

    }

    public void atualizarMecanicas()
    {
	// System.out.println("Estado Atual: " +
	// controleEstado.getEstadoAtual().estadoID + " Estado Anterior: "
	// + controleEstado.getEstadoAnterior().estadoID);

	if (controleEstado.comparar(ETipoEstado.CORRER_DIREITA))
	{
	    controleEstado.getEstadoAtual().executarEstado();
	}

	if (controleEstado.comparar(ETipoEstado.CORRER_ESQUERDA))
	{
	    controleEstado.getEstadoAtual().executarEstado();
	}

	if (controleEstado.comparar(ETipoEstado.PARADO))
	{
	    controleEstado.getEstadoAtual().executarEstado();
	}

	if (controleEstado.comparar(ETipoEstado.INICIAL))
	{
	    controleEstado.getEstadoAtual().executarEstado();
	}

	if (controleEstado.comparar(ETipoEstado.PULAR))
	{
	    controleEstado.getEstadoAtual().executarEstado();
	}

    }

    public abstract void atualizarAnimacoes();

    public void correrParaDireita()
    {
	controleEstado.transitar(ETipoEstado.CORRER_DIREITA);
    }

    public void correrParaEsquerda()
    {
	controleEstado.transitar(ETipoEstado.CORRER_ESQUERDA);
    }

    public void pularParaCima()
    {
	controleEstado.transitar(ETipoEstado.PULAR);
    }

    public abstract void executarPoder_1();

    public double getEixoX()
    {
	return eixoX;
    }

    public double getEixoY()
    {
	return eixoY;
    }

    public double getVelocidade()
    {
	return velocidade;
    }

    public void setEixoX(double x)
    {
	eixoX = x;
    }

    public void setEixoY(double y)
    {
	eixoY = y;
    }

    public double getWidth()
    {
	return mapaSprites.get(controleEstado.getEstadoAnterior().getEstadoID()).getWidth();
    }

    public void setVelocidade(double velocidade)
    {
	this.velocidade = velocidade;
    }

}
