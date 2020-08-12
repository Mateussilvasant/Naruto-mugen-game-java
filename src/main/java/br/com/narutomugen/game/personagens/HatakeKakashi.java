package br.com.narutomugen.game.personagens;

import br.com.narutomugen.game.ETipoEstado;
import br.com.narutomugen.game.Estado;
import br.com.narutomugen.game.Personagem;
import br.com.narutomugen.game.Render;
import br.com.narutomugen.game.Sprite;
import br.com.narutomugen.game.TexturaSprite;
import br.com.narutomugen.game.particulas.EmissorFogo;

public class HatakeKakashi extends Personagem
{

    public EmissorFogo katonJutsu;

    public HatakeKakashi(int x, int y, Render render, boolean invertido)
    {
	super(x, y, invertido);
	carregarRecursos(render, invertido);
	carregarEstados();
    }

    private void carregarEstados()
    {
	controleEstado.adicionarEstado(new Estado(ETipoEstado.PODER_1)
	{

	    @Override
	    public boolean acaoEstado()
	    {
		katonJutsu.update(getEixoX(), getEixoY() - getWidth());
		return false;
	    }
	});
    }

    private void carregarRecursos(Render render, boolean invertido)
    {
	TexturaSprite texturaParado = new TexturaSprite();
	texturaParado.carregarTextura("/personagens/kakashi/tiles/Parado", 1.0);

	TexturaSprite texturaCorrer = new TexturaSprite();
	texturaCorrer.carregarTextura("/personagens/kakashi/tiles/Correndo", 1.0);

	TexturaSprite texturaPulando = new TexturaSprite();
	texturaPulando.carregarTextura("/personagens/kakashi/tiles/Pulando", 1.0);

	TexturaSprite texturaIntro = new TexturaSprite();
	texturaIntro.carregarTextura("/personagens/kakashi/tiles/Intro", 1.0);

	mapaSprites.put(ETipoEstado.CORRER_DIREITA.getValue(),
		new Sprite(render, texturaCorrer, 7, invertido ? false : false));
	mapaSprites.put(ETipoEstado.CORRER_ESQUERDA.getValue(),
		new Sprite(render, texturaCorrer, 7, invertido ? true : true));
	mapaSprites.put(ETipoEstado.PARADO.getValue(), new Sprite(render, texturaParado, 7, invertido));
	mapaSprites.put(ETipoEstado.PULAR.getValue(), new Sprite(render, texturaPulando, 7, invertido));
	mapaSprites.put(ETipoEstado.INICIAL.getValue(), new Sprite(render, texturaIntro, 1, invertido, false));

	katonJutsu = new EmissorFogo(render);
    }

    public void atualizarMecanicas()
    {
	super.atualizarMecanicas();
    }

    @Override
    public void executarPoder_1()
    {
	controleEstado.transitar(ETipoEstado.PODER_1);
    }

    @Override
    public void atualizarAnimacoes()
    {
	if (controleEstado.comparar(ETipoEstado.PODER_1))
	{
	    controleEstado.getEstadoAtual().executarEstado();
	}

    }

    public void parar()
    {

	if (!controleEstado.verificaEstadoAtual(ETipoEstado.INICIAL.getValue()))
	{
	    if (!controleEstado.getEstadoAtual().getRepetir())
	    {
		controleEstado.transitar(ETipoEstado.PARADO);
		katonJutsu.resetar();
	    }
	}

    }

}
