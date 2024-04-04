import java.io.*;
import java.util.*;

//왜 된 거지..?
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static String a;

    static void input() throws IOException {
        a = br.readLine();
    }

    //코드 너무 드럽다.
    static void solution() {
        Stack<Character> q = new Stack<>();
        for (int i = 0; i < a.length(); ++i) {
            char cmd = a.charAt(i);
            if(cmd == ')'){
                while(true) {
                    char prev = q.pop();
                    if(prev == '(') break;
                    sb.append(prev);
                }
            } else if(cmd == '*' || cmd == '/'){
                while (!q.isEmpty()) {
                    char prev = q.peek();
                    if(!(prev == '*' || prev == '/')) break;
                    sb.append(q.pop());
                }
                q.add(cmd);
            } else if(cmd == '+' || cmd == '-'){
                while (!q.isEmpty()) {
                    char prev = q.peek();
                    if(!(prev == '*' || prev == '/' || prev == '-' || prev == '+')) break;
                    sb.append(q.pop());
                }
                q.add(cmd);
            } else if(cmd == '(') {
                q.add(cmd);
            } else{ //알파벳들
                sb.append(cmd);
            }
        }
        while (!q.isEmpty()) {
            sb.append(q.pop());
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}
