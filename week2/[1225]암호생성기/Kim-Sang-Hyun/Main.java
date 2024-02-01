import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 10; i++) {
            int T = Integer.parseInt(br.readLine());
            int[] numbers = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

            getPassword(T, numbers);
        }
    }

    public static void getPassword(int T, int[] numbers) {
        int gap = 0;
        int index = 0;

        while(true) {
            if(numbers[index] - (gap+1) <= 0){
                numbers[index++] = 0;
                break;
            } 
            numbers[index] -= (gap+1);
            index = (index+1) % 8;
            gap = (gap+1) % 5;
        }

        System.out.print("#" + T + " ");
        for(int i = 0; i < 8; i++) {
            System.out.print(numbers[index] + " ");
            index = (index+1)%8;
        }
        System.out.println();
    }
    
}