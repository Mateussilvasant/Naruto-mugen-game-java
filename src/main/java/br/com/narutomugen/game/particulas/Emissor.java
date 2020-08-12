package br.com.narutomugen.game.particulas;

import java.util.List;

import br.com.narutomugen.game.Render;

public abstract class Emissor
{
    protected Render render;

    public Emissor(Render render)
    {
	this.render = render;
    }

    protected abstract List<Particula> emitir(double x, double y);

    protected abstract void update(double x, double y);

}
