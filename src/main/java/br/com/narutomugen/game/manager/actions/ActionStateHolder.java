package br.com.narutomugen.game.manager.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import br.com.narutomugen.game.entities.character.actions.Actions;
import br.com.narutomugen.game.manager.states.State;
import br.com.narutomugen.game.manager.states.StateMachine;

public abstract class ActionStateHolder {

    protected HashMap<Actions, ActionState> actions;
    protected StateMachine<State> stateManager;
    private int numbersExecutions = 2;

    protected ActionStateHolder(StateMachine<State> stateManager, HashMap<Actions, ActionState> actions) {
        this.stateManager = stateManager;
        this.actions = actions;
    }

    protected abstract void registerAction(State state);

    protected abstract void dispatchAction(ActionState actionState);


    public void update() {
        updateMachine();
        executeActions(filterCurrentStates());
    }

    protected void executeActions(List<ActionState> actions) {

        for (ActionState action : actions) {
            dispatchAction(action);
        }

    }

    protected void incrementDispatchTimes(State state, ActionState action) {
        if (state.getCount() == action.maxDispatchers()) {
            state.resetState();
        } else {
            state.addCount();
        }
    }

    protected void updateMachine() {

        ActionState current = stateToAction(stateManager.getCurrentState());

        if (current != null) {

            if (!current.isRepeat()) {
                stateManager.update();
            }
        }
    }

    protected List<ActionState> filterCurrentStates() {

        List<State> states = stateManager.allStates();
        List<ActionState> filteredStates = new ArrayList<ActionState>();

        for (int i = 0; i <= numbersExecutions - 1; i++) {

            if(i < states.size()){
                filteredStates.add(stateToAction(states.get(i)));
            } else {
                break;
            }

        }

        return filteredStates;
    }

    protected ActionState stateToAction(State state) {

        if (state != null) {

            Actions action = Stream.of(Actions.values()).filter(a -> a.getValue() == state.id).findFirst().get();
            return actions.get(action);
        }

        return null;
    }

    protected State actionToState(ActionState action) {
        return new State(action.getId());
    }
}