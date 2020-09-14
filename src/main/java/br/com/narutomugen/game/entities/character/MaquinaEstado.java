package br.com.narutomugen.game.entities.character;

import br.com.narutomugen.game.entities.character.actions.ActionCommand;
import br.com.narutomugen.game.manager.actions.ActionComponent;

public class MaquinaEstado {
    
    private ActionComponent[] estados;
    private ActionComponent estadoAtual;
    private ActionComponent estadoAnterior;
    private int ponteiro;

    public MaquinaEstado(int quantidadeEstados) {
        estados = new ActionComponent[quantidadeEstados];
        ponteiro = 0;
    }

    public void transitar(ActionCommand actions) {

        ActionComponent estado = estados[actions.getValue()];
        estado.changeRepeat(true);

        if (estadoAnterior == null) {
            estadoAnterior = estado;
        } else if (estado.getId()!= estadoAtual.getId()) {
            estadoAnterior = estadoAtual;
        } 

        estadoAtual = estado;
    }

    public void adicionarEstado(ActionComponent estado) {
        estados[ponteiro] = estado;
        ponteiro++;
    }

    public ActionComponent getEstadoAtual() {
        return estadoAtual;
    }

    public ActionComponent getEstadoAnterior() {
        return estadoAnterior;
    }

    public boolean verificaEstadoAnterior(int value) {
        return estadoAnterior.getId() == value;
    }

    public boolean verificaEstadoAtual(int value) {
        return estadoAtual.getId() == value;
    }

    public boolean comparar(ActionCommand estado) {
        return estadoAtual.getId() == estado.getValue();
    }

}
