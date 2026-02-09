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
            String input = scanner.nextLine();
            String command = input.split(" ")[0];

            // We handle 'bye' here to break the loop, send everything else to handler
            if (input.equals("bye")) {
                isRunning = false;
            } else {
                handleCommand(command, input);
            }
        }
    }


    public static void handleCommand(String command, String fullInput) {
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
            addGenericTask(fullInput);
            break;
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

    public static void markTask(String input, boolean isDone) {
        String[] sections = input.split(" ");
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
            System.out.println("Error: Task number out of bounds.");
        }
    }

    public static void addTodo(String input) {
        String description = input.substring(5).trim();
        Task newTask = new Todo(description);
        saveTask(newTask);
    }

    public static void addDeadline(String input) {
        int byIndex = input.indexOf("/by");
        String description = input.substring(9, byIndex).trim();
        String by = input.substring(byIndex + 4).trim();
        Task newTask = new Deadline(description, by);
        saveTask(newTask);
    }

    public static void addEvent(String input) {
        int toIndex = input.indexOf("/to");
        int fromIndex = input.indexOf("/from");
        String description = input.substring(6, fromIndex).trim();
        String from = input.substring(fromIndex + 6, toIndex).trim();
        String to = input.substring(toIndex + 4).trim();
        Task newTask = new Event(description, from, to);
        saveTask(newTask);
    }

    public static void addGenericTask(String input) {
        tasks[taskCount] = new Task(input);
        taskCount++;
        System.out.println(LINE);
        System.out.println("added: " + input);
        System.out.println(LINE);
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