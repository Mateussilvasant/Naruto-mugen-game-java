package br.com.mateussilvasant.narutomugen.core.manager.states;

import java.util.Objects;

public class State {

    public Integer id;
    private int count = 1;

    public State(Integer id) {
        this.id = id;
    }

    public State(int value) {
    }

    public int getCount() {
        return count;
    }

    public void addCount() {
        count++;
    }

    public void subCount() {
        count--;
    }

    public void resetState() {
        count = 1;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (o == null)
            return false;

        if (getClass() != o.getClass())
            return false;

        State state = (State) o;

        return Objects.equals(id, state.id);
    }

    @Override
    public String toString() {
        return "[" + id + "]";
    }

}