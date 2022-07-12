package br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics;

public enum BlendMode {

    ADDITIVE(1), MULTIPLY(2), DEFAULT(3);

    private int value;

    BlendMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
