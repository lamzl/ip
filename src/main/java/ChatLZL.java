import java.lang.reflect.Field;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Paths;



public class ChatLZL {

    private static final String LINE = "--------------------------------";
    private static final String FILE_PATH = "./data/chatlzl.txt";
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        printWelcome();
        run();
        printExit();
    }

    public static void run() {
        loadData();
        if (!tasks.isEmpty()) {
            System.out.println("There are " + tasks.size() + " tasks I have loaded!");
            System.out.println(LINE);
        }
        boolean isRunning = true;
        while (isRunning) {
            try {
                String input = scanner.nextLine();

                if (input.trim().isEmpty()){
                    continue;
                }

                String command = input.split(" ")[0];

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
        case "ls":
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
        case "rm":
            deleteTask(fullInput);
            break;
        default:
            throw new LZLExceptions("I do not know what you just typed ;(( AM I BLIND???");
        }
    }

    public static void listTasks() {
        System.out.println(LINE);

        if (tasks.isEmpty()) {
            System.out.println("You have no tasks in your list.");
        } else {
            if (tasks.size() == 1) { // if the number of tasks is only 1
                System.out.println("Here is the task in your list:");
            } else {
                System.out.println("Here are the tasks in your list:");
            }
            for (int i = 0; i < tasks.size(); ++i) {
                System.out.println((i + 1) + "." + tasks.get(i).toString());
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
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).setDone(isDone);
                System.out.println(LINE);
                if (isDone) {
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks.get(index).toString());
                } else {
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks.get(index).toString());
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
        tasks.add(newTask);
        saveData();
        printAddTaskMessage(newTask);
    }


    public static void deleteTask(String input) throws LZLExceptions {
        String[] sections = input.split(" ");
        if (sections.length < 2) {
            throw new LZLExceptions("This is an invalid input, please specify which command to delete!");
        }
        try {
            int idx = Integer.parseInt(sections[1]) - 1;

            if (idx >= 0 && idx < tasks.size()) {
                Task removedTask = tasks.remove(idx);
                saveData();
                System.out.println(LINE);
                System.out.println("Okay, I will remove this task: ");
                System.out.println(" " + removedTask.toString());


                if (tasks.size() == 1) {
                    System.out.println("You have 1 task in your list right now!");
                } else {
                    System.out.println("You have " + tasks.size() + " tasks in your list right now!");
                }
            } else {
                throw new LZLExceptions("That task does not exist!");
            }
            System.out.println(LINE);
        } catch (NumberFormatException e) {
            throw new LZLExceptions("This is not a valid number, can you please add a valid number to delete the task?");
        }

    }

    public static void printAddTaskMessage(Task task) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task.toString());
        if (tasks.size() == 1) {
            System.out.println("Now you have " + tasks.size() + " task in your list.");
        } else {
            System.out.println("Now you have " + tasks.size() + " tasks in your list.");
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


    public static void saveData() {
        try {
            File file = new File(FILE_PATH);
            File parentDir = file.getParentFile();

            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            FileWriter fw = new FileWriter(file, false);

            for (Task task : tasks){
                String type = "";
                String extra = "";
                String isDone = task.isDone() ? "1" : "0";

                if (task instanceof Deadline) {
                    type = "D";
                    extra = " | " + ((Deadline) task).by;
                } else if (task instanceof Event) {
                    type = "E";
                    extra = " | " + ((Event) task).from + "-" + ((Event) task).to;
                } else if (task instanceof Todo){
                    type = "T";
                }
                fw.write(type + " | " + isDone + " | " + task.getDescription() + extra + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private static void loadData() {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return;
        }

        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" \\| ");

                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task t = null;

                switch (type) {
                case "T":
                    t = new Todo(description);
                    break;
                case "D":
                    t = new Deadline(description, parts[3]);
                    break;
                case "E":
                    String[] time = parts[3].split("-");
                    t = new Event(description, time[0], time[1]);
                    break;
                }

                if (t != null) {
                    if (isDone) t.setDone(true);
                    tasks.add(t);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    public static void printExit() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.print(LINE);
        scanner.close();
    }
}