package br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics;

import br.com.mateussilvasant.narutomugen.core.gamecore.constants.EDirection;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.paint.ColorG;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.textures.ITextureImage;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.textures.SpriteImage;

public interface IGameRenderer {

    public void initGameRenderer();

    public void renderCircle(ColorG color, double x, double y, double radius, double life, BlendMode blending);

    public void render(SpriteImage image, double x, double y, BlendMode blending, ColorG color);

    public void render(SpriteImage image, double x, double y);

    public void render(ITextureImage img, Integer indexFrame, double x, double y, EDirection direction);

    public void render(ITextureImage img, Integer indexFrame, double x, double y);

}
