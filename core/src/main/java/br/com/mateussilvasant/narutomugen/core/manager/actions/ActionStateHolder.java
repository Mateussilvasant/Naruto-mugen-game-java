package br.com.mateussilvasant.narutomugen.core.manager.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import br.com.mateussilvasant.narutomugen.core.entities.character.actions.ActionCommand;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.IGameRenderer;
import br.com.mateussilvasant.narutomugen.core.manager.states.State;
import br.com.mateussilvasant.narutomugen.core.manager.states.StateMachine;

public abstract class ActionStateHolder {

    protected HashMap<ActionCommand, ActionState> actions;

    protected StateMachine<State> stateManager;

    private int numbersExecutions = 2;

    protected ActionStateHolder(StateMachine<State> stateManager, HashMap<ActionCommand, ActionState> actions) {
        this.stateManager = stateManager;
        this.actions = actions;
    }

    protected abstract void registerAction(State state);

    protected abstract void registerSubAction(State state);

    protected abstract void dispatchAction(ActionState actionState, double delta, IGameRenderer gameRenderer);

    public void update(double delta, IGameRenderer gameRenderer) {
        updateMachine();
        executeActions(filterCurrentStates(), delta, gameRenderer);
    }

    protected void executeActions(List<ActionState> actions, double delta, IGameRenderer gameRenderer) {

        for (ActionState action : actions) {
            dispatchAction(action, delta, gameRenderer);
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

        Optional<ActionState> current = getActionByState(stateManager.getCurrentState(), ActionState.class);

        if (current.isPresent()) {

            ActionState currentAction = current.get();

            if (!currentAction.isRepeat()) {
                stateManager.update();
            }
        }
    }

    protected List<ActionState> filterCurrentStates() {

        List<State> states = stateManager.allStates();

        List<ActionState> filteredStates = new ArrayList<ActionState>();

        for (int i = 0; i <= numbersExecutions - 1; i++) {

            if (i < states.size()) {
                filteredStates.add(getActionByState(states.get(i), ActionState.class).get());
            } else {
                break;
            }

        }

        return filteredStates;
    }

    protected State getStateByAction(ActionState action) {
        return new State(action.getId());
    }

    protected <T> Optional<T> getActionByState(State state, Class<T> clazz) {

        if (state != null) {

            return actions.values().stream().filter(clazz::isInstance).filter(d -> d.id == state.id).map(clazz::cast)
                    .findFirst();

        }

        return null;
    }

    protected ActionCommand getCommand(Integer id) {

        return Stream.of(ActionCommand.values()).filter(a -> a.getValue() == id).findFirst().get();

    }

}