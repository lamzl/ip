public class Parser {

    public static String getCommandWord(String input) {
        return input.split(" ")[0];
    }


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

    public static String parseFind(String input) throws LZLExceptions {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new LZLExceptions("Please enter a keyword to find");
        }
        return parts[1].trim();
    }
}