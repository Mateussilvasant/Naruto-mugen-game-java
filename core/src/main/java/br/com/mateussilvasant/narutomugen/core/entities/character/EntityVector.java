package br.com.mateussilvasant.narutomugen.core.entities.character;

import br.com.mateussilvasant.narutomugen.core.gamecore.constants.EDirection;
import br.com.mateussilvasant.narutomugen.core.util.Vector2D;

public class EntityVector {

    public Vector2D position;
    public Vector2D velocity;
    public EDirection direction;
    public Vector2D initialPosition;
    public boolean isRightSide;

    public EntityVector(double x, double y, Vector2D velocity, EDirection direction) {
        this.position = new Vector2D(x, y);
        this.initialPosition = position;
        this.direction = direction;
        this.velocity = velocity;
    }
}
