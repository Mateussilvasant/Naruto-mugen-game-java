package br.com.narutomugen.game.entities.character.manager;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.narutomugen.game.entities.character.actions.Actions;
import br.com.narutomugen.game.manager.actions.ActionState;
import br.com.narutomugen.game.manager.actions.ActionStateHolder;
import br.com.narutomugen.game.manager.actions.SubAction;
import br.com.narutomugen.game.manager.states.State;
import br.com.narutomugen.game.manager.states.StateMachine;

public class ActionEntityManager extends ActionStateHolder {

    public ActionEntityManager(StateMachine<State> stateMachine, HashMap<Actions, ActionState> actions) {
        super(stateMachine,actions);
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
    protected void dispatchAction(ActionState actionState) {
        if (actionState.isSubAction()) {

            SubAction subAction = (SubAction) actionState;
            subAction.dispatch();

            if (!subAction.isUnattached()) {

                    State stateParent = new State(subAction.parent().getValue());

                    if(stateManager.exists(stateParent) == null){
                        subAction.changeRepeat(false);
                    }

            }
        } else {
            actionState.dispatch();
        }
    }

    @Override
    public void update() {
        updateMachine();
        executeActions(currentStates());
    }

    @Override
    protected List<ActionState> currentStates() {

        ActionState[] states = new ActionState[] 
        { 
                stateToAction(stateManager.getCurrentState()),
                stateToAction(stateManager.getLastState()) 
        };

        return Stream.of(states).filter(state -> state != null).collect(Collectors.toList());
    }

    

}