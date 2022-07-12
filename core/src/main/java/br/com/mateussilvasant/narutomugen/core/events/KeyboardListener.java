package br.com.mateussilvasant.narutomugen.core.events;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Input.Keys;

public class KeyboardListener implements IKeyboardListener {

	private List<String> keys;

	public KeyboardListener() {
		keys = new ArrayList<String>();
	}

	@Override
	public boolean pressed(String tecla) {
		return keys.contains(tecla);
	}

	@Override
	public boolean noCommand() {
		return keys.isEmpty();
	}

	@Override
	public void onKeyReleased(int keycode) {
		keys.remove(Keys.toString(keycode).toUpperCase());
	}

	@Override
	public void onKeyPressed(int keycode) {

		String code = Keys.toString(keycode).toUpperCase();

		if (!keys.contains(code)) {
			keys.add(code);
		}
	}

}
