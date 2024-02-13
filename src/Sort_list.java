import java.util. *;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import io.github.lynnnya.linkedlist.*;

public class Sort_list {
    private static final boolean debug = false;

    public static void do_debug() {
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

    public static void main(String[] args) {
        Random rand = new Random();
        Linked_list<Integer> list = new Linked_list<>();

        /*
         * int[] nums = {12, 15, 11, 7, 5, 12, 6, 3, 1, 19, 6, 16, 10, 9, 7, 6, 17, 8,
         * 5, 9, 13, 15, 3, 2,
         * 0, 1, 6, 14, 18, 17, 0, 1, 3, 5, 7, 11, 12, 15, 12, 6, 6, 16, 10, 9, 7, 6,
         * 17, 8, 5, 9,
         * 13, 15, 3, 2, 1, 6, 14, 18, 17, 19};
         */
        // int[] nums = { 475, 219, 331, 116, 22, 116, 417, 101, 398, 154, 483, 347,
        // 261, 229, 392, 216, 379, 400, 474,
        // 417, 372, 279, 91, 373, 314 };
        // for (int num : nums) {
        // list.add_node(num);
        // }
        // list.to_string();
        // System.out.println("\n" + "-----------------------------------------------");

        // System.out.println("\n" + "-----------------------------------------------");
        // list.sort();
        // list.to_string();

//        for (int i = 0; i < 50000; i++) {
//            list.add_node(rand.nextInt(50000));
//        }
//        // list.to_string();
//        long pre = System.nanoTime();
//        long after;
//        list.sort();
//        after = System.nanoTime();
//        long time = after - pre;
//        System.out.println(time);

        if (debug) {
            do_debug();
        }
        List<Integer> test = new LinkedList<>();
        for (int i = 0; i < 100000; i++) {
            test.add(rand.nextInt(50000));
        }
        long pre = System.nanoTime();
        long after;
        Collections.sort(test);
        after = System.nanoTime();
        long time = after - pre;
        System.out.println(time);
    }
}
