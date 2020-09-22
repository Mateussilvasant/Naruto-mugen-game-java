package br.com.narutomugen.game.manager.objects;

import br.com.narutomugen.game.manager.states.store.SimpleStack;
import br.com.narutomugen.game.manager.states.store.Store;

public class Deposit<T> {

    private Store<T> store;
    private int size;

    public Deposit(int size) {
        this(size, new SimpleStack<T>(size));
    }

    public Deposit(int size, Store<T> store) {
        this.store = store;
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

        if (store.getAllElements().size() < size) {
            store.store(object);
        }

    }

}
