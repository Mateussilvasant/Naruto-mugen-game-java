package br.com.narutomugen.game.manager.actions;

import java.util.HashMap;

import br.com.narutomugen.game.entities.character.actions.ActionCommand;

public abstract class Action extends ActionState {

    public HashMap<ActionCommand, SubAction> children;

    public Action(ActionCommand actionID) {
        super(actionID);
        children = new HashMap<>();
    }

    @Override
	public boolean dispatch() {
        ActionResponse action = 

        update();
        render(action.getAction());

       return action.getRepeatAction();    
    }

    public HashMap<ActionCommand,SubAction> getSubActions(){
        return children;
    }


}