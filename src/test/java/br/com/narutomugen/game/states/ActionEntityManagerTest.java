package br.com.narutomugen.game.states;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.narutomugen.game.entities.character.actions.Actions;
import br.com.narutomugen.game.entities.character.manager.ActionEntityManager;
import br.com.narutomugen.game.manager.actions.Action;
import br.com.narutomugen.game.manager.actions.ActionResponse;
import br.com.narutomugen.game.manager.actions.ActionState;
import br.com.narutomugen.game.manager.actions.SubAction;
import br.com.narutomugen.game.manager.states.State;
import br.com.narutomugen.game.manager.states.StateMachine;
import br.com.narutomugen.game.manager.states.store.SimpleStack;

public class ActionEntityManagerTest {

    private ActionEntityManager actionEntityManager;

    private HashMap<Actions, ActionState> actions = new HashMap<>();

    private StateMachine<State> machineState;

    private SimpleStack<State> store;

    @Before
    public void setUp() {

        // 4
        Action action1 = new Action(Actions.PARADO) {

            @Override
            public ActionResponse update() {

                System.out.println("Atualizando o ação { " + this.getId() + "} = Actions.PARADO");

                return new ActionResponse(false, Actions.PARADO);
            }

            @Override
            public void render(Actions action) {

            }

        };

        // 2
        SubAction subaction1 = new SubAction(Actions.INICIAL, Actions.PARADO, true) {

            @Override
            public ActionResponse update() {

                System.out.println("Atualizando o ação { " + this.getId() + "} = Actions.INICIAL");

                return new ActionResponse(false, Actions.INICIAL);
            }

            @Override
            public void render(Actions action) {

            }

        };

        SubAction subaction2 = new SubAction(Actions.PODER_1, Actions.PARADO, false) {

            @Override
            public ActionResponse update() {

                System.out.println("Atualizando o ação { " + this.getId() + "}");

                return new ActionResponse(false, Actions.PODER_1);
            }

            @Override
            public void render(Actions action) {

            }

        };

        action1.subActions.put(Actions.INICIAL, subaction1);

        actions.put(Actions.PARADO, action1);
        actions.put(Actions.INICIAL, subaction1);

        store = new SimpleStack<>(2);

        machineState = new StateMachine<>(store);

        actionEntityManager = new ActionEntityManager(machineState, actions);

    }

    @Test
    public void executeStateAndSubstatesWhenSubStateIsUnattached() {

        State state1 = new State(Actions.PARADO.getValue());
        State state2 = new State(Actions.INICIAL.getValue());

        actionEntityManager.registerAction(state1);
        actionEntityManager.registerAction(state2);

        boolean repeat = true;
        double contador = 0;

        while (repeat) {

            if (contador == 4) {
                repeat = false;
            } else if (contador == 2) {
               actions.get(Actions.PARADO).changeRepeat(false);
            }

            actionEntityManager.update();

            System.out.println(store.getAllElements());

            contador = contador + 1;
        }

        Assert.assertEquals("The final state isn't  {" + machineState.getCurrentState() + "} it's {" + state2, state2,
                machineState.getCurrentState());

    }
}