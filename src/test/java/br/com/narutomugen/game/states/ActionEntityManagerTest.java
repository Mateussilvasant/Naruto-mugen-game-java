package br.com.narutomugen.game.states;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import br.com.narutomugen.game.entities.character.actions.Actions;
import br.com.narutomugen.game.entities.character.manager.ActionEntityManager;
import br.com.narutomugen.game.manager.actions.Action;
import br.com.narutomugen.game.manager.actions.ActionResponse;
import br.com.narutomugen.game.manager.actions.ActionState;
import br.com.narutomugen.game.manager.actions.SubAction;

public class ActionEntityManagerTest{

    private ActionEntityManager actionEntityManager;
    private HashMap<Actions, ActionState> actions;

    @Before
    public void setUp(){
        actionEntityManager = mock(ActionEntityManager.class);
        

        ActionState action = new Action(Actions.INICIAL){

			@Override
			public HashMap<Actions, SubAction> initAction(HashMap<Actions, SubAction> subActions) {
				return null;
			}

			@Override
			public ActionResponse update() {
                
                System.out.println("Atualizando o ação { "+ this.getId() +"}");

				return new ActionResponse(false,Actions.INICIAL);
			}

            @Override
            public void render(Actions action) {
                
                System.out.println("Renderizando a ação { "+ this.getId() +"}");

            }
            
        };

    }

    @Test
    public void executeStateToActionTest(){

        doNothing().when(actionEntityManager).update();
    }
}