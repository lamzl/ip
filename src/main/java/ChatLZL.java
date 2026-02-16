import java.util.Scanner;

public class ChatLZL {

    private static final String LINE = "--------------------------------";
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        printWelcome();
        run();
        printExit();
    }


    public static void run() {
        boolean isRunning = true;
        while (isRunning) {
            try {
                String input = scanner.nextLine();
                String command = input.split(" ")[0];

                // We handle 'bye' here to break the loop, send everything else to handler
                if (input.equals("bye")) {
                    isRunning = false;
                } else {
                    handleCommand(command, input);
                }
            } catch (LZLExceptions e) {
                System.out.println(LINE);
                System.out.println(e.getMessage());
                System.out.println(LINE);
            } catch (Exception e) {
                System.out.println(LINE);
                System.out.println("Something went very wrong!: " + e.getMessage());
                System.out.println(LINE);
            }
        }
    }


    public static void handleCommand(String command, String fullInput) throws LZLExceptions {
        switch (command) {
        case "list":
            listTasks();
            break;
        case "mark":
            markTask(fullInput, true);
            break;
        case "unmark":
            markTask(fullInput, false);
            break;
        case "todo":
            addTodo(fullInput);
            break;
        case "deadline":
            addDeadline(fullInput);
            break;
        case "event":
            addEvent(fullInput);
            break;
        default:
            throw new LZLExceptions("I do not know what you just typed ;(( AM I BLIND???");
        }
    }

    public static void listTasks() {
        System.out.println(LINE);

        if (taskCount == 0) {
            System.out.println("You have no tasks in your list.");
        } else {
            if (taskCount == 1) {
                System.out.println("Here is the task in your list:");
            } else {
                System.out.println("Here are the tasks in your list:");
            }
            for (int i = 0; i < taskCount; ++i) {
                System.out.println((i + 1) + "." + tasks[i].toString());
            }
        }
        System.out.println(LINE);
    }

    public static void markTask(String input, boolean isDone) throws LZLExceptions {
        String[] sections = input.split(" ");
        if (sections.length < 2) {
            throw new LZLExceptions("This is an invalid input, please specify which command to mark!");
        }
        try {
            int index = Integer.parseInt(sections[1]) - 1;
            if (index >= 0 && index < taskCount) {
                tasks[index].setDone(isDone);
                System.out.println(LINE);
                if (isDone) {
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks[index].toString());
                } else {
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks[index].toString());
                }
                System.out.println(LINE);
            } else {
                throw new LZLExceptions("This task number is not initialised yet!");
            }
        } catch (NumberFormatException e){
            throw new LZLExceptions("Please enter a valid number thank you!");
        }
    }

    public static void addTodo(String input) throws LZLExceptions {
        String description = input.substring(5).trim();
        if (description.isEmpty()){
            throw new LZLExceptions("Your todo task cannot be empty!");
        }
        Task newTask = new Todo(description);
        saveTask(newTask);
    }

    public static void addDeadline(String input) throws LZLExceptions{
        int byIndex = input.indexOf("/by");

        if (byIndex == -1){
            throw new LZLExceptions("I think you did not put /by to specify the deadline");
        }
        String description = input.substring(9, byIndex).trim();
        String by = input.substring(byIndex + 4).trim();

        if (description.isEmpty() || by.isEmpty()){
            throw new LZLExceptions("WOW! I think you forgot to write in your description");
        }

        Task newTask = new Deadline(description, by);
        saveTask(newTask);
    }

    public static void addEvent(String input) throws LZLExceptions{
        int toIndex = input.indexOf("/to");
        int fromIndex = input.indexOf("/from");
        if (toIndex == -1 || fromIndex == -1){
            throw new LZLExceptions("You need to write /to or /from to input your event.");
        }
        String description = input.substring(6, fromIndex).trim();
        String from = input.substring(fromIndex + 6, toIndex).trim();
        String to = input.substring(toIndex + 4).trim();

        if (description.isEmpty() || to.isEmpty() || from.isEmpty()){
            throw new LZLExceptions("Your event is incomplete, please take a look again thanks.");
        }
        Task newTask = new Event(description, from, to);
        saveTask(newTask);
    }

    private static void saveTask(Task newTask) {
        tasks[taskCount] = newTask;
        taskCount++;
        printAddTaskMessage(newTask);
    }


    public static void printAddTaskMessage(Task task) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task.toString());
        if (taskCount == 1) {
            System.out.println("Now you have " + taskCount + " task in your list.");
        } else {
            System.out.println("Now you have " + taskCount + " tasks in your list.");
        }
        System.out.println(LINE);
    }

    public static void printWelcome() {
        String logo = "   _____ _           _   _    ______ _      \n"
                + "  / ____| |         | | | |  |___  /| |     \n"
                + " | |    | |__   __ _| |_| |     / / | |     \n"
                + " | |    | '_ \\ / _` | __| |    / /  | |     \n"
                + " | |____| | | | (_| | |_| |____/ /__| |____ \n"
                + "  \\_____|_| |_|\\__,_|\\__|______|_____|______|";
        System.out.println("Hello from\n" + logo);
        System.out.println(LINE);
        System.out.println("Hello! I'm ChatLZL");
        System.out.println("What is up?");
        System.out.println(LINE);
    }

    public static void printExit() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.print(LINE);
        scanner.close();
    }
}