package br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.textures;

import com.badlogic.gdx.graphics.Texture;

public interface ITextureImage {

    public void loadTextureByFile(String filepath, int width, int heigth);

    public void loadTextureByFile(String filepath, double scale);

    public void loadTexturesByFolder(String folderpath, double scale);

    public Texture getFrame(int position);

    public Texture getFirstFrame();

    public int getSize();

}
