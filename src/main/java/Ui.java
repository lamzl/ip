import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    private static String LINE = "-------------------------------";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    public void printWelcome() {
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

    public String readCommand () {
        return scanner.nextLine();
    }

    public void printLine() {
        System.out.println(LINE);
    }

    public void printExit() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
        scanner.close();
    }

    public void showError(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public boolean hasNextCommand() {
        return scanner.hasNextLine();
    }

    public void showFoundTasks(ArrayList<Task> matchingTasks) {
        printLine();
        if (matchingTasks.isEmpty()) {
            showMessage("No matching tasks found in your list.");
        } else {
            showMessage("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                showMessage((i + 1) + "." + matchingTasks.get(i).toString());
            }
        }
        printLine();
    }
}
