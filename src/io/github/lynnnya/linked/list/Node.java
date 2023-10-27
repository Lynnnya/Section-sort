package io.github.lynnnya.linked.list;

public class Node<T> implements Comparable<Node<T>> {
    private final T payload;
    private Node<T> previous;
    private Node<T> next;
    public Node () {
        payload = null;
    }
    public Node (T payload) {
        this.payload = payload;
        this.next = null;
    }
    public void set_previous (Node<T> previous) {
        this.previous = previous;
    }
    public Node<T> get_previous () {
        return previous;
    }
    public void set_next (Node<T> next) {
        this.next = next;
    }
    public Node<T> get_next () {
        return next;
    }
    public T get_payload () {
        return payload;
    }

    @Override
    public int compareTo(Node<T> o) {
        return 0;
    }
}
