package br.com.mateussilvasant.narutomugen.core.entities.character.actions.runright;

import br.com.mateussilvasant.narutomugen.core.animation.Frame;
import br.com.mateussilvasant.narutomugen.core.animation.IFrame;
import br.com.mateussilvasant.narutomugen.core.entities.character.Char;
import br.com.mateussilvasant.narutomugen.core.entities.character.EntityVector;
import br.com.mateussilvasant.narutomugen.core.entities.character.actions.ActionCommand;
import br.com.mateussilvasant.narutomugen.core.gamecore.constants.EDirection;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.IGameRenderer;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.textures.ITextureImage;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.textures.TextureImage;
import br.com.mateussilvasant.narutomugen.core.manager.actions.Action;
import br.com.mateussilvasant.narutomugen.core.manager.actions.ActionResponse;

public class Runright extends Action {

    private Char character;
    private IFrame rightAnimation;

    public Runright(ActionCommand command, Char character) {
        super(command);
        this.character = character;
    }

    @Override
    public void loadResources(String characterName) {

        ITextureImage textureRight = new TextureImage();
        textureRight.loadTexturesByFolder("/personagens/" + characterName + "/tiles/Correndo", 1.0);

        rightAnimation = new Frame(textureRight, 7,
                character.getEntity().direction);

    }

    @Override
    public ActionResponse update(double delta, IGameRenderer gameRenderer) {

        EntityVector entity = character.getEntity();

        entity.direction = EDirection.RIGHT;
        entity.position = entity.position.add(entity.velocity.multiply(delta));

        rightAnimation.renderFrame(entity.position.getX(), entity.position.getY(), gameRenderer);

        return new ActionResponse(false, ActionCommand.PARADO);
    }

    @Override
    public void render(ActionCommand action) {
        // TODO Auto-generated method stub

    }

}
