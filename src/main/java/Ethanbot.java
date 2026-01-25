import java.util.Scanner;
public class Ethanbot {
    public static void main(String[] args) {
        String chatBotName = "Ethanbot";
        String line = "--------------------------------";
        Scanner newScan = new Scanner(System.in); // getting input
        Task[] task = new Task[100]; // storing the tasks (now task objects)
        int taskCount = 0; // track the number of tasks

        // Initial Output
        System.out.println(line);
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you?");
        System.out.println(line);

        // While loop to print whatever the user types, until user types "bye"
        while(true){
            String input = newScan.nextLine();
            String[] sections = input.split(" ");
            String instruction = sections[0];

            if (input.equals("bye")){
                break;
            }
            else if (input.equals("list")){
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; ++i){
                    String status = task[i].isDone() ? "[X] " : "[ ] ";
                    System.out.println((i + 1) + "." + status + " " + task[i].getDescription());
                }
                System.out.println(line);
            }
            else if (instruction.equals("mark")){
                task[Integer.parseInt(sections[1]) - 1].setDone(true);

                System.out.println(line);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" [X] " + task[Integer.parseInt(sections[1]) - 1].getDescription());
                System.out.println(line);
            }
            else if (instruction.equals("unmark")){
                task[Integer.parseInt(sections[1]) - 1].setDone(false);

                System.out.println(line);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(" [ ] " + task[Integer.parseInt(sections[1]) - 1].getDescription());
                System.out.println(line);
            }
            else{
                // Store the input into the array
                task[taskCount] = new Task(input);
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
