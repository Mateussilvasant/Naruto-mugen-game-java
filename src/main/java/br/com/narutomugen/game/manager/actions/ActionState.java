package br.com.narutomugen.game.manager.actions;

import br.com.narutomugen.game.entities.character.actions.Actions;

public abstract class ActionState {
 
    protected int id;
    protected boolean isRepeat;
    protected int maxDispatchers = 1;
    protected boolean isSubAction;


    public ActionState(final Actions actionID) {
        id = actionID.getValue();
        isRepeat = true;
        isSubAction = false;
    }

    public abstract boolean dispatch();

    public abstract ActionResponse update();

    public abstract void render(Actions action);

    public void changeRepeat(boolean repetir) {
        this.isRepeat = repetir;
    }

    public boolean isRepeat() {
        return isRepeat;
    }

    public boolean isSubAction(){
        return isSubAction;
    }

    public void setSubAction(boolean isSubAction) {
        this.isSubAction = isSubAction;
    }

    public int getId() {
        return id;
    }

    public int maxDispatchers(){
        return maxDispatchers;
    }


}
