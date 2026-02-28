import java.util.ArrayList;

public class ChatLZL {

    private static final String LINE = "--------------------------------";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public ChatLZL(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        tasks = new TaskList(storage.loadData());
    }

    public static void main(String[] args) {
        new ChatLZL("./data/chatlzl.txt").run();
    }

    public void run() {
        ui.printWelcome();
        if (!tasks.isEmpty()) {
            System.out.println("There are " + tasks.getSize() + " tasks I have loaded!");
            System.out.println(LINE);
        }
        boolean isRunning = true;
        while (isRunning) {
            try {
                if (!ui.hasNextCommand()) {
                    break;
                }
                String input = ui.readCommand();

                if (input.trim().isEmpty()){
                    continue;
                }

                String command = Parser.getCommandWord(input);

                if (input.equals("bye")) {
                    isRunning = false;
                } else {
                    handleCommand(command, input);
                }
            } catch (LZLExceptions e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showMessage("Something went very wrong!: " + e.getMessage());
            }
        }
        ui.printExit();
    }


    public void handleCommand(String command, String fullInput) throws LZLExceptions {
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
        case "find":
            findTask(fullInput);
            break;
        default:
            throw new LZLExceptions("I do not know what you just typed ;(( AM I BLIND???");
        }
    }

    public void listTasks() {
        System.out.println(LINE);

        if (tasks.isEmpty()) {
            System.out.println("You have no tasks in your list.");
        } else {
            if (tasks.getSize() == 1) { // if the number of tasks is only 1
                System.out.println("Here is the task in your list:");
            } else {
                System.out.println("Here are the tasks in your list:");
            }
            for (int i = 0; i < tasks.getSize(); ++i) {
                System.out.println((i + 1) + "." + tasks.getTask(i).toString());
            }
        }
        System.out.println(LINE);
    }

    public void markTask(String input, boolean isDone) throws LZLExceptions {
        try {
            int index = Parser.parseIndex(input);
            if (index >= 0 && index < tasks.getSize()) {
                tasks.getTask(index).setDone(isDone);
                storage.saveData(tasks.getTasks());
                ui.printLine();
                if (isDone) {
                    ui.showMessage("Marking this task as done!...");
                } else {
                    ui.showMessage("Okay, I will mark this task as not done...");
                }
                ui.printLine();
            } else {
                throw new LZLExceptions("This task number is not initialised yet!");
            }
        } catch (NumberFormatException e){
            throw new LZLExceptions("Please enter a valid number thank you!");
        }
    }

    public void addTodo(String input) throws LZLExceptions {
        String description = Parser.parseTodo(input);
        Task newTask = new Todo(description);
        saveTask(newTask);
    }

    public void addDeadline(String input) throws LZLExceptions{
        String[] parsedData = Parser.parseDeadline(input);
        Task newTask = new Deadline(parsedData[0], parsedData[1]);
        saveTask(newTask);
    }

    public void addEvent(String input) throws LZLExceptions{
        String[] parsedData = Parser.parseEvent(input);
        Task newTask = new Event(parsedData[0], parsedData[1], parsedData[2]);
        saveTask(newTask);
    }

    private void saveTask(Task newTask) {
        tasks.addTask(newTask);
        storage.saveData(tasks.getTasks());
        printAddTaskMessage(newTask);
    }


    public void deleteTask(String input) throws LZLExceptions {
        try {
            int idx = Parser.parseIndex(input);

            if (idx >= 0 && idx < tasks.getSize()) {
                Task removedTask = tasks.deleteTask(idx);
                storage.saveData(tasks.getTasks());
                System.out.println(LINE);
                System.out.println("Okay, I will remove this task: ");
                System.out.println(" " + removedTask.toString());


                if (tasks.getSize() == 1) {
                    System.out.println("You have 1 task in your list right now!");
                } else {
                    System.out.println("You have " + tasks.getSize() + " tasks in your list right now!");
                }
            } else {
                throw new LZLExceptions("That task does not exist!");
            }
            System.out.println(LINE);
        } catch (NumberFormatException e) {
            throw new LZLExceptions("This is not a valid number, can you please add a valid number to delete the task?");
        }

    }

    public void printAddTaskMessage(Task task) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task.toString());
        if (tasks.getSize() == 1) {
            System.out.println("Now you have " + tasks.getSize() + " task in your list.");
        } else {
            System.out.println("Now you have " + tasks.getSize() + " tasks in your list.");
        }
        System.out.println(LINE);
    }

    public void findTask(String input) throws LZLExceptions {
        String keyword = Parser.parseFind(input);
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        ui.showFoundTasks(matchingTasks);
    }

}