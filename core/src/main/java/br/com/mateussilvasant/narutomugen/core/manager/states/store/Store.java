package br.com.mateussilvasant.narutomugen.core.manager.states.store;

public interface Store<T> {

	public T getLast();

	public T getPreviousLast();

	public void store(T next);

	public T update();

	public T contains(T t);

	T getElement(int index);

	int size();

	boolean isEmpty();

}
