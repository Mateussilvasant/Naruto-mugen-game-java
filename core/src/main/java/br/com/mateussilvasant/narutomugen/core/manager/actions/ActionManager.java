package br.com.mateussilvasant.narutomugen.core.manager.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import br.com.mateussilvasant.narutomugen.core.entities.character.actions.ActionCommand;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.IGameRenderer;
import br.com.mateussilvasant.narutomugen.core.manager.states.State;
import br.com.mateussilvasant.narutomugen.core.manager.states.StateMachine;

/*
    The ActionManager implements main logic responsible for
    register the new action or new sub action in state machine and execute it's respective logics.
*/
public class ActionManager extends ActionStateHolder {

    public ActionManager(StateMachine<State> stateMachine, HashMap<ActionCommand, ActionState> actions) {
        super(stateMachine, actions);
    }

    @Override
    public void registerAction(State state) {

        Optional<ActionState> actionExists = getActionByState(state, ActionState.class);

        if (actionExists.isPresent()) {

            ActionState stateAction = actionExists.get();

            if (stateManager.exists(state) != null) {
                incrementDispatchTimes(state, stateAction);
            } else {

                if (stateAction.isSubAction()) {
                    registerSubAction(state);
                } else {
                    stateManager.storeState(state);
                }

            }

        }
    }

    @Override
    protected void registerSubAction(State state) {
        List<State> states = stateManager.allStates();

        for (int i = 0; i < states.size(); i++) {

            Optional<Action> action = getActionByState(states.get(i), Action.class);

            if (action.isPresent()) {
                if (action.get().getSubActions().containsKey(getCommand(state.id))) {
                    stateManager.storeState(state);
                    break;
                }
            }

        }
    }

    @Override
    protected void dispatchAction(ActionState actionState, double delta, IGameRenderer gameRenderer) {

        if (actionState.isSubAction()) {

            SubAction subAction = (SubAction) actionState;

            if (!subAction.isUnattached()) {

                State stateParent = new State(subAction.parent().getValue());

                if (stateManager.exists(stateParent) == null) {
                    subAction.changeRepeat(false);
                }

            }
        }

        actionState.dispatch(delta, gameRenderer);
    }

}