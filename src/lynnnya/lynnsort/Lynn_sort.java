package lynnnya.lynnsort;

import lynnnya.linked.list.*;
public class Lynn_sort<T extends Comparable<T>> {
    private final Linked_list<T> list;
    private Linked_list<T> head_list;
    //private linked_list.Node<T> tb_compared;
    private Linked_list<T> reverse_head_list; //needs to start at tail
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
    private void sort_sections_additional () {
        Node<T> current_node = head;
        Node<T> new_head, new_tail;
        Node<T> previous_node;
        while (current_node != tail.get_next()) {
            if (is_greater(current_node, tail)) {
                previous_node = current_node.get_previous();
                list.insert(current_node, tail.get_next());
                current_node = previous_node;
            }
            current_node = list.iterate(current_node);
        }
    }
    private void sort_sections () {
        Node<T> current_node = head;
        Node<T> previous_node;
        while (current_node != tail.get_next()) {
            if (is_lesser(current_node, head)) {
                previous_node = current_node.get_previous();
                if (current_node == tail)
                    this.tail = previous_node;
                list.insert(current_node, head);
                this.head = current_node;
                current_node = previous_node;
            }
            current_node = list.iterate(current_node);
        }
    }
    public void sort () {
        boolean sudden_drop = true;
        this.head = list.get_head();
        this.tail = list.get_tail();

        while (sudden_drop) {
            sort_sections ();
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
