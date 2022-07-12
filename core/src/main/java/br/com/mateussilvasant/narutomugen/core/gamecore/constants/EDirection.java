package br.com.mateussilvasant.narutomugen.core.gamecore.constants;

public enum EDirection {
    RIGHT(1), LEFT(2);

    private int value;

    private EDirection(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
