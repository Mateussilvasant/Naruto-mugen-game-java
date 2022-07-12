package br.com.mateussilvasant.narutomugen.core.manager.actions;

import br.com.mateussilvasant.narutomugen.core.entities.character.actions.ActionCommand;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.IGameRenderer;

public abstract class ActionComponent {

    protected int id;
    protected boolean isRepeat;

    public ActionComponent(final ActionCommand eTipoEstado) {
        id = eTipoEstado.getValue();
        isRepeat = true;
    }

    public void changeRepeat(final boolean repetir) {
        this.isRepeat = repetir;
    }

    public boolean isRepeat() {
        return isRepeat;
    }

    public int getId() {
        return id;
    }

    public boolean dispatch(double delta, IGameRenderer gameRenderer) {
        return isRepeat = action(delta, gameRenderer);
    }

    public abstract boolean action(double delta, IGameRenderer gameRenderer);

}
