package br.com.mateussilvasant.narutomugen.core.particle.system;

import java.util.function.Function;

import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.BlendMode;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.paint.ColorG;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.textures.ITextureImage;
import br.com.mateussilvasant.narutomugen.core.util.Vector2D;

public interface IEmitterLoader {

        public void initEmitter(Function<Particle, Vector2D> equationMotion,
                        Function<Vector2D, Vector2D> equationVelocity, BlendMode blending,
                        ColorG startColor,
                        ColorG endColor, double particleRadius, int maxEmissionParticles, double particleTime,
                        int time);

        public void initEmitter(Function<Particle, Vector2D> equationMotion,
                        Function<Vector2D, Vector2D> equationVelocity, BlendMode blending,
                        ITextureImage texture, ColorG startColor,
                        ColorG endColor,
                        double particleRadius, int maxEmissionParticles, double particleTime, int time);

}
