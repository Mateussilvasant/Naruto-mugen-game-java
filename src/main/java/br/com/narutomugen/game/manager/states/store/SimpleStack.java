package br.com.narutomugen.game.manager.states.store;

import java.util.LinkedList;
import java.util.Optional;

public class SimpleStack<T> implements Store<T> {

    private LinkedList<T> stack;
    private int maxElements;

    public SimpleStack(int maxElements) {
        stack = new LinkedList<T>();
        this.maxElements = maxElements;
    }

    @Override
    public T getLast() {
        return stack.getLast();
    }

    public T getPreviousLast() {

        T last = stack.getLast();

        if (stack.size() >= 2) {

            int index = stack.indexOf(last);

            return stack.get(index - 1);

        }

        return null;
    }

    @Override
    public void store(T next) {
        if (!stack.contains(next)) {

            if (stack.size() <= maxElements) {
                stack.push(next);
            }

        }
    }

    @Override
    public void update() {
        stack.removeLast();
    }

    @Override
    public T contains(T element) {

        Optional<T> found = stack.stream().filter(t -> t.equals(element)).findFirst();

        if (found.isPresent()) {
            return found.get();
        } else {
            return null;
        }

    }

}