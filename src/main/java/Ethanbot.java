import java.util.Scanner;
public class Ethanbot {
    public static void main(String[] args) {
        String chatBotName = "Ethanbot";
        String line = "--------------------------------";
        Scanner newScan = new Scanner(System.in);

        // Initial Output
        System.out.println(line);
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you?");
        System.out.println(line);

        // While loop to print whatever the user types, until user types "bye"
        while(true){
            String input = newScan.nextLine();
            if (input.equals("bye")){
                break;
            }
            else{
                System.out.println(line);
                System.out.println(input);
                System.out.println(line);
            }
        }
        // ending message
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.print(line);
    }
}
