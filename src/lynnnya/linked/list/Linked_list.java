package lynnnya.linked.list;

import lynnnya.lynnsort.Lynn_sort;
public class Linked_list<T extends Comparable<T>> {
    private Node<T> head;
    private Node<T> tail;
    public Linked_list (Node<T> node) {
        this.head = node;
        this.tail = node;
    }
    public void add_node (Node<T> node) {
        node.set_previous(this.tail);
        this.tail.set_next(node);
        this.tail = node;
    }
    public void add_node (T payload) {
        Node<T> node = new Node<>(payload);
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
        int length = 0;
        Node<T> current_node = head;
        while (current_node != null) {
            length ++;
            current_node = iterate(current_node);
        }
        return length;
    }
    public Node<T> iterate (Node<T> current_node) {
        return current_node.get_next();
    }
    public void insert () {
        //sdf
    }
    public void insert (Node<T> tb_insert, Node<T> position) {
        if (position == null) {
            add_node(tb_insert);
            return;
        }
        Node<T> ins_next = tb_insert.get_next();
        Node<T> ins_previous = tb_insert.get_previous();
        Node<T> pos_next = position.get_next();
        Node<T> pos_previous = tb_insert.get_previous();
        if (ins_next == null)
            this.tail = tb_insert.get_previous();
        else
            ins_next.set_previous(tb_insert.get_previous());
        tb_insert.get_previous().set_next(ins_next);

        tb_insert.set_next(position);
        if (position.get_previous() == null) {
            position.set_previous(tb_insert);
        }
        Node<T> pos_previous_next = position.get_previous().get_next();
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
}
