import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles the storage of past tasks
 * This is to ensure that the task will still be present after exiting the program
 */
public class Storage {
    private String filePath;
    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The relative or absolute path to the file where data will be stored.
     */
    public Storage(String filePath){
        this.filePath = filePath;
    }
    /**
     * Saves the current list of tasks locally.
     * If the target directory or file does not exist, they will be created.
     * The tasks are formatted into a specific string format before being written to the file.
     *
     * @param tasks The ArrayList of Task objects to be saved.
     */
    public void saveData(ArrayList<Task> tasks) {
        try {
            File file = new File(filePath);
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
    /**
     * Loads the tasks from the hard disk into an ArrayList.
     * the corresponding Todo, Deadline, or Event objects.
     *
     * @return An ArrayList containing the tasks loaded from the file. Returns an empty list if the file does not exist.
     */
    public ArrayList<Task> loadData() {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return loadedTasks;
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
                    loadedTasks.add(t);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
        return loadedTasks;
    }
}
