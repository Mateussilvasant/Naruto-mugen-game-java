package br.com.narutomugen.game.manager.states.store;

public interface Store<T> {

	public T getLast();

	public T getPreviousLast();

	public void store(T next);

	public void update();

	public T contains(T t);


}
