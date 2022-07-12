package br.com.mateussilvasant.narutomugen.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import br.com.mateussilvasant.narutomugen.core.NarutoMugen;

public class DesktopLauncher {
	public static void main(String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setIdleFPS(60);
		config.setForegroundFPS(60);
		config.useVsync(true);
		config.setTitle("Naruto Mugen");

		new Lwjgl3Application(new NarutoMugen(), config);

	}
}
