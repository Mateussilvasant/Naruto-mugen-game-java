package br.com.narutomugen.game.manager.states.interfaces;

public interface IStateMachine<T> {
    void storeState(T next);
    void update();
    T exists(T state);
}