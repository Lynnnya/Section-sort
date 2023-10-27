package io.github.lynnnya.lynnsort;

import io.github.lynnnya.linked.list.*;
public class Lynn_sort<T extends Comparable<T>> {
    private final Linked_list<T> list;
    private Node<T> start;
    private Node<T> head;
    private Node<T> tail;
    //private boolean have_sections = true;
    public Lynn_sort (Linked_list<T> list) {
        this.list = list;
    }
    private boolean is_lesser (Node<T> current_node, Node<T> tb_compared) {
        return tb_compared.get_payload().compareTo(current_node.get_payload()) > 0;
    }
    private boolean is_greater (Node<T> current_node, Node<T> tb_compared) {
        return tb_compared.get_payload().compareTo(current_node.get_payload()) < 0;
    }
    /*private void merge () {
        Node<T> current = list.get_head();
        Node<T> tb_compared = null;
        Node<T> first = list.get_head();
        while (current != list.get_tail()) {
            if (current.get_previous() == null) {
                current = current.get_next();
                continue;
            }
            if (!is_lesser(current, current.get_previous())) {
                current = current.get_next();
                continue;
            }
            have_sections = true;
            tb_compared = current;
        }
    }*/
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
            if (current_node.get_previous() == null || current_node.equals(this.start)) {
                current_node = current_node.get_next();
                continue;
            }
            if (is_lesser(current_node, current_node.get_previous())) {
                new_tail = current_node.get_previous();
                if (!already_executed) {
                    this.start = current_node;
                    //System.out.println("\n" + this.start.get_payload());
                    already_executed = true;
                }
            }
            current_node = current_node.get_next();
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
            current_node = current_node.get_next();
        }
        this.head = this.start;
    }
    public void sort () {
        this.head = list.get_head();
        this.tail = list.get_tail();
        this.start = this.head;
        Linked_list<Node<T>> head_list = new Linked_list<>();
        Linked_list<Node<T>> reverse_head_list = new Linked_list<>();

        int i = 1;
        while (true) {

            sort_sections ();

            /*System.out.print("run " + i + ", sort 1:   ");
            list.to_string();
            System.out.println("\n" + this.tail.get_payload());*/

            Node<Node<T>> head = new Node<>(this.head);
            head_list.add_node(head);
            sort_sections_additional();

            /*System.out.print("run " + i + ", sort 2:   ");
            list.to_string();
            System.out.println("\n" + this.tail.get_payload());*/

            i++;

            if (this.tail == null) {
                break;
            }
            Node<Node<T>> reverse_head = new Node<>(this.tail.get_next());
            reverse_head_list.add_node(reverse_head);
        }


        /*while (have_sections) {
            have_sections = false;
            merge ();
        }*/
    }
}
/*Node<Node<T>> current = head_list.get_head();
        while (current != head_list.get_tail().get_next()) {
            Node<T> inner = current.get_payload();
            System.out.print(inner.get_payload() + ", ");
            current = current.get_next();
        }
        System.out.print("\n");
        current = reverse_head_list.get_head();
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
