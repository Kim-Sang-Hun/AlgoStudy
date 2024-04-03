import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        char[] infix = br.readLine().toCharArray();

        Stack<Character> stack = new Stack<Character>();

        for(char ch : infix) {

            if(ch == '+' || ch == '-') {
                while(!stack.isEmpty() && stack.peek() != '(') sb.append(stack.pop());
                stack.add(ch);
            }

            else if(ch == '*' || ch == '/') {
                while(!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/') && stack.peek() != '(') sb.append(stack.pop());
                stack.add(ch);
            }

            else if(ch == '(') {
                stack.add('(');
            }

            else if(ch == ')') {
                while (stack.peek() != '(') sb.append(stack.pop());
                stack.pop();
            }

            else if('A' <= ch && ch <= 'Z') {
                sb.append(ch);
            }

        }

        while (!stack.isEmpty()) sb.append(stack.pop());

        System.out.println(sb);
        
    }

}
