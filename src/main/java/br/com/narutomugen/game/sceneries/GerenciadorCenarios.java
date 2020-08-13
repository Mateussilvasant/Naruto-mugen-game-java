package br.com.narutomugen.game.sceneries;

import java.util.ArrayList;

import javafx.scene.image.ImageView;

public class GerenciadorCenarios {

	public int x;
	public int y;
	public ArrayList<ImageView> Cenarios;

	public GerenciadorCenarios() {
		Cenarios = new ArrayList<ImageView>();
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

}
