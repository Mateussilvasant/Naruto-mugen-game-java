package br.com.narutomugen.game.manager.states.store;

import java.util.Arrays;
import java.util.List;

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
    public List<T> getAllElements() {
        return Arrays.asList(oldValue,value);
    }

    @Override
    public boolean isEmpty() {
        if(oldValue == null && value == null){
            return true;
        } else {
            return false;
        }
    }





}