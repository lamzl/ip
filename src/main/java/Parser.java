/**
 * Parses user input and extracts relevant information for command execution.
 * This class handles all string manipulation to translate raw user text into usable data.
 *
 * @author Ethan
 */
public class Parser {

    /**
     * Extracts the main command word from the user's input.
     *
     * @param input The full string entered by the user.
     * @return The first word of the input, representing the command.
     */
    public static String getCommandWord(String input) {
        return input.split(" ")[0];
    }

    /**
     * Extracts the 0-based task index from commands like mark, unmark, and rm.
     *
     * @param input The full string entered by the user.
     * @return The integer index of the task.
     * @throws LZLExceptions If no index is provided or if the input is not a valid number.
     */
    public static int parseIndex(String input) throws LZLExceptions {
        String[] sections = input.split(" ");
        if (sections.length < 2) {
            throw new LZLExceptions("This is an invalid input, please specify the task number!");
        }
        try {
            return Integer.parseInt(sections[1]) - 1;
        } catch (NumberFormatException e) {
            throw new LZLExceptions("Please enter a valid number thank you!");
        }
    }

    /**
     * Extracts the description for a Todo task.
     *
     * @param input The full string entered by the user.
     * @return The description of the Todo.
     * @throws LZLExceptions If the description is empty.
     */
    public static String parseTodo(String input) throws LZLExceptions {
        if (input.trim().length() <= 4) {
            throw new LZLExceptions("Your todo task cannot be empty!");
        }
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new LZLExceptions("Your todo task cannot be empty!");
        }
        return description;
    }

    /**
     * Extracts the description and deadline time for a Deadline task.
     *
     * @param input The full string entered by the user.
     * @return A string array where index 0 is the description and index 1 is the deadline time.
     * @throws LZLExceptions If the "/by" keyword or descriptions are missing.
     */
    public static String[] parseDeadline(String input) throws LZLExceptions {
        String[] parts = input.split("/by", 2);

        if (parts.length < 2) {
            throw new LZLExceptions("I think you did not put /by to specify the deadline");
        }

        String description = parts[0].replaceFirst("deadline", "").trim();
        String by = parts[1].trim();

        if (description.isEmpty() || by.isEmpty()) {
            throw new LZLExceptions("WOW! I think you forgot to write in your description");
        }
        return new String[]{description, by};
    }

    /**
     * Extracts the description, start time, and end time for an Event task.
     *
     * @param input The full string entered by the user.
     * @return A string array where index 0 is the description, index 1 is the start time, and index 2 is the end time.
     * @throws LZLExceptions If the "/from" or "/to" keywords or descriptions are missing.
     */
    public static String[] parseEvent(String input) throws LZLExceptions {
        String[] parts = input.split("/from", 2);
        if (parts.length < 2) {
            throw new LZLExceptions("You need to write /from to input your event.");
        }

        String description = parts[0].replaceFirst("event", "").trim();

        String[] timeParts = parts[1].split("/to", 2);
        if (timeParts.length < 2) {
            throw new LZLExceptions("You need to write /to to input your event.");
        }

        String from = timeParts[0].trim();
        String to = timeParts[1].trim();

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new LZLExceptions("Your event is incomplete, please take a look again thanks.");
        }
        return new String[]{description, from, to};
    }

    /**
     * Extracts the search keyword for the find command.
     *
     * @param input The full string entered by the user.
     * @return The keyword to search for.
     * @throws LZLExceptions If no keyword is provided.
     */
    public static String parseFind(String input) throws LZLExceptions {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new LZLExceptions("Please provide a keyword to find! (e.g., find book)");
        }
        return parts[1].trim();
    }
}