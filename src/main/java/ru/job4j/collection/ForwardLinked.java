package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {

    private int size = 0;
    private int modCount = 0;
    private Node<T> head;

    public void add(T value) {
        if (size == 0) {
            head = new Node<>(value, null);
        } else {
            Node<T> element = head;
            while (element.next != null) {
                element = element.next;
            }
            element.next = new Node<>(value, null);
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> result = head;
        int count = 0;
        while (index > count) {
            result = result.next;
            count++;
        }
        return result.item;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T oldItem = head.item;
        Node<T> newFirst = head.next;
        head.item = null;
        head.next = null;
        head = newFirst;
        return oldItem;
    }

    public void addFirst(T value) {
        Node<T> oldFirst = head;
        head = new Node<>(value, oldFirst);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final int expectedModCount = modCount;
            private Node<T> nextElement = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return nextElement != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<T> resultElement = nextElement;
                nextElement = nextElement.next;
                return resultElement.item;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}
