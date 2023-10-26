import java.io.File;
import java.util.Scanner;

public class Sort_list {
    public static File get_file() {
        String file_name;
        System.out.print("Enter the name of the file to be opened: ");
        Scanner string_input = new Scanner(System.in);
        file_name = string_input.nextLine();

        File new_file = new File(file_name);
        if (!new_file.canRead()) {
            System.out.println("File Can Not Be Opened.");
            new_file = get_file();//recursion until the file opens successfully
        }
        return new_file;
    }
    public static void main (String[] args) {
        File input_file = get_file();
    }
}
