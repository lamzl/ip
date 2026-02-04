import java.util.Scanner;
public class ChatLZL {
    public static void printAddTaskMessage(Task task, int count){
        String line = "--------------------------------";
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task.toString());
        if (count == 1){
            System.out.println("Now you have " + count + " task in your list.");
        }
        else{
            System.out.println("Now you have " + count + " tasks in your list.");
        }
        System.out.println(line);
    }

    public static void main(String[] args) {
        String logo = "   _____ _           _   _    ______ _      \n"
                + "  / ____| |         | | | |  |___  /| |     \n"
                + " | |    | |__   __ _| |_| |     / / | |     \n"
                + " | |    | '_ \\ / _` | __| |    / /  | |     \n"
                + " | |____| | | | (_| | |_| |____/ /__| |____ \n"
                + "  \\_____|_| |_|\\__,_|\\__|______|_____|______|";
        System.out.println("Hello from\n" + logo);

        String chatBotName = "ChatLZL";
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
                if (taskCount == 1) {
                    System.out.println("Here is the task in your list:");
                }
                else {
                    System.out.println("Here are the tasks in your list:");
                }
                for (int i = 0; i < taskCount; ++i){
                    System.out.println((i + 1) + "." + task[i].toString()); // polymorphism
                }
                System.out.println(line);
            }
            else if (instruction.equals("mark")){
                task[Integer.parseInt(sections[1]) - 1].setDone(true);

                System.out.println(line);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + task[Integer.parseInt(sections[1]) - 1].toString());
                System.out.println(line);
            }
            else if (instruction.equals("unmark")){
                task[Integer.parseInt(sections[1]) - 1].setDone(false);

                System.out.println(line);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(" [ ] " + task[Integer.parseInt(sections[1]) - 1].toString());
                System.out.println(line);
            } else if (instruction.equals("todo")){
                String description = input.substring(5).trim();
                task[taskCount] = new Todo(description);
                taskCount++;
                printAddTaskMessage(task[taskCount - 1], taskCount);
            } else if (instruction.equals("deadline")){
                int byIndex = input.indexOf("/by");
                String description = input.substring(9, byIndex).trim();
                String by = input.substring(byIndex + 4).trim();
                task[taskCount] = new Deadline(description, by);
                taskCount++;
                printAddTaskMessage(task[taskCount - 1], taskCount);
            } else if (instruction.equals("event")){
                int toIndex = input.indexOf("/to");
                int fromIndex = input.indexOf("/from");
                String description = input.substring(6, fromIndex).trim();
                String from = input.substring(fromIndex + 6, toIndex).trim();
                String to = input.substring(toIndex + 4).trim();
                task[taskCount] = new Event(description, from, to);
                taskCount++;
                printAddTaskMessage(task[taskCount - 1], taskCount);
            } else {
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
