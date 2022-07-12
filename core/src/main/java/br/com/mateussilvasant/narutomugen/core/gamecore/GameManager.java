package br.com.mateussilvasant.narutomugen.core.gamecore;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.Graphics.Monitor;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import br.com.mateussilvasant.narutomugen.core.events.KeyboardListener;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.GameRenderer;
import br.com.mateussilvasant.narutomugen.core.gamecore.framework.graphics.IGameRenderer;
import space.earlygrey.shapedrawer.ShapeDrawer;

public abstract class GameManager implements ApplicationListener, InputProcessor, IUpdatable {

    private GameRenderer gameRenderer;

    private OrthographicCamera camera;

    private ExtendViewport viewport;

    private KeyboardListener inputEvent;

    private int windowWidth;

    private int windowHeigth;

    public static int WORLD_WIDTH;

    public static int WORLD_HEIGHT;

    public static float SCALE_WINDOW = 2.00f;

    public GameManager() {
        this.gameRenderer = new GameRenderer();
        this.inputEvent = new KeyboardListener();
    }

    @Override
    public void create() {

        Monitor primaryMonitor = Gdx.graphics.getPrimaryMonitor();
        DisplayMode display = Gdx.graphics.getDisplayMode(primaryMonitor);

        float x1 = Math.max(display.width, display.height);
        float y1 = Math.min(display.width, display.height);

        SCALE_WINDOW = x1 / y1;

        windowWidth = (int) ((display.width / 2) * SCALE_WINDOW);
        windowHeigth = (int) ((display.height / 2) * SCALE_WINDOW);

        WORLD_HEIGHT = (int) (windowHeigth + (windowHeigth * 0.80f));
        WORLD_WIDTH = (int) (windowWidth + (windowWidth * 0.80f));

        Gdx.graphics.setResizable(false);
        Gdx.graphics.setWindowedMode(windowWidth, windowHeigth);

        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        viewport = new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        viewport.apply(true);

        gameRenderer.initGameRenderer();

        Gdx.input.setInputProcessor(this);

        initGame();

    }

    public abstract void initGame();

    public abstract void releaseGame();

    public abstract void resizeWindow();

    @Override
    public void render() {

        SpriteBatch context = gameRenderer.getBatchRenderer();
        ShapeDrawer shapeRenderer = gameRenderer.getShapeRenderer();

        shapeRenderer.update();
        viewport.getCamera().update();
        gameRenderer.getBatchRenderer().setProjectionMatrix(viewport.getCamera().combined);

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        context.enableBlending();

        context.begin();
        update(DELTA_TIME(), gameRenderer);
        context.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        inputEvent.onKeyPressed(keycode);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        inputEvent.onKeyReleased(keycode);
        return false;
    }

    @Override
    public void dispose() {
        gameRenderer.getBatchRenderer().dispose();
        releaseGame();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        resizeWindow();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    public KeyboardListener keyboardListener() {
        return inputEvent;
    }

    public IGameRenderer gameRender() {
        return gameRenderer;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public float getWindowWidth() {
        return windowWidth;
    }

    public float getWindowHeigth() {
        return windowHeigth;
    }

    public static float DELTA_TIME() {
        return Gdx.graphics.getDeltaTime();
    }

}
