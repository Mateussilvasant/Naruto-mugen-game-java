package br.com.narutomugen.game.manager.actions;

import java.util.HashMap;

import br.com.narutomugen.game.entities.character.actions.Actions;
import br.com.narutomugen.game.manager.states.State;
import br.com.narutomugen.game.manager.states.StateMachine;

public class ActionController extends ActionStateHolder {


    public ActionController(StateMachine<State> stateMachine, HashMap<Actions, ActionState> actions) {
        super(stateMachine, actions);
    }

    @Override
    public void registerAction(State state) {
        State stateRef = stateManager.exists(state);

        if (stateRef != null) {
            incrementDispatchTimes(stateRef, stateToAction(stateRef));
        } else {
            stateManager.storeState(state);
        }
    }

    @Override
    protected void dispatchAction(ActionState actionState) {

        if (actionState.isSubAction()) {

            SubAction subAction = (SubAction) actionState;

            if (!subAction.isUnattached()) {

                State stateParent = new State(subAction.parent().getValue());

                if (stateManager.exists(stateParent) == null) {
                    subAction.changeRepeat(false);
                }

            }
        }

        actionState.dispatch();
    }

   


}