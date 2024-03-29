package br.com.mateussilvasant.narutomugen.core.entities.character.actions;

public enum ActionCommand {

    CORRER_DIREITA(0), CORRER_ESQUERDA(1), PARADO(2), PULAR(3), INICIAL(4), PODER_1(5);

    private Integer value;

    private ActionCommand(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
