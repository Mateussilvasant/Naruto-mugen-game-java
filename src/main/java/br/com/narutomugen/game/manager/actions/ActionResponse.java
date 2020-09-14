package br.com.narutomugen.game.manager.actions;

import br.com.narutomugen.game.entities.character.actions.ActionCommand;

public class ActionResponse {
    private boolean repeatAction;
    private ActionCommand actionDispached;

    public ActionResponse(boolean repeatAction, ActionCommand actionDispached){
        this.repeatAction = repeatAction;
        this.actionDispached = actionDispached;
    }

    public boolean getRepeatAction(){
        return repeatAction;
    }

    public ActionCommand getAction(){
        return actionDispached;
    }
}