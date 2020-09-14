package br.com.narutomugen.game.manager.actions;

import br.com.narutomugen.game.entities.character.actions.ActionCommand;

public abstract class ActionComponent {
 
    protected int id;
    protected boolean isRepeat;
    
    public ActionComponent(final ActionCommand eTipoEstado) {
        id = eTipoEstado.getValue();
        isRepeat = true;
    }
    public abstract boolean action();

    public boolean dispatch() {
	    return isRepeat = action();
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


}
