package br.com.narutomugen.game.entities.character.constants;

/* Enum que tipifica as dire��es do personagem*/
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
