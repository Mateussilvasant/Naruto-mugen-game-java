package br.com.mateussilvasant.narutomugen.core.manager.actions;

import br.com.mateussilvasant.narutomugen.core.entities.character.actions.ActionCommand;

public class ActionResponse {
    private boolean repeatAction;
    private ActionCommand actionDispached;

    public ActionResponse(boolean repeatAction, ActionCommand actionDispached) {
        this.repeatAction = repeatAction;
        this.actionDispached = actionDispached;
    }

    public boolean getRepeatAction() {
        return repeatAction;
    }

    public ActionCommand getAction() {
        return actionDispached;
    }
}