import java.io.*;
import java.util.*;

public class Kim-Sang-Hyun {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int t = 1; t <= 10; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            String[] numbers = st.nextToken().split("");

            System.out.print("#" + t + " ");
            solution(N, numbers);
        }
    }

    public static void solution(int N, String[] numbers) {
        Stack<String> stack = new Stack<String>();

        for (String number : numbers) {

            // stack이 비어있는 경우
            if(stack.isEmpty()) {
                stack.add(number);
                continue;
            }

            // 같은 번호가 붙어있는 경우
            if(stack.peek().equals(number)) {
                stack.pop();
                continue;
            }

            // 같지 않은 번호가 붙어있는 경우
            if(!stack.peek().equals(number)) {
                stack.add(number);
                continue;
            }

        }
        for (String string : stack) {
            System.out.print(string);
        }
        System.out.println();
        
    }

    
}
