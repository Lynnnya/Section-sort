package io.github.lynnnya.lynnsort;

import io.github.lynnnya.linkedlist.*;

public class Lynn_sort<T extends Comparable<T>> {
    private final boolean debug = false;
    private final Linked_list<T> list;
    Linked_list<Node<T>> head_list = new Linked_list<>();
    Linked_list<Node<T>> tail_list = new Linked_list<>();
    private Node<T> start;
    private Node<T> head;
    private Node<T> tail;
    private Node<T> end;

    public Lynn_sort(Linked_list<T> list) {
        this.list = list;
    }

    private boolean is_lesser(Node<T> current_node, Node<T> tb_compared) {
        return tb_compared.get_payload().compareTo(current_node.get_payload()) > 0;
    }

    private boolean is_greater(Node<T> current_node, Node<T> tb_compared) {
        return tb_compared.get_payload().compareTo(current_node.get_payload()) < 0;
    }

    private Node<T> check_section(Node<T> current) {
        Node<T> next;
        Node<T> temp = current.get_previous();
        while (!is_greater(current.get_next(), this.head.get_next()))
            current = current.get_next();
        next = current.get_next();
        list.insert(temp.get_next(), current, this.head.get_next());
        current = next;
        return current;
    }

    private void merge(Node<T> current) {
        Node<T> next;
        while (this.head != this.tail.get_next()) {

            if (debug) {
                System.out.println("\n" + "-----------------------------------------------");
                list.to_string();
                System.out.println("\n" + current.get_payload());
                System.out.println("\n" + this.head.get_payload());
                System.out.println("\n" + this.tail.get_payload());
                System.out.println("num after tail: " + this.tail.get_next().get_payload());
            }

            if (!is_lesser(current, this.head) && is_lesser(current, this.head.get_next())) {
                if (is_greater(current.get_next(), this.head.get_next())) {
                    next = current.get_next();
                    list.insert(current, this.head.get_next());
                    this.head = current.get_next();
                    current = next;
                    continue;
                }
                if (!is_greater(list.get_tail(), this.head.get_next())) {
                    list.insert(current, list.get_tail(), this.head.get_next());
                    break;
                }
                current = check_section(current);
            }
            this.head = this.head.get_next();
        }
    }

    private void head_merge(Node<Node<T>> segment) {
        System.out.println("\n" + "-----------------------------------------------");
        System.out.println(segment.get_payload().get_payload());

        Node<T> current = segment.get_next().get_payload();

        System.out.println("\n" + "-----------------------------------------------");
        System.out.println(current.get_payload());
        Node<T> next;
        if (segment.get_next().get_next() != null)
            this.end = segment.get_next().get_next().get_payload().get_previous();
        else {
            if (tail_list.length() == 1)
                this.end = tail_list.get_tail().get_payload().get_next();
            else
                this.end = list.get_tail();
        }
        while (this.head != this.tail.get_next()) {
            if (!is_lesser(current, this.head) && is_lesser(current, this.head.get_next())) {
                if (is_greater(current.get_next(), this.head.get_next())) {
                    next = current.get_next();
                    list.insert(current, this.head.get_next());
                    this.head = current.get_next();
                    current = next;
                    continue;
                }
                if (!is_greater(end.get_previous(), this.head.get_next())) {
                    list.insert(current, end.get_previous(), this.head.get_next());
                    break;
                }
                current = check_section(current);
            }
            this.head = this.head.get_next();
        }
    }
    private void sort_sections_additional() {
        Node<T> current_node = this.head.get_next();
        Node<T> next;
        boolean ran = false;
        this.end = this.tail;
        while (current_node != this.tail.get_next()) {
            if (current_node != this.head && !is_greater(current_node, this.head)) {
                next = current_node.get_next();
                if (current_node == this.tail)
                    this.tail = current_node.get_previous();
                list.insert(current_node, this.head);
                this.head = current_node;
                current_node = next;
                continue;
            }
            if (ran)
                continue;
            if (is_lesser(current_node, current_node.get_previous())) {
                this.start = current_node;
                ran = true;
                current_node = current_node.get_next();
            }

            Node<Node<T>> head = new Node<>(this.head);
            head_list.add_node(head);
        }
    }

    public void sort() {
        this.start = list.get_head();
        this.tail = list.get_tail();
        head_list = new Linked_list<>();
        tail_list = new Linked_list<>();

        if (debug) {
            System.out.println("\n" + "-----------------------------------------------");
            list.to_string();
        }

        while (this.tail != null) {
            this.head = this.start;
            sort_sections_additional();

            if (debug) {
                System.out.println("\n" + "-----------------------------------------------");
                Node<T> x = this.head;
                while (x != tail_list.get_tail().get_payload().get_next()) {
                    System.out.print(x.get_payload() + ", ");
                    x = x.get_next();
                }
                System.out.println("start of next search: " + this.start.get_payload());
            }
        }
        if (!head_list.get_tail().get_payload().equals(this.start)) {
            Node<Node<T>> head = new Node<>(this.start);
            head_list.add_node(head);
        }

        //System.out.println(this.start.get_payload());

        if (debug) {
            System.out.println("\n" + "-----------------------------------------------");
            Node<Node<T>> current = head_list.get_head();
            while (current != head_list.get_tail().get_next()) {
                System.out.print(current.get_payload().get_payload() + ", ");
                current = current.get_next();
            }
            System.out.println("\n" + "-----------------------------------------------");
            current = tail_list.get_head();
            while (current != tail_list.get_tail().get_next()) {
                System.out.print(current.get_payload().get_payload() + ", ");
                current = current.get_next();
            }
            System.out.println("\n" + "-----------------------------------------------");
            list.to_string();
        }

        if (debug) {
            System.out.println("\n" + "-----------------------------------------------");
            System.out.println("\n" + tail_list.get_tail().get_payload().get_payload());
            list.to_string();
        }

        int l = head_list.length();
        l++;

        while (head_list.length() > 0) {
            Node<Node<T>> current = head_list.get_head();

            System.out.println("\n" + "-----------------------------------------------");
            Node<Node<T>> x = head_list.get_head();
            while (x != head_list.get_tail().get_next()) {
                System.out.print(x.get_payload().get_payload() + ", ");
                x = x.get_next();
            }
            while (current != null) {
                if (current.get_next() != null) {
                    System.out.println("\n" + "-----------------------------------------------");
                    System.out.println("part 1 ran.");
                    this.head = current.get_payload();
                    this.tail = current.get_next().get_payload().get_previous();
                    head_merge(current);
                    head_list.remove(current.get_next());
                    current = current.get_next();
                    continue;
                }
                System.out.println("\n" + "-----------------------------------------------");
                System.out.println("part 2 ran.");
                this.head = list.get_head();
                this.tail = current.get_payload().get_previous();
                head_merge(current.get_previous());
                head_list.remove(current);
                break;
            }
        }

        if (debug) {
            System.out.println("\n" + "-----------------------------------------------");
            list.to_string();
            System.out.println("\n" + "-----------------------------------------------");
        }

        if (head_list.length() < 0 || tail_list.length() < 1)
            return;
        this.head = list.get_head();
        this.tail = this.end.get_previous();
        merge(this.end);
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
