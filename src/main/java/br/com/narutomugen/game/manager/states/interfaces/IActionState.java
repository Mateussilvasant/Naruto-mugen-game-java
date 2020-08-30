package br.com.narutomugen.game.manager.states.interfaces;

import br.com.narutomugen.game.manager.actions.ActionState;

public interface IActionState<T> {
    
    void update();

    void registerAction(T state);

    ActionState stateToAction(T t);


}