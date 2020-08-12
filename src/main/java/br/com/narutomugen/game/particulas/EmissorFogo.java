package br.com.narutomugen.game.particulas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.narutomugen.game.Render;
import javafx.geometry.Point2D;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;

public class EmissorFogo extends Emissor
{

    private List<Particula> particulas = new ArrayList<>();

    public EmissorFogo(Render render)
    {
	super(render);
	particulas = new ArrayList<Particula>();
    }

    @Override
    protected List<Particula> emitir(double x, double y)
    {
	int numeroParticulas = 2;
	List<Particula> particulas = new ArrayList<>();

	for (int i = 0; i < numeroParticulas; i++)
	{
	    Particula particula = new Particula(x, y,
		    new Point2D(Math.cos((Math.random() - 0.5) * 0.90), Math.sin(Math.random() * 0.5)), 10, 5.0,
		    Color.rgb(255, 51, 0), BlendMode.ADD);
	    particulas.add(particula);
	}

	return particulas;
    }

    public void resetar()
    {
	particulas.clear();
    }

    @Override
    public void update(double x, double y)
    {
	particulas.addAll(emitir(x, y));

	for (Iterator<Particula> it = particulas.iterator(); it.hasNext();)
	{
	    Particula particula = it.next();
	    particula.update();

	    if (!particula.estaVivo())
	    {
		it.remove();
		continue;
	    }
	    particula.render(render.getContexto());
	}
    }

}
