package br.com.mateussilvasant.narutomugen.core.animation;

import br.com.mateussilvasant.narutomugen.core.gamecore.constants.EDirection;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.IGameRenderer;

public interface IFrame {

    public void renderFrame(double x, double y, IGameRenderer gameRenderer);

    public void renderFrame(int indexFrame, double x, double y, IGameRenderer gameRenderer);

    public void renderFrame(int indexFrame, double x, double y, EDirection direction, IGameRenderer gameRenderer);

    public void setDirection(EDirection direction);

    public void setAnimationVelocity(int velocity);

    public int getAnimationVelocity();

    public EDirection getDirection();

    public boolean isEndAnimation();

    public double getWidth();

    public double getHeight();

    public void noRepeatAnimation();
}
