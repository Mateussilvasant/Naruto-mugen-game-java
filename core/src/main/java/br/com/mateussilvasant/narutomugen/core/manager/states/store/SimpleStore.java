package br.com.mateussilvasant.narutomugen.core.manager.states.store;

public class SimpleStore<T> implements Store<T> {

    private T value;
    private T oldValue;

    @Override
    public T getLast() {
        return value;
    }

    @Override
    public T getPreviousLast() {
        return oldValue;
    }

    @Override
    public void store(T next) {

        if (value == null) {
            value = next;
        } else if (next != value) {
            oldValue = value;
        } else {
            value = next;
        }

    }

    @Override
    public T update() {
        return null;
    }

    @Override
    public T contains(T t) {

        if (t.equals(value)) {
            return value;
        } else if (t.equals(oldValue)) {
            return oldValue;
        }

        return null;
    }

    @Override
    public boolean isEmpty() {
        if (oldValue == null && value == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public T getElement(int index) {

        if (index == 0) {
            return oldValue;
        } else if (index == 1) {
            return value;
        }

        return null;

    }

    @Override
    public int size() {

        int size = 0;

        if (oldValue != null) {
            size++;
        } else if (value != null) {
            size++;
        }
        return size;

    }

}