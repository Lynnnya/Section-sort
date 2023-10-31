package io.github.lynnnya.linkedlist;

import io.github.lynnnya.lynnsort.Lynn_sort;
public class Linked_list<T extends Comparable<T>> {
    private Node<T> head;
    private Node<T> tail;
    private int length;
    public Linked_list () {
        this.head = null;
        this.length = 0;
    }
    public Linked_list (Node<T> node) {
        this.head = node;
        this.tail = node;
        this.length = 1;
    }
    public void add_node (Node<T> node) {
        if (head == null) {
            this.head = node;
            this.tail = node;
            return;
        }
        node.set_previous(this.tail);
        this.tail.set_next(node);
        this.tail = node;
        this.length ++;
    }
    public void add_node (T payload) {
        Node<T> node = new Node<>(payload);
        if (head == null) {
            this.head = node;
            this.tail = node;
            return;
        }
        node.set_previous(this.tail);
        this.tail.set_next(node);
        this.tail = node;
    }
    public Node<T> get_head () {
        return head;
    }
    public Node<T> get_tail () {
        return tail;
    }
    public int length () {
        return length;
    }
    public void remove (Node<T> tb_removed) {
        if (tb_removed == null)
            return;
        this.length--;
        Node<T> next = tb_removed.get_next();
        Node<T> previous = tb_removed.get_previous();
        if (next == null && previous == null) {
            this.head = null;
            this.tail = null;
            return;
        }
        if (next == null) {
            tb_removed.get_previous().set_next(null);
            this.tail = tb_removed.get_previous();
        }
        else
            tb_removed.get_next().set_previous(previous);

        if (previous == null) {
            tb_removed.get_next().set_previous(null);
            this.head = tb_removed.get_next();
        }
        else
            tb_removed.get_previous().set_next(next);
    }
    public void insert (Node<T> first, Node<T> last, Node<T> position) {
        Node<T> end_next = last.get_next();
        Node<T> begin_previous = first.get_previous();

        if (end_next == null && position == null)
            return;
        if (begin_previous == null)
            this.head = last.get_next();
        else
            first.get_previous().set_next(end_next);
        last.get_next().set_previous(begin_previous);

        if (position == null) {
            first.set_previous(this.tail);
            this.tail.set_next(first);
            last.set_next(null);
            this.tail = last;
            return;
        }
        if (position.get_previous() == null) {
            last.set_next(this.head);
            this.head.set_previous(last);
            first.set_previous(null);
            this.head = first;
            return;
        }

        Node<T> pos_previous = position.get_previous();
        position.get_previous().set_next(first);
        first.set_previous(pos_previous);
        last.set_next(position);
        position.set_previous(last);
    }
    public void insert (Node<T> tb_insert, Node<T> position) {
        Node<T> ins_next = tb_insert.get_next();
        Node<T> ins_previous = tb_insert.get_previous();
        if (ins_previous == null)
            this.head = tb_insert.get_next();
        else
            tb_insert.get_previous().set_next(ins_next);

        if (ins_next == null)
            this.tail = tb_insert.get_previous();
        else
            tb_insert.get_next().set_previous(ins_previous);

        if (position == null) {
            tb_insert.set_next(null);
            add_node(tb_insert);
            length--;
            return;
        }

        Node<T> pos_previous = position.get_previous();
        if (pos_previous == null)
            this.head = tb_insert;
        else
            position.get_previous().set_next(tb_insert);
        position.set_previous(tb_insert);

        tb_insert.set_next(position);
        tb_insert.set_previous(pos_previous);
    }
    public void swap (Node<T> current, Node<T> tb_swaped) {
        Node<T> cur_next = current.get_next();
        Node<T> cur_previous = current.get_previous();
        Node<T> tb_next = tb_swaped.get_next();
        Node<T> tb_previous = tb_swaped.get_previous();
        if (cur_next == null)
            this.tail = tb_swaped;
        else
            cur_next.set_previous(tb_swaped);
        tb_swaped.set_next(cur_next);

        if (tb_previous == null)
            this.head = current;
        else
            tb_previous.set_next(current);
        current.set_previous(tb_previous);

        current.set_next(tb_next);
        tb_swaped.set_previous(cur_previous);
    }
    public void sort () {
        if (head == null)
            return;
        Lynn_sort<T> sort = new Lynn_sort<>(this);
        sort.sort();
    }
    public void to_string () {
        Node<T> current = this.head;
        while (current != this.tail.get_next()) {
            System.out.print(current.get_payload() + ", ");
            current = current.get_next();
        }
    }
}
