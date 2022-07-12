package br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.textures;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class TextureImage implements ITextureImage {

	public ArrayList<Texture> images;

	public static final String ASSETS_FOLDER = "assets/";

	public TextureImage() {
		images = new ArrayList<Texture>();
	}

	@Override
	public void loadTextureByFile(String filepath, int width, int heigth) {

		Pixmap pixmap200 = new Pixmap(Gdx.files.internal(ASSETS_FOLDER + filepath));

		Pixmap pixmap100 = new Pixmap(width, heigth, pixmap200.getFormat());
		pixmap100.drawPixmap(pixmap200,
				0, 0, pixmap200.getWidth(), pixmap200.getHeight(),
				0, 0, pixmap100.getWidth(), pixmap100.getHeight());

		Texture texture = new Texture(pixmap100);
		pixmap200.dispose();
		pixmap100.dispose();

		images.add(texture);
	}

	@Override
	public void loadTextureByFile(String filepath, double scale) {

		Texture temp = new Texture(Gdx.files.internal(ASSETS_FOLDER + filepath));
		images.add(resizeTextura(temp, scale));

	}

	@Override
	public void loadTexturesByFolder(String folderpath, double scale) {

		File[] texturas = new File(getPath("/" + ASSETS_FOLDER + folderpath)).listFiles();

		for (int i = 0; i < texturas.length; i++) {

			Texture temp = new Texture(Gdx.files.internal(texturas[i].getPath()));
			images.add(resizeTextura(temp, scale));

		}
	}

	@Override
	public Texture getFrame(int pos) {
		return images.get(pos);
	}

	@Override
	public Texture getFirstFrame() {
		return images.get(0);
	}

	@Override
	public int getSize() {
		return images.size();
	}

	private URI getPath(String caminho) {

		try {
			return TextureImage.class.getResource(caminho).toURI();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return null;
	}

	private Texture resizeTextura(Texture textura, double scale) {

		if (scale != 0) {

			textura.getTextureData().prepare();

			Pixmap pixmap200 = textura.getTextureData().consumePixmap();

			Pixmap pixmap100 = new Pixmap((int) (pixmap200.getWidth() * scale), (int) (pixmap200.getHeight() * scale),
					pixmap200.getFormat());

			pixmap100.drawPixmap(pixmap200, 0, 0, pixmap200.getWidth(), pixmap200.getHeight(), 0, 0,
					pixmap100.getWidth(),
					pixmap100.getHeight());

			Texture texture = new Texture(pixmap100);

			pixmap200.dispose();
			pixmap100.dispose();

			return texture;
		}

		return textura;
	}

}
