public class Exponential_sort<T> {
    private Linked_list<?> list;
    private Linked_list<Linked_list<Node<?>>> node_list;
    public Exponential_sort(Linked_list<?> list) {
        this.list = list;
    }
    public Linked_list<?> get_list () {
        return list;
    }
    public Linked_list<?> sort () {
        Node<?> current_node = list.get_head();
        while (current_node != null) {

        }
        return list;
    }
}
