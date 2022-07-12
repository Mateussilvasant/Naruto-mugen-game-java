package br.com.mateussilvasant.narutomugen.core.manager.actions;

import br.com.mateussilvasant.narutomugen.core.entities.character.actions.ActionCommand;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.IGameRenderer;

public abstract class SubAction extends ActionState {

    private final ActionCommand parent;
    private boolean unattached;

    public SubAction(final ActionCommand actionID, final ActionCommand parent, boolean unattached) {
        super(actionID);
        this.parent = parent;
        this.unattached = unattached;

        setSubAction(true);
    }

    @Override
    public boolean dispatch(double delta, IGameRenderer gameRenderer) {

        ActionResponse action = update(delta, gameRenderer);
        render(action.getAction());

        return action.getRepeatAction();
    }

    public ActionCommand parent() {
        return parent;
    }

    public boolean isUnattached() {
        return unattached;
    }

}