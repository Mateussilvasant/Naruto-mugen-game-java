package br.com.mateussilvasant.narutomugen.core.manager.states.store.interfaces;

public interface IStack<T> {
    T remove();

    void add(T key);

    T getLast();

    boolean isEmpty();
}