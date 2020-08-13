package br.com.narutomugen.game.graphics.textures;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TexturaSprite {

	public ArrayList<ImageView> imagens;

	public TexturaSprite() {
		imagens = new ArrayList<ImageView>();
	}

	public int getSize() {
		return imagens.size();
	}

	public TexturaSprite imagemToTextura(Image image, int velocidade) {
		TexturaSprite texturaSprite = new TexturaSprite();
		texturaSprite.addFrame(image);
		return texturaSprite;
	}

	public void addFrame(InputStream inputStream) {
		imagens.add(new ImageView(new Image(inputStream)));
	}

	public void carregarTextura(String caminhoTexturas, double escala) {
		File[] texturas = new File(getURL(caminhoTexturas)).listFiles();

		for (int i = 0; i < texturas.length; i++) {
			try {

				Image temp = new Image(new FileInputStream(texturas[i]));

				Image image = new Image(new FileInputStream(texturas[i]), temp.getWidth() * escala,
						temp.getHeight() * escala, true, false);

				imagens.add(new ImageView(image));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	private URI getURL(String caminho) {
		try {
			return TexturaSprite.class.getResource(caminho).toURI();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addFrame(Image imagem) {
		imagens.add(new ImageView(imagem));
	}

	public Image getFrame(int pos) {
		return imagens.get(pos).getImage();
	}

}
