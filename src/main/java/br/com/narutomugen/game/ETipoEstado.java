package br.com.narutomugen.game;

public enum ETipoEstado
{

    CORRER_DIREITA(0), CORRER_ESQUERDA(1), PARADO(2), PULAR(3), INICIAL(4), PODER_1(5);

    private int value;

    private ETipoEstado(int value)
    {
	this.value = value;
    }

    public int getValue()
    {
	return value;
    }
}
