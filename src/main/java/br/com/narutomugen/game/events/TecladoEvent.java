package br.com.narutomugen.game.events;

import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class TecladoEvent {

	private List<String> teclas;

	public TecladoEvent(Scene scene) {
		teclas = new ArrayList<String>();
		setEvent(scene);
	}

	public boolean pressionou(String tecla) {
		return teclas.contains(tecla);
	}

	public boolean semComando() {
		return teclas.isEmpty();
	}

	private void setEvent(Scene scene) {
		onKeyReleased(scene);
		onKeyPressed(scene);
	}

	private void onKeyReleased(Scene scene) {
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				teclas.remove(code);
			}
		});
	}

	private void onKeyPressed(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				String codigo = e.getCode().toString();

				if (!teclas.contains(codigo)) {
					teclas.add(codigo);
				}
			}
		});
	}

}
