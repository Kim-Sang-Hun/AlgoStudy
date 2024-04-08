import java.util.*;
import java.io.*;

public class Main {

    private static char[] numbers;
    private static int size;
    private static boolean[] used;

    private static Stack<Integer> stack = new Stack<Integer>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        numbers = br.readLine().toCharArray();

        size = (numbers.length < 10) ? numbers.length : 9 + (numbers.length - 9) / 2;
        used = new boolean[size+1];

        seperateNumbers(0);

    }

    private static void seperateNumbers(int index) {

        if(index >= numbers.length) {
            for(Integer num : stack) System.out.print(num + " ");
            System.exit(0);
        }

        int number;

        // 1 ~ 9
        number = numbers[index] - '0';
        if(number <= size && !used[number]) {
            used[number] = true;
            stack.add(number);

            seperateNumbers(index+1);

            used[number] = false;
            stack.pop();
        }

        // 10 ~
        if(index+1 < numbers.length) {
            number = (numbers[index] - '0') * 10 + (numbers[index+1] - '0');

            if(number <= size && !used[number]) {
                used[number] = true;
                stack.add(number);
    
                seperateNumbers(index+2);
    
                used[number] = false;
                stack.pop();
            }

        }

    }

}
