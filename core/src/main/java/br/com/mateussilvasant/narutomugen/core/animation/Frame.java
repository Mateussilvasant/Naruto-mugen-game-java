package br.com.mateussilvasant.narutomugen.core.animation;

import br.com.mateussilvasant.narutomugen.core.gamecore.GameManager;
import br.com.mateussilvasant.narutomugen.core.gamecore.constants.EDirection;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.IGameRenderer;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.textures.ITextureImage;

public class Frame implements IFrame {

    private ITextureImage sprite;

    private EDirection direction;

    private int velocity;

    private float frameCounter;

    private boolean repeatAnimation;

    private boolean isEndAnimation;

    public Frame(ITextureImage sprite, int velocity) {
        this.repeatAnimation = true;
        this.direction = EDirection.LEFT;
        this.isEndAnimation = false;
        this.velocity = velocity;
        this.sprite = sprite;
    }

    public Frame(ITextureImage spriteTextura, int velocity, EDirection direction) {
        this.repeatAnimation = true;
        this.isEndAnimation = false;
        this.direction = direction;
        this.velocity = velocity;
        this.sprite = spriteTextura;
    }

    public Frame(
            ITextureImage sprite, int velocity,
            EDirection direction,
            boolean repeatAnimation) {
        this.isEndAnimation = false;
        this.repeatAnimation = repeatAnimation;
        this.direction = direction;
        this.velocity = velocity;
        this.sprite = sprite;
    }

    @Override
    public void renderFrame(double x, double y, IGameRenderer gameRenderer) {
        gameRenderer.render(sprite, (int) frameCounter, x, y, direction);
        nextFrame();
    }

    @Override
    public void renderFrame(int indexFrame, double x, double y, IGameRenderer gameRenderer) {
        gameRenderer.render(sprite, indexFrame, x, y, direction);
        nextFrame();
    }

    @Override
    public void renderFrame(int indexFrame, double x, double y, EDirection direction, IGameRenderer gameRenderer) {
        gameRenderer.render(sprite, indexFrame, x, y, direction);
        nextFrame();
    }

    private void nextFrame() {

        if (!isEndAnimation) {

            frameCounter += (GameManager.DELTA_TIME() * velocity);

            if ((int) frameCounter > (sprite.getSize() - 1)) // restart
            {

                if (!repeatAnimation) {
                    isEndAnimation = true;
                } else {
                    frameCounter = 0;
                }

            }

        }

    }

    @Override
    public void setDirection(EDirection direction) {
        this.direction = direction;
    }

    @Override
    public void setAnimationVelocity(int velocity) {
        this.velocity = velocity;
    }

    @Override
    public int getAnimationVelocity() {
        return velocity;
    }

    @Override
    public EDirection getDirection() {
        return direction;
    }

    @Override
    public boolean isEndAnimation() {
        return isEndAnimation;
    }

    @Override
    public double getWidth() {
        return sprite.getFrame((int) frameCounter).getWidth();
    }

    @Override
    public double getHeight() {
        return sprite.getFrame((int) frameCounter).getHeight();
    }

    @Override
    public void noRepeatAnimation() {
        repeatAnimation = false;
    }

}
