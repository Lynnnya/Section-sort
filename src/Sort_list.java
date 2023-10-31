import java.util.Random;

import io.github.lynnnya.linkedlist.*;

public class Sort_list {
    public static void main (String[] args) {
        Random rand = new Random();
        Linked_list<Integer> list = new Linked_list<>();
        int[] nums = {14, 9, 7, 22, 26, 2, 30, 11, 4, 29, 17, 20, 21, 8, 6,
                5, 23, 28, 12, 15, 13, 25, 27, 3, 16, 10, 24, 18, 1, 19};
        for (int num : nums) {
            list.add_node(num);
        }
        list.to_string();
        System.out.println("\n" + "-----------------------------------------------");

        /*for (int i=0; i<50000; i++)
            list.add_node(rand.nextInt(50000));
        long pre = System.nanoTime();
        long after;
        list.sort();
        after = System.nanoTime();
        long time = after-pre;
        System.out.println(time);*/
        list.sort();
        list.to_string();
    }
}
