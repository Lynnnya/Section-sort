import java.util.Random;

import io.github.lynnnya.linkedlist.*;

public class Sort_list {
    private static final boolean debug = false;
    public static void do_debug () {
        Random rand = new Random();
        boolean is_wrong = false;

        int c = 0;
        while (!is_wrong) {
            Linked_list<Integer> list = new Linked_list<>();
            Linked_list<Integer> ref_list = new Linked_list<>();
            c++;
            System.out.println("run " + c);
            int num;
            for (int i = 0; i < 25; i++) {
                num = rand.nextInt(500);
                list.add_node(num);
                ref_list.add_node(num);
            }
            list.sort();
            Node<Integer> current = list.get_head();
            while (current != list.get_tail().get_next()) {
                if (current.get_previous() == null)
                    current = current.get_next();
                if (current.get_payload() < current.get_previous().get_payload())
                    is_wrong = true;
                current = current.get_next();
            }
            if (c == 100000 || is_wrong) {
                System.out.println("\n" + "-----------------------------------------------");
                ref_list.to_string();
                System.out.println("\n" + "-----------------------------------------------");
                list.to_string();
                break;
            }
        }
    }
    public static void main (String[] args) {
        Random rand = new Random();
        Linked_list<Integer> list = new Linked_list<>();

        /*int[] nums = {12, 15, 11, 7, 5, 12, 6, 3, 1, 19, 6, 16, 10, 9, 7, 6, 17, 8, 5, 9, 13, 15, 3, 2,
                0, 1, 6, 14, 18, 17, 0, 1, 3, 5, 7, 11, 12, 15, 12, 6, 6, 16, 10, 9, 7, 6, 17, 8, 5, 9,
                13, 15, 3, 2, 1, 6, 14, 18, 17, 19};
        int[] nums = {7, 39, 11, 40, 16, 40, 3, 10, 22, 46, 8, 20, 17, 13, 12, 15,
                30, 17, 44, 0, 24, 24, 15, 43, 38, 9, 19, 12, 26, 5, 42, 2, 10,
                31, 22, 20, 5, 25, 21, 49, 6, 48, 29, 29, 26, 16, 39, 32, 20, 16, };
        for (int num : nums) {
            list.add_node(num);
        }
        list.to_string();
        System.out.println("\n" + "-----------------------------------------------");*/

        for (int i=0; i<50000; i++)
            list.add_node(rand.nextInt(50000));
        //list.to_string();
        long pre = System.nanoTime();
        long after;
        list.sort();
        after = System.nanoTime();
        long time = after-pre;
        System.out.println(time);
        /*System.out.println("\n" + "-----------------------------------------------");
        list.sort();
        list.to_string();*/


        if (debug) {
            do_debug();
        }
    }
}
