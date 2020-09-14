package br.com.narutomugen.game.manager.actions;

import br.com.narutomugen.game.entities.character.actions.ActionCommand;

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
	public boolean dispatch() {
        
         ActionResponse action = 

         update();
         render(action.getAction());

        return action.getRepeatAction();
    }
        
    public ActionCommand parent(){
        return parent;
    }

    public boolean isUnattached(){
        return unattached;
    }

}