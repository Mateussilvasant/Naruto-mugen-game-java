package br.com.narutomugen.game;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

public abstract class JogoApp extends Application
{
    private Scene cena;
    private Stage estagio;
    private StackPane painel;
    private double escalaWindow;
    public static double alturaWindow;
    public static double larguraWindow;

    public JogoApp(double escalaWindow)
    {
	this.escalaWindow = escalaWindow;
    }

    @Override
    public void start(Stage stage) throws Exception
    {
	configurarPropriedades();
	iniciarComponentes(stage);
	stage.setScene(getScene());
	stage.setResizable(false);
	stage.show();
	onStart();
    }

    public abstract void onStart();

    public void iniciarComponentes(Stage stage)
    {
	estagio = stage;
	alturaWindow = ((Screen.getPrimary().getVisualBounds().getHeight() / 2) * escalaWindow);
	larguraWindow = ((Screen.getPrimary().getVisualBounds().getWidth() / 2) * escalaWindow);
	painel = new StackPane(new Rectangle(larguraWindow, alturaWindow));
	cena = new Scene(painel);
    }

    // Argumentos da JVM, propriedades do sistema, argumentos da linha de comando do
    // aplicativo
    private void configurarPropriedades()
    {

	// Otimizações de armazenamento em cache de forma
	System.setProperty("prism.cacheshapes", "false");

	System.setProperty("prism.verbose", "true");

	// Forçar GPU
	System.setProperty("prism.forceGPU", "true");

	// Usa o directX para camada de renderização
	System.setProperty("prism.order", "es2,d3d,es1,sw");

	// configura o limite máximo de VRAM para 2GB
	System.setProperty("prism.targetvram", "2G");

	// VSync - Sincronizar a taxa de atualização da tela com a quantidade de quadros
	// gerados no chip gráfico
	System.setProperty("prism.vsync", "true");

	// Configura o limite de taxa de quadros do JavaFX
	System.setProperty("javafx.animation.framerate", "100");

	// Otimizações de regiao suja (regiao que deve ser renderizada na tela
	// novamenta)
	System.setProperty("prism.threadcheck", "true");
	System.setProperty("prism.dirtyopts", "true");

	// Cache de regioes das imagens na tela
	System.setProperty("prism.disableRegionCaching", "false");

	// Ativa/Desativa o modo High DPI
	System.setProperty("prism.allowhidpi", "false");
    }

    public void adicionarComponente(Node node)
    {
	painel.getChildren().add(node);
    }

    public double getAlturaWindow()
    {
	return alturaWindow;
    }

    public double getLarguraWindow()
    {
	return larguraWindow;
    }

    public Scene getScene()
    {
	return cena;
    }

    public Stage getEstagio()
    {
	return estagio;
    }

}
