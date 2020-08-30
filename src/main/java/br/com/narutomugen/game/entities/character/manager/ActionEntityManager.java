package br.com.narutomugen.game.entities.character.manager;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.narutomugen.game.entities.character.actions.Actions;
import br.com.narutomugen.game.manager.actions.ActionState;
import br.com.narutomugen.game.manager.actions.SubAction;
import br.com.narutomugen.game.manager.states.State;
import br.com.narutomugen.game.manager.states.StateMachine;
import br.com.narutomugen.game.manager.states.interfaces.IActionState;
import br.com.narutomugen.game.manager.states.store.Store;

public class ActionEntityManager implements IActionState<State> {

    private HashMap<Actions, ActionState> actions;
    private StateMachine<State> stateManager;

    public ActionEntityManager(Store<State> store, HashMap<Actions, ActionState> actions) {
        this.stateManager = new StateMachine<State>(store);
        this.actions = actions;
    }

    @Override
    public void registerAction(State state) {

        State ref = stateManager.exists(state);

        if (ref != null) {

            ActionState action = stateToAction(ref);

            if (ref.getCount() == action.maxDispatchers()) {
                ref.resetState();
            } else {
                ref.addCount();
            }

        } else {
            stateManager.storeState(state);
        }

    }

    @Override
    public void update() {
        executeActions(getStates());
        updateMachine();
    }

    private List<ActionState> getStates() {

        ActionState[] states = new ActionState[] { stateToAction(stateManager.getCurrentState()),
                stateToAction(stateManager.getLastState()) };

        return Stream.of(states).filter(state -> state != null).collect(Collectors.toList());
    }

    private void executeActions(List<ActionState> actions) {
        actions.forEach(action -> dispatchAction(action));
    }

    private void updateMachine() {

        ActionState current = stateToAction(stateManager.getCurrentState());

        if (!current.isRepeat()) {
            update();
        }
    }

    private void dispatchAction(ActionState actionState) {

        if (actionState.isSubAction()) {

            SubAction subAction = (SubAction) actionState;
            subAction.dispatch();

            
            if (!subAction.isUnattached()) {

                State stateParent = new State(subAction.parent().getValue());

                if (stateManager.exists(stateParent) == null) {
                    subAction.changeRepeat(false);
                }
            }

            
        } else {
            actionState.dispatch();
        }


    }

    @Override
    public ActionState stateToAction(State state) {

        Actions action = Stream.of(Actions.values()).filter(a -> a.getValue() == state.id).findFirst().orElseThrow();

        return actions.get(action);
    }

    public State actionToState(ActionState action) {
        return new State(action.getId());
    }

}