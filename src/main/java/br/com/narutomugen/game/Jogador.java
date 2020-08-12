package br.com.narutomugen.game;

public class Jogador
{
    public Personagem personagem;
    private String comandos[];
    private TecladoEvent tecladoEvent;

    public Jogador(Personagem personagem, TecladoEvent event, ETipoControle eTipoControle)
    {
	setPersonagem(personagem);
	comandos = eTipoControle.getComandos();
	tecladoEvent = event;
    }

    public void atualizarMecanicas()
    {
	capturarComando();
	personagem.atualizarMecanicas();
    }

    public void atualizarAnimacoes()
    {
	personagem.atualizarAnimacoes();
    }

    private void capturarComando()
    {
	if (tecladoEvent.pressionou(comandos[0]))
	{
	    personagem.correrParaDireita();
	}

	if (tecladoEvent.pressionou(comandos[1]))
	{
	    personagem.correrParaEsquerda();
	}

	if (tecladoEvent.pressionou(comandos[2]))
	{
	    personagem.pularParaCima();
	}

	if (tecladoEvent.pressionou(comandos[3]))
	{
	    personagem.executarPoder_1();
	}

	if (tecladoEvent.semComando())
	{
	    personagem.parar();
	}

    }

    public void setPersonagem(Personagem personagem)
    {
	this.personagem = personagem;
    }

}
