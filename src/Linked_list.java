public class Linked_list<T> {
    private Node<T> head;
    private Node<T> tail;
    public Linked_list (Node<T> node) {
        this.head = node;
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
    public Node<T> iterate (Node<T> current_node) {
        current_node = current_node.get_next();
        return current_node;
    }
}
