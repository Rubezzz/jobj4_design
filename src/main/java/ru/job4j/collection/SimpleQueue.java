package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {

    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    int count = 0;
    boolean inIsMain = true;

    public T poll() {
        if (count == 0) {
            throw new NoSuchElementException();
        }

        for (int i = 0; i < count - 1; i++) {
            if (inIsMain) {
                out.push(in.pop());
            } else {
                in.push(out.pop());
            }
        }
        T result = inIsMain ? in.pop() : out.pop();
        count--;
        inIsMain = !inIsMain;
        return result;
    }

    public void push(T value) {
        if (inIsMain) {
            in.push(value);
        } else {
            out.push(value);
        }
        count++;
    }
}
