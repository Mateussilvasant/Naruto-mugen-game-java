package br.com.mateussilvasant.narutomugen.core.app;

import javafx.application.Application;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

@Deprecated
public abstract class MainUI extends Application {
	private Scene cena;
	private Stage estagio;
	private StackPane painel;
	private double escalaWindow;
	public static double alturaWindow;
	public static double larguraWindow;

	public MainUI(double escalaWindow) {
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
		larguraWindow = ((Screen.getPrimary().getVisualBounds().getWidth() / 1.5) * escalaWindow);

		painel = new StackPane(new Rectangle(larguraWindow, alturaWindow));
		painel.setCache(true);
		painel.setCacheHint(CacheHint.SPEED);
		painel.setCacheShape(true);

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

	public Pane getViewPane() {
		return painel;
	}

}
