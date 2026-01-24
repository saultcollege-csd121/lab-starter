import static java.lang.IO.*;
import static  java.lang.IO.*;
import static java.lang.IO.*;


import static  java.lang.IO.*;
void main() {

    Scanner scanner = new Scanner(System.in);
    List<Integer> numbers = new ArrayList<>();

    System.out.print("How many numbers do you want to enter? ");
    int count = scanner.nextInt();

    for (int i = 0; i < count; i++) {
        System.out.print("Enter a number: ");
        numbers.add(scanner.nextInt());
    }

    double average = calculateAverage(numbers);

    if (average >= 50) {
        System.out.println("Average is high: " + average);
    } else {
        System.out.println("Average is low: " + average);
    }

    try {
        FileWriter writer = new FileWriter("numbers.txt");

        for (int num : numbers) {
            writer.write(num + "\n");
        }

        writer.write("Average: " + average);
        writer.close();

        System.out.println("Data saved to file.");

    } catch (IOException e) {
        System.out.println("Error writing to the file.");
    }

    scanner.close();
}

/**
 * Calculates the average of a list of integers
 *
 * @param numbers a list of numbers entered by the user
 * @return the average value
 */
double calculateAverage(List<Integer> numbers) {

    int total = 0;

    for (int num : numbers) {
        total += num;
    }

    return (double) total / numbers.size();
}