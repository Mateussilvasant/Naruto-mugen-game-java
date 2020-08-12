package br.com.narutomugen.game;

public abstract class Estado
{

    public boolean repetir;
    public int estadoID;

    public Estado(ETipoEstado eTipoEstado)
    {
	estadoID = eTipoEstado.getValue();
	repetir = true;
    }

    public void executarEstado()
    {
	repetir = acaoEstado();
    }

    public abstract boolean acaoEstado();

    public boolean getRepetir()
    {
	return repetir;
    }

    public int getEstadoID()
    {
	return estadoID;
    }

    public void setRepetir(boolean repetir)
    {
	this.repetir = repetir;
    }

}
