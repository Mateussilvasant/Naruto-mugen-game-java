package br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import space.earlygrey.shapedrawer.ShapeDrawer;

public interface IBatchRenderer {
    public Texture getWhiteBackgroundRegion();

    public SpriteBatch getBatchRenderer();

    public ShapeDrawer getShapeRenderer();

}
