package br.com.mateussilvasant.narutomugen.core.events;

public interface IKeyboardListener {
    public boolean pressed(String key);

    public boolean noCommand();

    public void onKeyReleased(int keycode);

    public void onKeyPressed(int keycode);
}
