package br.com.narutomugen.game;

/* Enum que tipifica as direções do personagem*/
public enum EDirecao
{
    DIREITA(0), ESQUERDA(1);
    
    private int valor;

    private EDirecao(int valor)
    {
	this.valor = valor;
    }

    public int getValor()
    {
	return valor;
    }
}
