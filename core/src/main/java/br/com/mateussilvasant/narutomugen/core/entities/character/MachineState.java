package br.com.mateussilvasant.narutomugen.core.entities.character;

import br.com.mateussilvasant.narutomugen.core.entities.character.actions.ActionCommand;
import br.com.mateussilvasant.narutomugen.core.manager.actions.ActionComponent;

//Refactor
public class MachineState {

    private ActionComponent[] states;
    private ActionComponent currentState;
    private ActionComponent previousState;
    private int index;

    public MachineState(int qtdStates) {
        states = new ActionComponent[qtdStates];
        index = 0;
    }

    public void transitar(ActionCommand actions) {

        ActionComponent state = states[actions.getValue()];
        state.changeRepeat(true);

        if (previousState == null) {
            previousState = state;
        } else if (state.getId() != currentState.getId()) {
            previousState = currentState;
        }

        currentState = state;
    }

    public void nextState(ActionComponent state) {
        states[index] = state;
        index++;
    }

    public ActionComponent currentState() {
        return currentState;
    }

    public ActionComponent previousState() {
        return previousState;
    }

    public boolean isPreviousState(int value) {
        return previousState.getId() == value;
    }

    public boolean isCurrentState(int value) {
        return currentState.getId() == value;
    }

    public boolean isCurrentState(ActionCommand estado) {
        return currentState.getId() == estado.getValue();
    }

}
