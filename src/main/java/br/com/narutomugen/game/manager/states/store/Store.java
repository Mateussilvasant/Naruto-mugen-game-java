package br.com.narutomugen.game.manager.states.store;

import java.util.List;

public interface Store<T> {

	public T getLast();

	public T getPreviousLast();

	public void store(T next);

	public T update();

	public T contains(T t);

	List<T> getAllElements();

	boolean isEmpty();


}
