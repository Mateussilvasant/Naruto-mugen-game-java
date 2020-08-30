package br.com.narutomugen.game.manager.actions;

import br.com.narutomugen.game.entities.character.actions.Actions;

public class ActionResponse {
    private boolean repeatAction;
    private Actions actionDispached;

    public ActionResponse(boolean repeatAction, Actions actionDispached){
        this.repeatAction = repeatAction;
        this.actionDispached = actionDispached;
    }

    public boolean getRepeatAction(){
        return repeatAction;
    }

    public Actions getAction(){
        return actionDispached;
    }
}