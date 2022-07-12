package br.com.mateussilvasant.narutomugen.core.manager.objects;

import br.com.mateussilvasant.narutomugen.core.manager.states.store.SimpleStack;

public class Deposit<T> {

    private SimpleStack<T> store;
    private int size;

    public Deposit(int size) {
        this(size, new SimpleStack<T>(size));
    }

    public Deposit(int size, SimpleStack<T> simpleStack) {
        this.store = simpleStack;
        this.size = size;
    }

    public T newInstance(Class<T> type) {
        try {

            T instance = type.getDeclaredConstructor().newInstance();

            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e.fillInStackTrace());
        }
    }

    public T recover(Class<T> type) {

        if (store.isEmpty()) {
            return newInstance(type);
        } else {
            return store.update();
        }

    }

    public void release(T object) {

        ((Depositable) object).clear();

        if (store.size() < size) {
            store.store(object);
        }

    }

}
