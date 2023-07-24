import java.util.*;
import java.time.*;
import java.math.*;
import java.io.*;

${solution}
public class Driver {
     private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ${paramsInputSection}
        scanner.close();


        long startTime = System.nanoTime();

        ${returnType} result = new Solution().${methodName}(${paramList});

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        Runtime runtime = Runtime.getRuntime();
        double memory = (double) (runtime.totalMemory() - runtime.freeMemory());
        printResult(result);
        System.out.println(executionTime);
        System.out.println(memory/(1024*1024));
    }
    private static void printResult(int result) {
        System.out.println(result);
    }
    private static void printResult(boolean result) {
        System.out.println(result);
    }
    private static void printResult(String result) {
        System.out.println(result);
    }
    private static void printResult(int[] result) {
        System.out.println(Arrays.toString(result));
    }
    private static void printResult(String[] result) {
        System.out.println(Arrays.toString(result));
    }


    private static String READ_STRING(){
        String input = scanner.nextLine();
        input = input.replace(" ", "");
        return input;
    }

    private static String[] READ_STRING_ARR() {
        String input = scanner.nextLine();
        if (input.equals("[]")) {
            return new String[0];
         }
        // Удаляем квадратные скобки и пробелы
        input = input.replace("[", "").replace("]", "").replace(" ", "");

        // Разделяем строку на элементы
        String[] elements = input.split(",");

        return elements;
    }
    private static int[] READ_INTEGER_ARR() {
        String input = scanner.nextLine();
        if (input.equals("[]")) {
         return new int[0];
         }

        // Удаляем квадратные скобки и пробелы
        input = input.replace("[", "").replace("]", "").replace(" ", "");

        // Разделяем строку на элементы
        String[] elements = input.split(",");

        int[] arr = new int[elements.length];
        for (int i = 0; i < elements.length; i++) {
            arr[i] = Integer.parseInt(elements[i]);
        }

        return arr;
    }
    private static int READ_INTEGER() {
        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }
}
