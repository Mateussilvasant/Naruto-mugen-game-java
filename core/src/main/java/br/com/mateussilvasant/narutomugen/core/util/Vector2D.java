package br.com.mateussilvasant.narutomugen.core.util;

import com.badlogic.gdx.math.Vector2;

public class Vector2D {

    public static final Vector2D ZERO = new Vector2D(0.0, 0.0);

    private Vector2 vector;

    public Vector2D(double x, double y) {
        this.vector = new Vector2((float) x, (float) y);
    }

    public Vector2D() {
        this.vector = ZERO.vector;
    }

    public double getX() {
        return vector.x;
    }

    public double getY() {
        return vector.y;
    }

    public Vector2D add(double x, int y) {

        Vector2 vector2 = vector.add((float) x, (float) y);
        this.vector = vector2;

        return new Vector2D(vector2.x, vector2.y);
    }

    public Vector2D subtract(double x, int y) {

        Vector2 vector2 = vector.sub((float) x, (float) y);
        this.vector = vector2;

        return new Vector2D(vector2.x, vector2.y);
    }

    public Vector2D add(Vector2D velocity) {

        Vector2 vector2 = vector.add(velocity.vector);
        this.vector = vector2;

        return new Vector2D(vector2.x, vector2.y);
    }

    public Vector2D multiply(double delta) {

        Vector2 vector2 = vector.scl((float) delta);
        this.vector = vector2;

        return new Vector2D(vector2.x, vector2.y);

    }

}
