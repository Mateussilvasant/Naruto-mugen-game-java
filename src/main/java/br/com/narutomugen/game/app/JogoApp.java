package br.com.narutomugen.game.app;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

public abstract class JogoApp extends Application {
	private Scene cena;
	private Stage estagio;
	private StackPane painel;
	private double escalaWindow;
	public static double alturaWindow;
	public static double larguraWindow;

	public JogoApp(double escalaWindow) {
		this.escalaWindow = escalaWindow;
	}

	@Override
	public void start(Stage stage) throws Exception {
		iniciarComponentes(stage);
		stage.setScene(getScene());
		stage.setResizable(false);
		stage.show();
		onStart();
	}

	public abstract void onStart();

	public void iniciarComponentes(Stage stage) {
		estagio = stage;
		alturaWindow = ((Screen.getPrimary().getVisualBounds().getHeight() / 2) * escalaWindow);
		larguraWindow = ((Screen.getPrimary().getVisualBounds().getWidth() / 2) * escalaWindow);
		painel = new StackPane(new Rectangle(larguraWindow, alturaWindow));
		cena = new Scene(painel);
	}

	public void adicionarComponente(Node node) {
		painel.getChildren().add(node);
	}

	public double getAlturaWindow() {
		return alturaWindow;
	}

	public double getLarguraWindow() {
		return larguraWindow;
	}

	public Scene getScene() {
		return cena;
	}

	public Stage getEstagio() {
		return estagio;
	}

}
