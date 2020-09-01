package br.com.narutomugen.game.entities.character.actions;

public enum Actions {

    CORRER_DIREITA(0), CORRER_ESQUERDA(1), PARADO(2), PULAR(3), INICIAL(4), PODER_1(5);

    private Integer value;

    private Actions(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
