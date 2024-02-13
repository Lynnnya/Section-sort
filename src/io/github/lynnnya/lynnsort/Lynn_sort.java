package io.github.lynnnya.lynnsort;

import io.github.lynnnya.linkedlist.*;

public class Lynn_sort<T extends Comparable<T>> {
    private final boolean debug = false;
    private final Linked_list<T> list;
    Linked_list<Node<T>> head_list = new Linked_list<>();
    Linked_list<Node<T>> tail_list = new Linked_list<>();
    private Node<T> start;
    private Node<T> head;

    public Lynn_sort(Linked_list<T> list) {
        this.list = list;
    }

    private boolean is_lesser(Node<T> current_node, Node<T> tb_compared) {
        return tb_compared.get_payload().compareTo(current_node.get_payload()) > 0;
    }

    private boolean is_greater(Node<T> current_node, Node<T> tb_compared) {
        return tb_compared.get_payload().compareTo(current_node.get_payload()) < 0;
    }

    private void s_section() {
        Node<T> current = head.get_next();
        start = head;

        while (current != list.get_tail().get_next()) {
            if (!is_greater(current, start)) {
                Node<T> next = current.get_next();
                list.insert(current, start);
                start = current;
                current = next;
            } else
                current = current.get_next();
        }
    }

    private Node<T> lf_section(Node<T> beginning) {
        Node<T> current = list.get_head();
        while (current.get_next() != null) {
            if (is_greater(current, current.get_next())) {
                return current;
            }
            current = current.get_next();
        }
        return null;
    }

    private void merge() {
        Node<T> current = list.get_head();
        Node<T> tail = null;
        Node<T> tail_next = null;
        tail = lf_section(current);
        if (debug) {
            System.out.println("\n" + "-----------------------------------------------");
            System.out.println("Tail = " + tail.get_payload());
            System.out.println("\n" + "-----------------------------------------------");
        }
        while (current.get_next() != null) {
            if (current == tail) {
                current = list.get_head();
                tail = lf_section(tail);
                if (debug) {
                    System.out.println("\n" + "-----------------------------------------------");
                    System.out.println("Tail = " + tail.get_payload());
                    System.out.println("\n" + "-----------------------------------------------");
                }
            }
            if (tail == null || tail.get_next() == null) {
                break;
            }
            if (is_greater(current.get_next(), tail.get_next())) {
                tail_next = tail.get_next();
                if (debug) {
                    System.out.println("\n" + "-----------------------------------------------");
                    System.out.println("To be swapped = " + tail.get_next().get_payload());
                    System.out.println("Current = " + current.get_payload());
                    System.out.println("\n" + "-----------------------------------------------");
                }
                list.insert(tail.get_next(), current.get_next());
                if (debug) {
                    list.to_string();
                    System.out.println("\n" + "-----------------------------------------------");
                }
                if (tail == null || tail.get_next() == null) {
                    break;
                }
                if (is_greater(tail_next, tail.get_next())) {
                    current = list.get_head();
                }
            } else {
                current = current.get_next();
            }
        }
    }

    public void sort() {
        head = list.get_head();
        while (head != null) {
            s_section();
            head = head.get_next();
        }
        if (debug) {
            System.out.println("\n" + "-----------------------------------------------");
            this.list.to_string();
            System.out.println("\n" + "-----------------------------------------------");
        }
        merge();
    }
}
/*
 * private void tail_merge () {
 * Node<T> current = tail_list.get_tail().get_payload();
 * Node<T> end =
 * tail_list.get_tail().get_previous().get_payload().get_previous();
 * Node<T> next;
 * while (this.head != this.tail.get_next()) {
 * if (is_greater(current, this.head) && is_lesser(current,
 * this.head.get_next())) {
 * if (!is_lesser(current.get_next(), this.head.get_next())) {
 * next = current.get_next();
 * list.insert(current, this.head.get_next());
 * current = next;
 * continue;
 * }
 * if (is_lesser(end, this.head.get_next())) {
 * list.insert(current, end, this.head.get_next());
 * break;
 * }
 * Node<T> temp = current.get_previous();
 * while (is_lesser(current.get_next(), this.head.get_next()))
 * current = current.get_next();
 * next = current.get_next();
 * list.insert(temp.get_next(), current, this.head.get_next());
 * current = next;
 * }
 * this.head = this.head.get_next();
 * }
 * }
 */

/*
 * Node<Node<T>> current = head_list.get_head();
 * while (current != head_list.get_tail().get_next()) {
 * Node<T> inner = current.get_payload();
 * System.out.print(inner.get_payload() + ", ");
 * current = current.get_next();
 * }
 * System.out.print("\n");
 * current = tail_list.get_head();
 * while (current != head_list.get_tail().get_next()) {
 * Node<T> inner = current.get_payload();
 * System.out.print(inner.get_payload() + ", ");
 * current = current.get_next();
 * }
 * System.out.print("\n");
 */

/*
 * package io.github.lynnnya.lynnsort;
 * 
 * import io.github.lynnnya.linked.list.*;
 * public class Lynn_sort<T extends Comparable<T>> {
 * private final Linked_list<T> list;
 * private Node<T> start;
 * private Node<T> head;
 * private Node<T> tail;
 * public Lynn_sort (Linked_list<T> list) {
 * this.list = list;
 * }
 * private boolean is_lesser (Node<T> current_node, Node<T> tb_compared) {
 * return tb_compared.get_payload().compareTo(current_node.get_payload()) > 0;
 * }
 * private boolean is_greater (Node<T> current_node, Node<T> tb_compared) {
 * return tb_compared.get_payload().compareTo(current_node.get_payload()) < 0;
 * }
 * private void merge () {
 * 
 * }
 * private void sort_sections_additional () {
 * Node<T> current_node = this.head;
 * Node<T> new_tail = null;
 * Node<T> previous_node;
 * boolean already_executed = false;
 * while (current_node != this.tail.get_next()) {
 * if (is_greater(current_node, this.tail)) {
 * previous_node = current_node.get_previous();
 * list.insert(current_node, this.tail.get_next());
 * this.tail = current_node;
 * current_node = previous_node;
 * }
 * if (is_lesser(current_node, current_node.get_previous())) {
 * new_tail = current_node;
 * if (!already_executed) {
 * this.start = current_node;
 * already_executed = true;
 * }
 * }
 * current_node = list.iterate(current_node);
 * }
 * this.tail = new_tail;
 * }
 * private void sort_sections () {
 * Node<T> current_node = this.start;
 * Node<T> previous_node;
 * while (current_node != this.tail.get_next()) {
 * if (is_lesser(current_node, this.start)) {
 * previous_node = current_node.get_previous();
 * if (current_node == this.tail)
 * this.tail = previous_node;
 * list.insert(current_node, this.start);
 * this.start = current_node;
 * current_node = previous_node;
 * }
 * current_node = list.iterate(current_node);
 * }
 * this.head = this.start;
 * }
 * public void sort () {
 * this.head = list.get_head();
 * this.tail = list.get_tail();
 * 
 * while (true) {
 * sort_sections ();
 * sort_sections_additional();
 * if (this.tail == null)
 * break;
 * }
 * 
 * }
 * }
 * /*
 * public Lynn_sort(Linked_list<T> list) {
 * this.list = list;
 * }
 * private boolean is_lesser (Node<T> current_node) {
 * return tb_compared.get_payload().compareTo(current_node.get_payload()) < 0;
 * }
 * private void sort_sections () {
 * Node<T> sta_previous = this.start.get_previous();
 * while (current_node != end) {
 * if (is_lesser(current_node))
 * list.insert(current_node, tb_compared);
 * current_node = list.iterate(current_node);
 * }
 * }
 * public Linked_list<T> sort () {
 * boolean sudden_drop = true;
 * this.start = list.get_head();
 * this.end = list.get_tail().get_next();
 * 
 * while (sudden_drop) {
 * sort_sections(start, end);
 * }
 * return list;
 * }
 */
