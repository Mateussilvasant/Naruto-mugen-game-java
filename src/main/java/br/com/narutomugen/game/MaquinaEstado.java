package br.com.narutomugen.game;

public class MaquinaEstado
{
    private Estado[] estados;
    private Estado estadoAtual;
    private Estado estadoAnterior;
    private int ponteiro;

    public MaquinaEstado(int quantidadeEstados)
    {
	estados = new Estado[quantidadeEstados];
	ponteiro = 0;
    }

    public void transitar(ETipoEstado eTipoEstado)
    {

	Estado estado = estados[eTipoEstado.getValue()];
	estado.setRepetir(true);

	if (estadoAnterior == null)
	{
	    estadoAnterior = estado;
	} else if (estado.estadoID != estadoAtual.getEstadoID())
	{
	    estadoAnterior = estadoAtual;
	}

	estadoAtual = estado;
    }

    public void adicionarEstado(Estado estado)
    {
	estados[ponteiro] = estado;
	ponteiro++;
    }

    public Estado getEstadoAtual()
    {
	return estadoAtual;
    }

    public Estado getEstadoAnterior()
    {
	return estadoAnterior;
    }

    public boolean verificaEstadoAnterior(int value)
    {
	return estadoAnterior.getEstadoID() == value;
    }

    public boolean verificaEstadoAtual(int value)
    {
	return estadoAtual.getEstadoID() == value;
    }

    public boolean comparar(ETipoEstado estado)
    {
	return estadoAtual.estadoID == estado.getValue();
    }

}
