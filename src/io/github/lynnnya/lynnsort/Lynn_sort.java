package io.github.lynnnya.lynnsort;

import io.github.lynnnya.linkedlist.*;

public class Lynn_sort<T extends Comparable<T>> {
    private final Linked_list<T> list;
    Linked_list<Node<T>> head_list = new Linked_list<>();
    Linked_list<Node<T>> tail_list = new Linked_list<>();
    private Node<T> start;
    private Node<T> head;
    private Node<T> tail;
    public Lynn_sort (Linked_list<T> list) {
        this.list = list;
    }
    private boolean is_lesser (Node<T> current_node, Node<T> tb_compared) {
        return tb_compared.get_payload().compareTo(current_node.get_payload()) > 0;
    }
    private boolean is_greater (Node<T> current_node, Node<T> tb_compared) {
        return tb_compared.get_payload().compareTo(current_node.get_payload()) < 0;
    }
    private Node<T> check_section(Node<T> current) {
        Node<T> next;
        Node<T> temp = current.get_previous();
        while (is_lesser(current.get_next(), this.head.get_next()))
            current = current.get_next();
        next = current.get_next();
        list.insert(temp.get_next(), current, this.head.get_next());
        current = next;
        return current;
    }
    private void head_merge (Node<T> current) {
        Node<T> next;
        while (this.head != this.tail.get_next()) {
            if (is_greater(current, this.head) && is_lesser(current, this.head.get_next())) {
                if (!is_lesser(current.get_next(), this.head.get_next())) {
                    next = current.get_next();
                    list.insert(current, this.head.get_next());
                    current = next;
                    continue;
                }
                current = check_section(current);
            }
            this.head = this.head.get_next();
        }
    }
    private void head_merge () {
        Node<T> current = head_list.get_tail().get_payload();
        Node<T> end = tail_list.get_tail().get_payload();
        Node<T> next;
        while (this.head != this.tail.get_next()) {
            if (is_greater(current, this.head) && is_lesser(current, this.head.get_next())) {
                if (!is_lesser(current.get_next(), this.head.get_next())) {
                    next = current.get_next();
                    list.insert(current, this.head.get_next());
                    current = next;
                    continue;
                }
                if (is_lesser(end, this.head.get_next())) {
                    list.insert(current, end, this.head.get_next());
                    break;
                }
                current = check_section(current);
            }
            this.head = this.head.get_next();
        }
    }
    private void tail_merge () {
        Node<T> current = tail_list.get_tail().get_previous().get_payload();
        Node<T> end = tail_list.get_tail().get_payload().get_next();
        Node<T> previous;
        while (this.head != this.tail.get_previous()) {
            if (is_lesser(current, this.head) && is_greater(current, this.head.get_previous())) {
                if (!is_lesser(current.get_previous(), this.head.get_previous())) {
                    previous = current.get_previous();
                    list.insert(current, this.head);
                    current = previous;
                    continue;
                }
                if (is_greater(end, this.head.get_previous())) {
                    list.insert(end, current, this.head);
                    break;
                }
                Node<T> temp = current;
                while (is_greater(current.get_previous(), this.head.get_previous()))
                    current = current.get_previous();
                previous = current.get_previous();
                list.insert(current, temp, this.head);
                current = previous;
            }
            this.head = this.head.get_previous();
        }
    }
    private void sort_sections_additional () {
        Node<T> current_node = this.head;
        Node<T> new_tail = null;
        Node<T> next;
        boolean already_executed = false;
        while (current_node != this.tail.get_next()) {
            if (is_lesser(current_node, this.head)) {
                next = current_node.get_next();
                if (current_node == this.tail)
                    this.tail = current_node.get_previous();
                list.insert(current_node, this.head);
                this.head = current_node;
                current_node = next;
                continue;
            }
            if (is_greater(current_node, this.tail)) {
                next = current_node.get_next();
                if (current_node == this.head)
                    this.head = current_node.get_next();
                list.insert(current_node, this.tail.get_next());
                this.tail = current_node;
                current_node = next;
                continue;
            }
            if (current_node.get_previous() == null || current_node.equals(this.head)) {
                current_node = current_node.get_next();
                continue;
            }
            if (is_lesser(current_node, current_node.get_previous())) {
                if (!already_executed) {
                    this.start = current_node;
                    already_executed = true;
                }
                new_tail = current_node.get_previous();
            }
            current_node = current_node.get_next();
        }
        Node<Node<T>> reverse_head = new Node<>(this.tail);
        tail_list.add_node(reverse_head);
        this.tail = new_tail;
    }

    public void sort () {
        this.start = list.get_head();
        this.tail = list.get_tail();
        head_list = new Linked_list<>();
        tail_list = new Linked_list<>();

        while (this.tail != null) {
            this.head = this.start;
            sort_sections_additional();
            Node<Node<T>> head = new Node<>(this.head);
            head_list.add_node(head);

        }
        while (tail_list.length() > 1) {
            this.head = tail_list.get_tail().get_previous().get_previous().get_payload();
            this.tail = tail_list.get_tail().get_previous().get_payload().get_next();
            tail_merge();
            tail_list.remove(tail_list.get_tail().get_previous());
        }
        while (head_list.length() > 0) {
            this.head = head_list.get_tail().get_previous().get_payload();
            this.tail = head_list.get_tail().get_payload().get_previous();
            head_merge ();
            head_list.remove(head_list.get_tail());
        }
        this.head = list.get_head();
        this.tail = tail_list.get_tail().get_payload();
        head_merge(tail_list.get_tail().get_payload().get_next());
    }
}

/*private void tail_merge () {
        Node<T> current = tail_list.get_tail().get_payload();
        Node<T> end = tail_list.get_tail().get_previous().get_payload().get_previous();
        Node<T> next;
        while (this.head != this.tail.get_next()) {
            if (is_greater(current, this.head) && is_lesser(current, this.head.get_next())) {
                if (!is_lesser(current.get_next(), this.head.get_next())) {
                    next = current.get_next();
                    list.insert(current, this.head.get_next());
                    current = next;
                    continue;
                }
                if (is_lesser(end, this.head.get_next())) {
                    list.insert(current, end, this.head.get_next());
                    break;
                }
                Node<T> temp = current.get_previous();
                while (is_lesser(current.get_next(), this.head.get_next()))
                    current = current.get_next();
                next = current.get_next();
                list.insert(temp.get_next(), current, this.head.get_next());
                current = next;
            }
            this.head = this.head.get_next();
        }
    }*/

/*Node<Node<T>> current = head_list.get_head();
        while (current != head_list.get_tail().get_next()) {
            Node<T> inner = current.get_payload();
            System.out.print(inner.get_payload() + ", ");
            current = current.get_next();
        }
        System.out.print("\n");
        current = tail_list.get_head();
        while (current != head_list.get_tail().get_next()) {
            Node<T> inner = current.get_payload();
            System.out.print(inner.get_payload() + ", ");
            current = current.get_next();
        }
        System.out.print("\n");*/




/*package io.github.lynnnya.lynnsort;

import io.github.lynnnya.linked.list.*;
public class Lynn_sort<T extends Comparable<T>> {
    private final Linked_list<T> list;
    private Node<T> start;
    private Node<T> head;
    private Node<T> tail;
    public Lynn_sort (Linked_list<T> list) {
        this.list = list;
    }
    private boolean is_lesser (Node<T> current_node, Node<T> tb_compared) {
        return tb_compared.get_payload().compareTo(current_node.get_payload()) > 0;
    }
    private boolean is_greater (Node<T> current_node, Node<T> tb_compared) {
        return tb_compared.get_payload().compareTo(current_node.get_payload()) < 0;
    }
    private void merge () {

    }
    private void sort_sections_additional () {
        Node<T> current_node = this.head;
        Node<T> new_tail = null;
        Node<T> previous_node;
        boolean already_executed = false;
        while (current_node != this.tail.get_next()) {
            if (is_greater(current_node, this.tail)) {
                previous_node = current_node.get_previous();
                list.insert(current_node, this.tail.get_next());
                this.tail = current_node;
                current_node = previous_node;
            }
            if (is_lesser(current_node, current_node.get_previous())) {
                new_tail = current_node;
                if (!already_executed) {
                    this.start = current_node;
                    already_executed = true;
                }
            }
            current_node = list.iterate(current_node);
        }
        this.tail = new_tail;
    }
    private void sort_sections () {
        Node<T> current_node = this.start;
        Node<T> previous_node;
        while (current_node != this.tail.get_next()) {
            if (is_lesser(current_node, this.start)) {
                previous_node = current_node.get_previous();
                if (current_node == this.tail)
                    this.tail = previous_node;
                list.insert(current_node, this.start);
                this.start = current_node;
                current_node = previous_node;
            }
            current_node = list.iterate(current_node);
        }
        this.head = this.start;
    }
    public void sort () {
        this.head = list.get_head();
        this.tail = list.get_tail();

        while (true) {
            sort_sections ();
            sort_sections_additional();
            if (this.tail == null)
                break;
        }

    }
}
    /*
    *public Lynn_sort(Linked_list<T> list) {
        this.list = list;
    }
    private boolean is_lesser (Node<T> current_node) {
        return tb_compared.get_payload().compareTo(current_node.get_payload()) < 0;
    }
    private void sort_sections () {
        Node<T> sta_previous = this.start.get_previous();
        while (current_node != end) {
            if (is_lesser(current_node))
                list.insert(current_node, tb_compared);
            current_node = list.iterate(current_node);
        }
    }
    public Linked_list<T> sort () {
        boolean sudden_drop = true;
        this.start = list.get_head();
        this.end = list.get_tail().get_next();

        while (sudden_drop) {
            sort_sections(start, end);
        }
        return list;
    }*/
