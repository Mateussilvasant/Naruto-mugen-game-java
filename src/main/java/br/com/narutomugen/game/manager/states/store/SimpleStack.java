package br.com.narutomugen.game.manager.states.store;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimpleStack<T> implements Store<T> {

    private List<T> stack;
    private int maxElements;
    private int count = -1;

    public SimpleStack(int maxElements) {
        stack = new ArrayList<T>();
        this.maxElements = maxElements;
    }

    @Override
    public T getLast() {
        if (stack.size() >= 1) {
            return stack.get(0);
        } else {
            return null;
        }
    }

    public T getPreviousLast() {

        if (stack.size() >= 2) {
            return stack.get(1);
        } else {
            return null;
        }

    }

    @Override
    public void store(T next) {
        if (!stack.contains(next)) {

            if (stack.size() < maxElements) {
                stack.add(next);
                count++;
            }

        }
    }

    @Override
    public void update() {
        stack.remove(0);
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

    @Override
	public List<T> getAllElements() {
		return stack;
	}

}