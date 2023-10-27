import java.io.File;
import java.util.Random;
import java.util.Scanner;
import io.github.lynnnya.linked.list.*;

public class Sort_list {
    public static void main (String[] args) {
        Random rand = new Random();
        Linked_list<Integer> list = new Linked_list<>();
        /*int[] nums = {14, 9, 7, 2, 11, 4, 17, 20, 8, 6,
                5, 12, 15, 13, 3, 16, 10, 18, 1, 19};
        for (int num : nums) {
            list.add_node(num);
        }
        list.to_string();
        System.out.println("\n" + "-----------------------------------------------");*/

        for (int i=0; i<50000; i++)
            list.add_node(rand.nextInt(50000));
        long pre = System.nanoTime();
        long after;
        list.sort();
        after = System.nanoTime();
        long time = after-pre;
        System.out.println(time);
        //list.to_string();
    }
}
