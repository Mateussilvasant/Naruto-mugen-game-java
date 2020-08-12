package br.com.narutomugen.game;

public enum ETipoControle
{

    PLAYER1
    {

	@Override
	public String[] getComandos()
	{
	    return new String[]
	    { "RIGHT", "LEFT", "UP", "Q" };
	}

    },

    PLAYER2
    {

	@Override
	public String[] getComandos()
	{
	    return new String[]
	    { "L", "J", "I", "U" };
	}

    };

    public abstract String[] getComandos();

}
