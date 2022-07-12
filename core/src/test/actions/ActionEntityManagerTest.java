package actions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.mateussilvasant.narutomugen.core.entities.character.actions.ActionCommand;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.GameRenderer;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.IGameRenderer;
import br.com.mateussilvasant.narutomugen.core.manager.actions.Action;
import br.com.mateussilvasant.narutomugen.core.manager.actions.ActionManager;
import br.com.mateussilvasant.narutomugen.core.manager.actions.ActionResponse;
import br.com.mateussilvasant.narutomugen.core.manager.actions.ActionState;
import br.com.mateussilvasant.narutomugen.core.manager.actions.SubAction;
import br.com.mateussilvasant.narutomugen.core.manager.states.State;
import br.com.mateussilvasant.narutomugen.core.manager.states.StateMachine;
import br.com.mateussilvasant.narutomugen.core.manager.states.store.SimpleStack;

public class ActionEntityManagerTest {

    private ActionManager actionEntityManager;

    private IGameRenderer gameRenderer;

    private HashMap<ActionCommand, ActionState> actions = new HashMap<>();

    private StateMachine<State> machineState;

    private SimpleStack<State> store;

    @BeforeEach
    public void setUp() {

        this.gameRenderer = new GameRenderer();
        this.gameRenderer.initGameRenderer();

        // 4
        Action action1 = new Action(ActionCommand.PARADO) {

            @Override
            public ActionResponse update(double delta, IGameRenderer gameRenderer) {

                System.out.println("Atualizando o ação { " + this.getId() + "} = Actions.PARADO");

                return new ActionResponse(false, ActionCommand.PARADO);
            }

            @Override
            public void render(ActionCommand action) {
                return;
            }

            @Override
            public void loadResources(String characterName) {
                return;
            }

        };

        // 2
        SubAction subaction1 = new SubAction(ActionCommand.INICIAL, ActionCommand.PARADO, true) {

            @Override
            public ActionResponse update(double delta, IGameRenderer gameRenderer) {
                System.out.println("Atualizando o ação { " + this.getId() + "} = Actions.INICIAL");

                return new ActionResponse(false, ActionCommand.INICIAL);
            }

            @Override
            public void render(ActionCommand action) {
                return;
            }

        };

        SubAction subaction2 = new SubAction(ActionCommand.PODER_1, ActionCommand.PARADO, false) {

            @Override
            public ActionResponse update(double delta, IGameRenderer gameRenderer) {
                System.out.println("Atualizando o ação { " + this.getId() + "}");

                return new ActionResponse(false, ActionCommand.PODER_1);
            }

            @Override
            public void render(ActionCommand action) {
                return;
            }

        };

        action1.children.put(ActionCommand.INICIAL, subaction1);

        actions.put(ActionCommand.PARADO, action1);
        actions.put(ActionCommand.INICIAL, subaction1);

        store = new SimpleStack<>(2);

        machineState = new StateMachine<>(store);

        actionEntityManager = new ActionManager(machineState, actions);

    }

    @Test
    public void executeStateAndSubstatesWhenSubStateIsUnattached() {

        State state1 = new State(ActionCommand.PARADO.getValue());
        State state2 = new State(ActionCommand.INICIAL.getValue());

        actionEntityManager.registerAction(state1);
        actionEntityManager.registerAction(state2);

        boolean repeat = true;
        double contador = 0;

        while (repeat) {

            if (contador == 4) {
                repeat = false;
            } else if (contador == 2) {
                actions.get(ActionCommand.PARADO).changeRepeat(false);
            }

            actionEntityManager.update(0, gameRenderer);

            contador = contador + 1;
        }

        String message = "The final state isn't  {" + machineState.getCurrentState() + "} it's {" + state2;

        assertEquals(state2,
                machineState.getCurrentState(), message);

    }
}