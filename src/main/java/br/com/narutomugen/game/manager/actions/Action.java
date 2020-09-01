package br.com.narutomugen.game.manager.actions;

import java.util.HashMap;

import br.com.narutomugen.game.entities.character.actions.Actions;

public abstract class Action extends ActionState {

    public HashMap<Actions, SubAction> subActions;

    public Action(Actions actionID) {
        super(actionID);
        subActions = new HashMap<>();
    }

    @Override
	public boolean dispatch() {
        ActionResponse action = 

        update();
        render(action.getAction());

       return action.getRepeatAction();    
    }

    public HashMap<Actions,SubAction> getSubActions(){
        return subActions;
    }


}