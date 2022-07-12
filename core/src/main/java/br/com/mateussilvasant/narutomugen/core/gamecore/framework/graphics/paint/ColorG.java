package br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.paint;

import javafx.scene.paint.Color;

public class ColorG {

    private Color color;

    public ColorG(Color color) {
        this.color = color;
    }

    public static ColorG hex(String hex) {
        return new ColorG(Color.valueOf(hex));
    }

    public ColorG interpolate(ColorG endValue, double t) {
        Color result = color.interpolate(endValue.fx(), t);
        return new ColorG(result);
    }

    private Color fx() {
        return color;
    }

    public com.badlogic.gdx.graphics.Color gdx() {

        com.badlogic.gdx.graphics.Color result = new com.badlogic.gdx.graphics.Color();
        result.set((float) color.getRed(), (float) color.getGreen(), (float) color.getBlue(),
                (float) color.getOpacity());

        return result;
    }

}
