package br.com.narutomugen.game.manager.actions;

import br.com.narutomugen.game.entities.character.actions.Actions;

public abstract class SubAction extends ActionState {
    
    private final Actions parent;
    private boolean unattached;

    public SubAction(final Actions actionID, final Actions parent, boolean unattached) {
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
        
    public Actions parent(){
        return parent;
    }

    public boolean isUnattached(){
        return unattached;
    }

}