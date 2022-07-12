package br.com.mateussilvasant.narutomugen.core.sceneries;

import java.util.ArrayList;

import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.textures.ITextureImage;

public class StageBase {

	private int x;
	private int y;

	private ArrayList<ITextureImage> scenes;

	public StageBase() {
		scenes = new ArrayList<ITextureImage>();
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public ITextureImage getFirstScene() {
		return scenes.get(0);
	}

	public void addScene(ITextureImage scene) {
		scenes.add(scene);
	}

	public void moveToCenter(int world_width, int world_height) {
		this.x = (world_width / 2) - (getFirstScene().getFirstFrame().getWidth() / 2);
		this.y = (world_height / 2) - (getFirstScene().getFirstFrame().getHeight() / 2);
	}

}
