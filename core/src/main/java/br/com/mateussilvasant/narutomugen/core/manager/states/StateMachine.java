package br.com.mateussilvasant.narutomugen.core.manager.states;

import java.util.ArrayList;
import java.util.List;

import br.com.mateussilvasant.narutomugen.core.manager.states.interfaces.IStateMachine;
import br.com.mateussilvasant.narutomugen.core.manager.states.store.Store;

public class StateMachine<T> implements IStateMachine<T> {

    private Store<T> states;

    public StateMachine(Store<T> store) {
        this.states = store;
    }

    public T getCurrentState() {
        return states.getLast();
    }

    public T getLastState() {
        return states.getPreviousLast();
    }

    @Override
    public void storeState(T next) {
        states.store(next);
    }

    @Override
    public void update() {
        states.update();
    }

    @Override
    public T exists(T state) {
        return states.contains(state);
    }

    public List<T> allStates() {
        List<T> statesList = new ArrayList<>();

        for (int i = 0; i < states.size(); i++) {
            statesList.add(states.getElement(i));
        }
        return statesList;
    }

}