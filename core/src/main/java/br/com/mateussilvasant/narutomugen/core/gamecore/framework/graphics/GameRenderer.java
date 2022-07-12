package br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import br.com.mateussilvasant.narutomugen.core.gamecore.constants.EDirection;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.paint.ColorG;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.textures.ITextureImage;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.textures.SpriteImage;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class GameRenderer implements IGameRenderer, IBatchRenderer {

    private SpriteBatch batchRenderer;

    private ShapeDrawer shapeRenderer;

    @Override
    public void initGameRenderer() {

        if (batchRenderer == null && shapeRenderer == null) {
            this.batchRenderer = new SpriteBatch();
            this.shapeRenderer = new ShapeDrawer(batchRenderer,
                    new TextureRegion(getWhiteBackgroundRegion(), 0, 0, 1, 1));
        }

    }

    @Override
    public void renderCircle(ColorG color, double x, double y, double radius, double life, BlendMode blending) {

        if (blending != null) {
            setBlendBatch(blending);
        }

        shapeRenderer.filledCircle((float) x, (float) y, (float) radius, color.gdx());

        if (blending != null) {
            batchRenderer.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        }
    }

    @Override
    public void render(ITextureImage texture, Integer indexFrame, double x, double y, EDirection direction) {

        Texture img = texture.getFrame(indexFrame);

        if (direction.equals(EDirection.RIGHT)) {

            float newX = (float) (x - (img.getWidth() / 2));
            float newY = (float) (y - (img.getHeight() / 2));

            float newWidth = (float) img.getWidth();
            float newHeight = (float) img.getHeight();

            batchRenderer.draw(img, newX, newY, newWidth, newHeight);

        } else if (direction.equals(EDirection.LEFT)) {

            float newX = (float) (x + (img.getWidth() / 2));
            float newY = (float) (y + -img.getHeight() / 2);

            float newWidth = (float) -img.getWidth();
            float newHeight = (float) img.getHeight();

            batchRenderer.draw(img, newX, newY, newWidth, newHeight);

        }
    }

    @Override
    public void render(ITextureImage texture, Integer indexFrame, double x, double y) {
        Texture img = texture.getFrame(indexFrame);
        batchRenderer.draw(img, (float) x, (float) y);
    }

    @Override
    public void render(SpriteImage image, double x, double y, BlendMode blending, ColorG color) {

        if (blending != null) {
            setBlendBatch(blending);
        }

        batchRenderer.setColor(color.gdx());
        batchRenderer.draw(image.getTexture(), (float) x, (float) y);
        batchRenderer.setColor(Color.WHITE);

        if (blending != null) {
            batchRenderer.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        }

    }

    @Override
    public void render(SpriteImage image, double x, double y) {
        batchRenderer.draw(image.getTexture(), (float) x, (float) y);
    }

    @Override
    public Texture getWhiteBackgroundRegion() {

        Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.drawPixel(0, 0);

        Texture texture = new Texture(pixmap);
        pixmap.dispose();

        return texture;
    }

    @Override
    public SpriteBatch getBatchRenderer() {
        return batchRenderer;
    }

    @Override
    public ShapeDrawer getShapeRenderer() {
        return shapeRenderer;
    }

    private void setBlendBatch(BlendMode blendmode) {

        if (blendmode.equals(BlendMode.ADDITIVE)) {

            batchRenderer.setBlendFunctionSeparate(GL20.GL_ONE,
                    GL20.GL_ONE, GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_COLOR);

        } else if (blendmode.equals(BlendMode.MULTIPLY)) {

            batchRenderer.setBlendFunctionSeparate(GL20.GL_SRC_COLOR, GL20.GL_ONE,
                    GL20.GL_ONE, GL20.GL_ZERO);

        } else if (blendmode.equals(BlendMode.DEFAULT)) {

            batchRenderer.setBlendFunction(GL20.GL_SRC_ALPHA,
                    GL20.GL_ONE_MINUS_SRC_ALPHA);
        }

    }

}
