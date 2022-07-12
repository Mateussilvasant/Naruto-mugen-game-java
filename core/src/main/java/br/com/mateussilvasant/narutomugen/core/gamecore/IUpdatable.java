package br.com.mateussilvasant.narutomugen.core.gamecore;

import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.IGameRenderer;

public interface IUpdatable {

    public void update(double delta, IGameRenderer gameRenderer);

}
