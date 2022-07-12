package br.com.mateussilvasant.narutomugen.core.manager.states.interfaces;

import br.com.mateussilvasant.narutomugen.core.manager.actions.ActionState;

public interface IActionState<T> {

    void update();

    void registerAction(T state);

    ActionState stateToAction(T t);

}