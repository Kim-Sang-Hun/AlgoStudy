import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Shin-Eun-Jin {
    static String input;
    static Stack<Character> stack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();

        input = br.readLine();
        stack = new Stack<>();

        for (char curC : input.toCharArray()) {

            if (curC == '+' || curC == '-' || curC == '*' || curC == '/') {
                // 현재 연산자의 우선순위가 스택의 맨 위 연산자보다 높거나 같을 때까지 스택에서 pop하고 결과에 추가
                while (!stack.isEmpty() && precedence(curC) <= precedence(stack.peek())) {
                    ans.append(stack.pop());
                }

                stack.push(curC);
            } else if (curC == '(') {
                stack.push(curC);
            } else if (curC == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    ans.append(stack.pop());
                }
                stack.pop();
            } else {
                ans.append(curC);
            }
        }

        while (!stack.isEmpty()) {
            ans.append(stack.pop());
        }

        System.out.println(ans);
    }

    public static int precedence(char op) {
        switch(op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1;
    }
}
