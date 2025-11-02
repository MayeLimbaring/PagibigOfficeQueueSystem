import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Centralized Queuing System for Pag-ibig Office
 * Handles queue for 3 help desk stations
 * Allows reset and viewing of current queue number
 */
public class QueueSystem {
    private static AtomicInteger currentNumber = new AtomicInteger(0);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("====================================");
        System.out.println("  PAG-IBIG CENTRALIZED QUEUE SYSTEM ");
        System.out.println("====================================");

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Get Next Queue Number");
            System.out.println("2. View Current Queue Number");
            System.out.println("3. Reset Queue Number");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Enter a number 1-4:");
                scanner.next();
            }

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    int next = nextQueueNumber();
                    System.out.println("Your queue number: " + next);
                    break;
                case 2:
                    System.out.println("Current queue number: " + getCurrentNumber());
                    break;
                case 3:
                    System.out.print("Enter new starting number: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Enter a number:");
                        scanner.next();
                    }
                    int newNum = scanner.nextInt();
                    resetQueueNumber(newNum);
                    System.out.println("Queue number has been reset to " + newNum);
                    break;
                case 4:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }

        } while (choice != 4);

        scanner.close();
    }

    // Generate next queue number (thread-safe)
    public static int nextQueueNumber() {
        return currentNumber.incrementAndGet();
    }

    // Return current queue number
    public static int getCurrentNumber() {
        return currentNumber.get();
    }

    // Reset queue number to inputted value
    public static void resetQueueNumber(int newValue) {
        currentNumber.set(newValue);
    }
}
