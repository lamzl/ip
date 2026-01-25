import java.util.Scanner;
public class Ethanbot {
    public static void main(String[] args) {
        String chatBotName = "Ethanbot";
        String line = "--------------------------------";
        Scanner newScan = new Scanner(System.in); // getting input
        String[] task = new String[100]; // storing the tasks
        int taskCount = 0; // track the number of tasks

        // Initial Output
        System.out.println(line);
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you?");
        System.out.println(line);

        // While loop to print whatever the user types, until user types "bye"
        while(true){
            String input = newScan.nextLine();
            if (input.equals("bye")){
                break;
            }
            else if (input.equals("list")){
                System.out.println(line);
                for (int i = 0; i < taskCount; ++i){
                    System.out.println((i + 1) + ". " + task[i]);
                }
            }
            else{
                // Store the input into the array
                task[taskCount] = input;
                ++taskCount;

                System.out.println(line);
                System.out.println("added: " + input);
                System.out.println(line);
            }
        }
        // ending message
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.print(line);

        newScan.close();
    }
}
