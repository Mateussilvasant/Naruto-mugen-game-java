package br.com.mateussilvasant.narutomugen.core.manager.actions;

import java.util.HashMap;

import br.com.mateussilvasant.narutomugen.core.entities.character.actions.ActionCommand;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.IGameRenderer;

public abstract class Action extends ActionState {

    public HashMap<ActionCommand, SubAction> children;

    public Action(ActionCommand actionID) {
        super(actionID);
        children = new HashMap<>();
    }

    @Override
    public boolean dispatch(double delta, IGameRenderer gameRenderer) {

        ActionResponse action = update(delta, gameRenderer);
        render(action.getAction());

        return action.getRepeatAction();
    }

    public abstract void loadResources(String characterName);

    public HashMap<ActionCommand, SubAction> getSubActions() {
        return children;
    }

}