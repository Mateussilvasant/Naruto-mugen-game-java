package br.com.mateussilvasant.narutomugen.core.entities.character;

import br.com.mateussilvasant.narutomugen.core.gamecore.constants.EDirection;
import br.com.mateussilvasant.narutomugen.core.util.Vector2D;

public class Char {

    private EntityVector entity;

    public Char(double x, double y, EDirection direction, Vector2D velocity) {
        this.entity = new EntityVector(x, y, velocity, direction);
    }

    public Char(double x, double y, EDirection direction) {
        this(x, y, direction, new Vector2D(150, 0));
    }

    public EntityVector getEntity() {
        return entity;
    }
}
